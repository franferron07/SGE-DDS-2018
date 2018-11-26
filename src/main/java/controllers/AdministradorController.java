package controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import repositorios.RepositorioUsuarios;
import repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Usuario;
import usuarios.Administrador;
import usuarios.Cliente;
import Utils.ConsumoHogar;
import Utils.ConsumoTipoDispositivo;
import Utils.ConsumoTransformador;
import dispositivos.Consumo;
import dispositivos.DispositivoDetalle;
import models.ModelHelper;

public class AdministradorController {
	
	public RepositorioUsuarios repositorio_usuarios;
	public RepositorioClientes repositorio_clientes;
	
	public AdministradorController(){
		repositorio_usuarios = new RepositorioUsuarios();
	}
	
	public ModelAndView reportes (Request request,Response response) {
		Map<String, Object> model=new HashMap<>();
		
		return new ModelAndView(model, "reportes.hbs");
	}
	
	
	
	public String reportes_results(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		
		String id = Integer.toString(request.session().attribute("id"));
		
		List<NameValuePair> pairs = URLEncodedUtils.parse(request.body(), Charset.defaultCharset());

        Map<String, String> params = toMap(pairs);
        
        String desde = request.queryParams("fecha_desde");
        String hasta = request.queryParams("fecha_hasta");
        String reporte = params.get("reporte");
        
        LocalDateTime fechaParse_desde = this.parsFecha(desde);
        LocalDateTime fechaParse_hasta = this.parsFecha(hasta);
        
        String queryConsumoPorHogar = "SELECT u.nombre, u.apellido, u.domicilio, SUM(c.consumo) + SUM(DATEDIFF(" + fechaParse_desde + ", " + fechaParse_hasta + ") * de.horasPorDia)"+
				   "FROM usuario u"+
				   "JOIN cliente cl ON u.usuario_id = cl.usuario_id"+
				   "JOIN dispositivo d on d.cliente_id = cl.usuario_id"+
				   "JOIN dispositivoesencial de on de.id = d.id"+
				   "JOIN dispositivointeligente di on di.dispositivo_id = d.id"+
				   "JOIN modo m on m.dispositivo_inteligente_id = di.id"+
				   "JOIN consumo c ON c.modo_id = m.id"+
				   
				   "WHERE c.fechainicio >="+ fechaParse_desde+
				   "AND c.fechafin <="+ fechaParse_hasta+
				 
				   "GROUP BY u.nombre, u.apellido, u.domicilio";
        
        
        //En caso de querer obtener las queries de los consumos por cliente correspondientes a sus dispositivos inteligentes
        //y estándares por separado, se usarían las siguientes quieries (ligeramente modificadas, ya que no están completas).
        /*
        String queryConsumoXClienteXInteligente = "SELECT u.nombre, u.apellido, u.domicilio, SUM(c.consumo)"+
        							   "FROM usuario u"+
        							   "JOIN cliente cl ON u.usuario_id = cl.usuario_id"+
        							   "JOIN dispositivo d on d.cliente_id = cl.usuario_id"+
        							   "JOIN dispositivointeligente di on di.dispositivo_id = d.id"+
        							   "JOIN modo m on m.dispositivo_inteligente_id = di.id"+
        							   "JOIN consumo c ON c.modo_id = m.id"+
        							   
        							   "WHERE c.fechainicio >="+ fechaParse_desde+
        							   "AND c.fechafin <="+ fechaParse_hasta+
        							 
        							   "GROUP BY u.nombre, u.apellido, u.domicilio";
        							   
        							   
        String queryConsumoXClienteXEsencialr = "SELECT u.nombre, u.apellido, u.domicilio, SUM(SELECT DATEDIFF"+ fechaParse_desde + ", " +  fechaParse_hasta + ") * de.horasPorDia"+
        							  "FROM usuario u"+
        							  "JOIN cliente cl ON u.usuario_id = cl.usuario_id"+
        							  "JOIN dispositivo d on d.cliente_id = cl.usuario_id"+
        							  "JOIN dispositivoesencial de on de.id = d.id"+

        							  
									  "GROUP BY u.nombre, u.apellido, u.domicilio";
        */							   
        							   
        javax.persistence.Query queryA = ModelHelper.getEntityManager().createNativeQuery(queryConsumoPorHogar, ConsumoHogar.class);
		List<ConsumoHogar> consumoHogares = queryA.getResultList();
		model.put("consumoHogares", consumoHogares);
        							   
        							   
		//Creo que esta query está conceptualmente mal. Es muy sencillo separarla en dos, obteniendo así el consumo total de todos los dispositivos inteligentes por un lado
		//y el consumo total de los dispositivos esenciales por el otro, pero no sabría como pasarle ambas queries al persistence.Query juntas.
        String queryConsumoTipoDispositivo = "SELECT di.id as 'Dispositivo Inteligente', SUM(c.consumo) as 'Consumo de dispositivos inteligentes', de.id as 'dispositivo esencial', SUM(DATEDIFF(" + fechaParse_desde + ", " + fechaParse_hasta + ") * de.horasPorDia) as 'Consumo de dispositivos esenciales'"+
        										 "FROM dispositivo d"+
        										 "JOIN dispositivointeligente di ON di.dispositivo_id = d.id"+
        										 "JOIN dispositivoesencial de on de.id = d.id"+
        										 "JOIN modo m ON m.inteligente_id = d.id"+
        										 "JOIN consumo c ON c.modo_id = m.id"+
        										 
        										 "WHERE u.tipo_usuario = 'cliente'"+
        										 "GROUP BY di_id, de.id";
        										 
        /*
        String queryConsumoPorDE = "SELECT SUM(c.consumo)"+
        										 "FROM dispositivo d"+
        										 "JOIN modo m ON m.inteligente_id = d.id"+
        										 "JOIN consumo c ON u.id = c.id"+
        										 "WHERE u.tipo_usuario = 'cliente'";
        */
        
        javax.persistence.Query queryB = ModelHelper.getEntityManager().createNativeQuery(queryConsumoTipoDispositivo, ConsumoTipoDispositivo.class);
		List<ConsumoTipoDispositivo> consumoTipoDispositivos = queryB.getResultList();
		model.put("consumoTipoDispositivos", consumoTipoDispositivos);
        										 
        
		//Falta completar
        String queryConsumoPorTransformador = "SELECT t.id, c.longitud, c.latitud, SUM(consumo)"+
                								 "FROM transformador t"+
                								 "JOIN ubicable u ON u.ubicable_id = t.ubicable_id"+
                								 "JOIN coordenada c ON u.ubicable_id = c.ubicable_id";
        
        javax.persistence.Query queryC = ModelHelper.getEntityManager().createNativeQuery(queryConsumoPorHogar, ConsumoTransformador.class);
		List<ConsumoTransformador> consumoTransformadores = queryC.getResultList();
		model.put("consumoTransformadores", consumoTransformadores);
                								 
	
               
        //String desde = params.get("desde");
        //String hasta = params.get("hasta");
        //String reporte = params.get("reporte");
        
        System.out.println(desde);
        System.out.println(hasta);
        System.out.println(reporte);
        
		return "";
	}
	

	public ModelAndView mostrar(Request request, Response response, boolean permiteEdicion) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		Usuario usuario = repositorio_usuarios.buscarUsuario(id);
		model.put("usuario", usuario);
		model.put("permiteEdicion", permiteEdicion);
		return new ModelAndView(model, "modalUsuario.hbs");
}
	
/*

	public ModelAndView reporte(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		int id = Integer.parseInt(request.params("id"));
		Administrador admin =  (Administrador) repositorio_usuarios.buscarUsuario(id);
		model.put("admin", admin);
		return new ModelAndView(model, "modalEstado.hbs");
	}
	
*/
	
	public ModelAndView crearDispositivo(Request request, Response response) {
		
		return new ModelAndView(null, "dispositivoDetalle.hbs");
		
	}
	
	public ModelAndView crear(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		model.put("dispositivo", "Nuevo dispositivo");
		return new ModelAndView(model, "dispositivoDetalle.hbs");
	}
	
	public ModelAndView guardar(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		DispositivoDetalle nuevoDispositivo = new DispositivoDetalle();
		nuevoDispositivo.setNombre(request.queryParams("nombre"));
		nuevoDispositivo.setDescripcion(request.queryParams("descripcion"));
		nuevoDispositivo.setHsMensualMinimo(Integer.parseInt(request.queryParams("hsMensualMinimo")));
		nuevoDispositivo.setHsMensualMaximo(Integer.parseInt(request.queryParams("hsMensualMaximo")));
		nuevoDispositivo.setConsumoKwHora(Float.parseFloat(request.queryParams("ConsumoKwHora")));
		nuevoDispositivo.setEsEsencial(Boolean.parseBoolean(request.queryParams("esEsencial")));
		nuevoDispositivo.setEsInteligente(Boolean.parseBoolean(request.queryParams("esInteligente")));
		
		model.put("dispositivos", 4);
		return new ModelAndView(model, "dispositivoDetalle.hbs");
	}
	
	
	public LocalDateTime parsFecha( String fecha ){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return LocalDateTime.parse(fecha, formatter);


	}

	
	
	
	private static Map<String, String> toMap(List<NameValuePair> pairs){
		Map<String, String> map = new HashMap<>();
		for(int i=0; i<pairs.size(); i++){
			NameValuePair pair = pairs.get(i);
			map.put(pair.getName(), pair.getValue());
			
			}
		
		return map;
		
		}
	
}
