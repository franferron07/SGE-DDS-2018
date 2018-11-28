package controllers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Utils.HibernateProxyTypeAdapter;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import models.ModelHelper;
import models.UsuarioModel;
import reglasYActuadores.ActuadoresEnum;
import reglasYActuadores.CondicionRegla;
import reglasYActuadores.Regla;
import reglasYActuadores.ReglaSimple;
import repositorios.RepositorioRegla;
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
		
		model.put("reglas", RepositorioRegla.getReglas());
		return new ModelAndView(model, "reglas.hbs");
	}
	
	
	public ModelAndView crear(Request request, Response response) {
		
		int id = request.session().attribute("id");
		
		Map<String, Object> model=new HashMap<>();
		
		List<Enum> enumValues = Arrays.asList(ActuadoresEnum.values());
		model.put("actuadores", enumValues);
		
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
		//String actuadores =  request.queryParams("actuadores");
		int id_dispositivo = Integer.parseInt(request.queryParams("dispositivos"));
		
		String actuadores_string = request.queryParams("actuadores_string");
		String[] acts = actuadores_string.split(",");
		
		
		
		
		// Busco Dispositivo inteligente 
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
        DispositivoInteligente inteligente = (DispositivoInteligente) RepositorioUsuarios.buscarDispositivo(cliente, id_dispositivo);
        
		int tipo = Integer.parseInt(request.queryParams("tipo"));  //0 simple , 1 compuesta
		
		Gson gson = new Gson();
		List<CondicionRegla> condiciones = gson.fromJson(condiciones_json, new TypeToken<List<CondicionRegla>>(){}.getType());
		
		
		// REGLA SIMPLE
		if( tipo == 0 ){
			
			ReglaSimple regla = new ReglaSimple(nombreRegla);
			
			regla.agregarCondiciones(condiciones);
			regla.agregarDispositivo( inteligente );
			
			// agrego actuadores en reglas
			for (String a: acts) {           
				
				ActuadoresEnum actuador = ActuadoresEnum.valueOf(a);
				actuador.setValorEnum(a);
				regla.agregarActuadorEnum(actuador);
				
		    }
			
			/*ActuadoresEnum actuador = ActuadoresEnum.valueOf(actuadores);
			actuador.setValorEnum(actuadores);
			regla.agregarActuadorEnum(actuador);*/
			
			modelHelper.agregar(regla);
			
			RepositorioRegla.agregarRegla(regla);
		}
		else{
			// FALTA DEFINIR 
			
		}
		
		model.put("reglas", RepositorioRegla.getReglas());
		//System.out.println( condiciones.size()+"******************" );
		
	
		
		
		
		
		
		
		
		return new ModelAndView(model , "reglas.hbs");
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

		//int id = request.session().attribute("id");
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
