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
import usuarios.Administrador;
import usuarios.Cliente;
import dispositivos.DispositivoDetalle;

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

        String desde = params.get("desde");
        String hasta = params.get("hasta");
        String reporte = params.get("reporte");
        
        System.out.println(desde);
        System.out.println(hasta);
        System.out.println(reporte);
        
		return "";
	}
	
/*
	public ModelAndView mostrar(Request request, Response response, boolean permiteEdicion) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		Usuario usuario = this.buscarUsuario(id);
		model.put("usuario", usuario);
		model.put("permiteEdicion", permiteEdicion);
		return new ModelAndView(model, "modalUsuario.hbs");
}

	public ModelAndView reporte(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		int id = Integer.parseInt(request.params("id"));
		Administrador admin =  (Administrador) repo.buscarUsuario(id);
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

	
	
	
	   private static Map<String, String> toMap(List<NameValuePair> pairs){
	        Map<String, String> map = new HashMap<>();
	        for(int i=0; i<pairs.size(); i++){
	            NameValuePair pair = pairs.get(i);
	            map.put(pair.getName(), pair.getValue());
	        }
	        return map;
	    }
}
