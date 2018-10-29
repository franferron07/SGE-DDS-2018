package controllers;

import java.util.HashMap;
import java.util.Map;

import dispositivos.DispositivoUsuario;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class DispositivoUsuarioController {

	
	public RepositorioUsuarios repo;
	
	public DispositivoUsuarioController(){
		
		repo = new RepositorioUsuarios();
	}
	
	
	public ModelAndView dispositivos(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();	
		int id = request.session().attribute("id");
		
		Cliente cliente = (Cliente) repo.buscarUsuario(id);
		model.put("dispositivos", cliente.getDispositivos());
		return new ModelAndView(model, "dispositivos.hbs");
	}
	
	public ModelAndView ver(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		DispositivoUsuario dispositivo = this.buscarDispositivo(id);
		model.put("dispositivo", dispositivo);
		return new ModelAndView(model, "modalDispositivo.hbs");
	}
	
	
	public ModelAndView crear(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		model.put("dispositivos", 4);
		return new ModelAndView(model, "dispositivoCrear.hbs");
	}
	
	public ModelAndView guardar(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		model.put("dispositivos", 4);
		return new ModelAndView(model, "dispositivoCrear.hbs");
	}
	
	
	public ModelAndView modificar(Request request, Response response) {
		
		return this.mostrar(request, response, true);
	}
	
	public ModelAndView mostrar(Request request, Response response, boolean permiteEdicion) {
		Map<String, Object> model=new HashMap<>();
		int id_disp = Integer.parseInt(request.params("id"));
		
		int id = request.session().attribute("id");
		Cliente cliente = (Cliente) repo.buscarUsuario(id);

		DispositivoUsuario disp = repo.buscarDispositivo( cliente , id_disp );
		model.put("dispositivo", disp);
		model.put("permiteEdicion", permiteEdicion);
		return new ModelAndView(model, "modalDispositivo.hbs");
	}
	
	
	
	public ModelAndView update(Request request, Response response) {
		
		return new ModelAndView(null, "modalDispositivos.hbs");
	}
	

	private DispositivoUsuario buscarDispositivo(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
