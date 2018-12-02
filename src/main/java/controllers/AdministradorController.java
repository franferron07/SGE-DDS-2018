package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import repositorios.RepositorioDispositivosLista;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Usuario;
import usuarios.Administrador;
import usuarios.Cliente;
import Utils.ConsumoHogar;
import Utils.ConsumoTipoDispositivo;
import Utils.ConsumoTransformador;
import Utils.TrafoJson;
import dispositivos.Consumo;
import dispositivos.DispositivoDetalle;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Transformador;
import models.ModelHelper;
import models.TransformadorModel;

public class AdministradorController {
	


	public RepositorioDispositivosLista repositorio_dispositivos;
	
	public AdministradorController(){

	}
	
	public ModelAndView reportes (Request request,Response response) {
		Map<String, Object> model=new HashMap<>();
		
		return new ModelAndView(model, "reportes.hbs");
	}
	
	
	
	public ModelAndView reportes_results(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		
		String id = Integer.toString(request.session().attribute("id"));
		
		List<NameValuePair> pairs = URLEncodedUtils.parse(request.body(), Charset.defaultCharset());

        Map<String, String> params = toMap(pairs);
        
        String desde = params.get("desde");
        String hasta = params.get("hasta");
        String reporte = params.get("reporte");
        
        System.out.println(request.body());
        
        LocalDateTime fechaParse_desde = this.parsFecha(desde);
        LocalDateTime fechaParse_hasta = this.parsFecha(hasta);
        
        switch(reporte) {
        
        
        	case "hogar":
		        String queryConsumoPorHogar = "SELECT \n" + 
		        		" 	 u.id as id,\n" +
		        		"    u.nombre as nombre,\n" + 
		        		"    u.apellido as apellido,\n" + 
		        		"    u.domicilio as domicilio,\n" + 
		        		"    IFNULL(SUM(c.consumo), 0) AS consumo_inteligentes,\n" + 
		        		"    IFNULL(SUM(DATEDIFF('"+ hasta +"', '" + desde + "') * de.consumoKwHora * 24), 0 ) AS consumo_estandar,\n" + 
		        		"    IFNULL(SUM(DATEDIFF('"+ hasta +"', '" + desde + "') * de.consumoKwHora * 24), 0 )  + IFNULL(SUM(c.consumo), 0) AS consumo_total \n" + 
		        		"FROM\n" + 
		        		"    usuario u\n" + 
		        		"        LEFT JOIN\n" + 
		        		"    dispositivo d ON d.cliente_id = u.id\n" + 
		        		"        LEFT JOIN\n" + 
		        		"    dispositivoDetalle de ON de.id = d.dispositivoDetalle_id\n" + 
		        		"        LEFT JOIN\n" + 
		        		"    DispositivoInteligente di ON di.id = d.id\n" + 
		        		"        LEFT JOIN\n" + 
		        		"    modo m ON m.dispositivo_inteligente_id = di.id\n" + 
		        		"        LEFT JOIN\n" + 
		        		"    consumo c ON c.modo_id = m.id\n" + 
		        		"WHERE\n" + 
		        		"    u.tipo_usuario = 'cliente'\n" + 
		        		"        AND c.fechaInicio >= '" + desde + "'\n" + 
		        		"        AND c.fechaFin <= '" + hasta + "'\n" + 
		        		"GROUP BY 1 , 2 , 3;";
		        
		        						   
		        javax.persistence.Query queryA = ModelHelper.getEntityManager().createNativeQuery(queryConsumoPorHogar, ConsumoHogar.class);
				List<ConsumoHogar> consumoHogares = queryA.getResultList();
				
				for (ConsumoHogar element : consumoHogares) {
				    System.out.println(element.id);
				    System.out.println(element.nombre);
				}
				
				model.put("consumo", consumoHogares);
				return new ModelAndView(model, "resultadoReporteHogar.hbs");

			
        	case "dispositivo":
										 
				/*
				SELECT IFNULL(AVG(c.consumo), 0) AS consumo_inteligentes, IFNULL(AVG(DATEDIFF(" + hasta + ", " + desde + ") * de.horasPorDia), 0) as consumo_estandar
				FROM dispositivo d
				JOIN dispositivointeligente di ON di.id = d.id
				JOIN modo m ON m.dispositivo_inteligente_id = d.id
				JOIN consumo c ON c.modo_id = m.id
	            JOIN dispositivoestandar de ON de.id = d.id
	            GROUP BY d.tipo
			    WHERE c.fechaInicio >= + desde +
			    AND c.fechaFin <= + hasta +
				*/
		        							 
		        
		        String queryConsumoPromedioDI = "SELECT IFNULL(AVG(c.consumo), 0) AS consumo_inteligentes"+
		        										 " FROM dispositivo d"+
		        										 " LEFT JOIN DispositivoInteligente di ON di.id = d.id"+
		        										 " LEFT JOIN modo m ON m.dispositivo_inteligente_id = d.id"+
		        										 " LEFT JOIN consumo c ON c.modo_id = m.id"+
		        										 " WHERE c.fechaInicio >= '"+ desde +
		        										 "' AND c.fechaFin <= '" + hasta+"'";
		        										 
		        String queryConsumoPromedioDE = "SELECT IFNULL(AVG(DATEDIFF('" + hasta + "', '" + desde + "') * de.horasPorDia), 0) AS consumo_estandar"+
		        										 " FROM dispositivo d"+
		        										 " LEFT JOIN DispositivoEstandar de ON de.id = d.id";								 
		        
		        
		        
		        javax.persistence.Query queryB = ModelHelper.getEntityManager().createNativeQuery(queryConsumoPromedioDI);
		        
		        javax.persistence.Query queryC = ModelHelper.getEntityManager().createNativeQuery(queryConsumoPromedioDE);
		        
				List<Double> resultBList = queryB.getResultList();
		        double resultadoInteligentes = resultBList.get(0);
				
		        List<Double> resultCList = queryC.getResultList();
		        double resultadoEstandares = resultCList.get(0);
				
				
				model.put("resultadoInteligentes", resultadoInteligentes);
				model.put("resultadoEstandares", resultadoEstandares);
				
				System.out.println("****************************************");
				System.out.println("resultaldo inteligente" + resultadoInteligentes);
				System.out.println("resultado estandar" + resultadoEstandares);
				
				return new ModelAndView(model, "resultadoReporteTipoDispositivo.hbs");
			
			
        	case "transformador":
			
				TransformadorModel transformadorModel = new TransformadorModel();
				List<Transformador> transformadores = transformadorModel.buscarTodosLosTransformadores();
				List<ConsumoTransformador> consumoTransformadores = new ArrayList<ConsumoTransformador>();
				
				for (Transformador transformador : transformadores) {
					
					consumoTransformadores.add(new ConsumoTransformador(transformador.getId(), transformador.getUbicacion().getLongitud(), transformador.getUbicacion().getLatitud(), transformador.consumoTotal(fechaParse_desde, fechaParse_hasta)));
				}	
		        
				
				model.put("consumoTransformadores", consumoTransformadores);
				return new ModelAndView(model, "resultadoReporteTransformador.hbs");

        }        								 
	
               
        //String desde = params.get("desde");
        //String hasta = params.get("hasta");
        //String reporte = params.get("reporte");
        
        System.out.println(desde);
        System.out.println(hasta);
        System.out.println(reporte);
        
		return null;
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
	public ModelAndView mostrarDispositivos(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();

		model.put("dispositivosDetalle", new ModelHelper().buscarTodos(DispositivoDetalle.class));
		return new ModelAndView(model, "dispositivosDetalle.hbs");
		
	}
	
	
	public ModelAndView crearDispositivo(Request request, Response response) {
		
		return new ModelAndView(null, "dispositivoDetalle.hbs");
		
	}
	
	
	public ModelAndView guardar(Request request, Response response) {
		
		List<NameValuePair> pairs = URLEncodedUtils.parse(request.body(), Charset.defaultCharset());

        Map<String, String> params = toMap(pairs);
        
        System.out.println(params.get("nombre"));
		
		System.out.println(request.body());
		
		Map<String, Object> model=new HashMap<>();
		
		String id = Integer.toString(request.session().attribute("id"));
		
		DispositivoDetalle nuevoDispositivo = new DispositivoDetalle();
		nuevoDispositivo.setNombre(params.get("nombre"));
		nuevoDispositivo.setDescripcion(params.get("descripcion"));
		nuevoDispositivo.setHsMensualMinimo(Integer.parseInt(params.get("hsMensualMinimo")));
		nuevoDispositivo.setHsMensualMaximo(Integer.parseInt(params.get("hsMensualMaximo")));
		nuevoDispositivo.setConsumoKwHora(Float.parseFloat(params.get("consumoKwHora")));
		nuevoDispositivo.setEsEsencial(Boolean.parseBoolean(params.get("esEsencial")));
		nuevoDispositivo.setEsInteligente(Boolean.parseBoolean(params.get("esInteligente")));
		nuevoDispositivo.setEsBajoConsumo(Boolean.parseBoolean(params.get("consumo")));
		
		repositorio_dispositivos.agregarDipositivoDetalle(nuevoDispositivo);
		
		System.out.println(request.queryParams("nombre"));
		
		model.put("dispositivosDetalle", new ModelHelper().buscarTodos(DispositivoDetalle.class));
		return new ModelAndView(model, "dispositivosDetalle.hbs");
	}
	
	
	public LocalDateTime parsFecha( String fecha ){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
