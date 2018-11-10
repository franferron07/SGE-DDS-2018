package controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class ClienteController {
	
	public ClienteController(){
		
	}
	
	public ModelAndView ver(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		int id = request.session().attribute("id");
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("cliente", cliente);
		return new ModelAndView(model, "cliente.hbs");
	}

	public ModelAndView estado(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		int id = request.session().attribute("id");
		Cliente cliente =  (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("cliente", cliente);
		return new ModelAndView(model, "cliente.hbs");
	}
	
	public ModelAndView consumoPeriodo(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();

		int id = request.session().attribute("id");
		//Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);

		model.put("total_consumo", 0);
		model.put("es_consulta", false);
		return new ModelAndView(model, "consumo.hbs");
	}
	
	
	public ModelAndView consumoPeriodoResultado(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();

		int id = request.session().attribute("id");
		
		String desde = request.params("desde");
		String hasta = request.params("hasta");
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		
		LocalDateTime fecha_desde = cliente.parsearFecha(desde);
		LocalDateTime fecha_hasta = cliente.parsearFecha(hasta);
		
		/* Calculo el consumo */
		double total_consumo = cliente.consumoEnUnPeriodo(fecha_desde, fecha_hasta  );
		
		
		model.put("total_consumo", total_consumo);
		model.put("es_consulta", true);
		return new ModelAndView(model, "consumo.hbs");
	}
	
	

}
