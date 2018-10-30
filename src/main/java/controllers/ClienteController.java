package controllers;

import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class ClienteController {
	
	public RepositorioUsuarios repo;
	
	public ClienteController(){
		repo = new RepositorioUsuarios();
	}
	
	public ModelAndView ver(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		int id = request.session().attribute("id");
		Cliente cliente = (Cliente) repo.buscarUsuario(id);
		model.put("cliente", cliente);
		return new ModelAndView(model, "cliente.hbs");
	}

	public ModelAndView estado(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		Cliente cliente =  (Cliente) repo.buscarUsuario(id);
		model.put("cliente", cliente);
		return new ModelAndView(model, "cliente.hbs");
	}
	
	public ModelAndView consumoPeriodo(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		//Cliente cliente = this.buscarUsuario(id);
		//model.put("cliente", cliente);
		return new ModelAndView(model, "modalConsumo.hbs");
	}
	
	

	
	

}
