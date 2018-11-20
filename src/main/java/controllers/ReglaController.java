package controllers;

import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class ReglaController {
	
	
	public ReglaController(){
		
	}
	
	//listamos reglas
	public ModelAndView reglas(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();	
		int id = request.session().attribute("id");
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("reglas", cliente.getDispositivos());
		return new ModelAndView(model, "reglas.hbs");
	}
	
	
	public ModelAndView crear(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		return new ModelAndView(model, "reglaCrear.hbs");
	}
	
	public ModelAndView guardar(Request request, Response response) {
		int id = request.session().attribute("id");
		
		return new ModelAndView(null, "reglas.hbs");
	}
	
	public ModelAndView modificar(Request request, Response response) {
		
		return new ModelAndView(null, "modalRegla.hbs");
	}
	
	public ModelAndView update(Request request, Response response) {
		
		Map<String, Object> model=new HashMap<>();
		int id = request.session().attribute("id");
		
		return new ModelAndView(null, "reglas.hbs");
	}
	
	public ModelAndView eliminar(Request request, Response response) {

		Map<String, Object> model=new HashMap<>();
		int id = request.session().attribute("id");
		
		return new ModelAndView(null, "reglas.hbs");
	}
	

}
