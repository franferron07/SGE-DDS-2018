package controllers;


import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;


import dispositivos.DispositivoInteligente;
import entities.Sensor;
import models.ModelHelper;

import reglasYActuadores.ActuadorString;
import reglasYActuadores.CondicionRegla;
import reglasYActuadores.Regla;
import reglasYActuadores.ReglaSimple;
import repositorios.RepositorioActuadoresString;
import repositorios.RepositorioRegla;
import repositorios.RepositorioSensor;
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
		
		model.put("reglas", RepositorioRegla.filtrarReglsaCliente(id) );
		return new ModelAndView(model, "reglas.hbs");
	}
	
	
	public ModelAndView crear(Request request, Response response) {
		
		int id = request.session().attribute("id");
		
		Map<String, Object> model=new HashMap<>();
		
		model.put("actuadores", RepositorioActuadoresString.getActuadores());
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("dispositivos" , cliente.filtrarDispositivosInteligentes() );
		
		return new ModelAndView(model, "reglaCrear.hbs");
	}
	
	public ModelAndView guardar(Request request, Response response) {
		
		Map<String, Object> model=new HashMap<>();			

		ModelHelper modelHelper = new ModelHelper();
		int id = request.session().attribute("id");
		
		//recibo params
		String nombreRegla =request.queryParams("nombre");
		String condiciones_json =request.queryParams("export");
		
		String dispositivos_string = request.queryParams("dispositivos_string");
		String[] dispos = dispositivos_string.split(",");
		
		String actuadores_string = request.queryParams("actuadores_string");
		String[] acts = actuadores_string.split(",");
		
		int tipo = Integer.parseInt(request.queryParams("tipo"));  //0 simple , 1 compuesta
		
		//parseo condiciones
		Gson gson = new Gson();
		List<CondicionRegla> condiciones = gson.fromJson(condiciones_json, new TypeToken<List<CondicionRegla>>(){}.getType());
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		
		// REGLA SIMPLE
		if( tipo == 0 ){
			
			ReglaSimple regla = new ReglaSimple(nombreRegla);
			regla.agregarCondiciones(condiciones);

			// agrego actuadores en reglas
			for (String a: acts) {           
				
				ActuadorString actuador = RepositorioActuadoresString.buscarActuadorEnum( Integer.parseInt(a) );
				regla.agregarActuadores_string(actuador);
		    }
			
			//agrego dispos en reglas
			for (String d: dispos) {           
				
				int id_dispo = Integer.parseInt(d);
				
				DispositivoInteligente inteligente = (DispositivoInteligente) RepositorioUsuarios.buscarDispositivo(cliente, id_dispo);
				regla.agregarDispositivo(inteligente);
		    }
			
			modelHelper.agregar(regla);
			Sensor sensor = RepositorioSensor.sensores.get(0);
			sensor.agregarObservador(regla);
			
			
			RepositorioRegla.agregarRegla(regla);
		}
		else{
			// FALTA DEFINIR 
			
		}
		
		//model.put("reglas", RepositorioRegla.getReglas());
		response.redirect("/sge/cliente/reglas");
		return new ModelAndView(model , "reglas.hbs");
	}
	
	public ModelAndView modificar(Request request, Response response) {
		
		Map<String, Object> model=new HashMap<>();
		
		int id = request.session().attribute("id");
		
		int id_regla = Integer.parseInt(request.params("id"));
		
		Regla regla = RepositorioRegla.buscarRegla(id_regla);
		
		model.put("regla", regla);
		
		model.put("actuadores", RepositorioActuadoresString.getActuadores());
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("dispositivos" , cliente.filtrarDispositivosInteligentes() );
		
		return new ModelAndView(model, "modalRegla.hbs");
	}
	
	public ModelAndView update(Request request, Response response) {
		
		Map<String, Object> model=new HashMap<>();
		int id = request.session().attribute("id");
		
		return new ModelAndView(null, "reglas.hbs");
	}
	
	public ModelAndView eliminar(Request request, Response response) {

		Map<String, Object> model=new HashMap<>();
		int id_regla = Integer.parseInt(request.params("id"));

		//// modifico regla en db y lo guardo
		Regla regla = (Regla) RepositorioRegla.buscarRegla(id_regla);
		regla.desactivar();		
		ModelHelper modelHelper = new ModelHelper();
		modelHelper.modificar(regla);
		//RepositorioRegla.quitarRegla(regla);
		
		model.put("reglas", RepositorioRegla.getReglas());
		return new ModelAndView(model, "reglas.hbs");
	}
	

}
