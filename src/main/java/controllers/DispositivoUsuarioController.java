package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import models.ModelHelper;
import models.UsuarioModel;
import repositorios.RepositorioDispositivosLista;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class DispositivoUsuarioController {

	
	public DispositivoUsuarioController(){
		
	}
	
	
	//listamos dispositivos
	public ModelAndView dispositivos(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();	
		int id = request.session().attribute("id");
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		List<DispositivoUsuario> dispositivos = cliente.getDispositivos();
		
		
		for (DispositivoUsuario dispositivo : dispositivos) {
			System.out.println(dispositivo.getId());
			System.out.println(dispositivo instanceof DispositivoInteligente);
			dispositivo.setEsAdaptado(dispositivo instanceof DispositivoInteligente);
		}
		model.put("dispositivos", dispositivos);
		return new ModelAndView(model, "dispositivos.hbs");
	}
	
	
	public ModelAndView ver(Request request, Response response) {
		
		return null;
	}
	
	
	public ModelAndView crear(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		
		model.put("dispositivos", RepositorioDispositivosLista.getDispositivosLista());
		
		return new ModelAndView(model, "dispositivoCrear.hbs");
	}
	
	
	public ModelAndView guardar(Request request, Response response) {
		int id = request.session().attribute("id");
		int id_detalle =Integer.parseInt(request.queryParams("dispositivo_detalle"));
		int tipo = Integer.parseInt(request.queryParams("tipo"));  //0 estandar , 1 inteligente
		
		DispositivoDetalle detalle = RepositorioDispositivosLista.buscarDispositivoDetalle(id_detalle);
		

		if( tipo == 0){
			DispositivoEstandar estandar = new DispositivoEstandar(detalle);
			estandar.setHorasPorDia(Float.parseFloat(request.queryParams("horas") ) );
			
			RepositorioUsuarios.agregar_dispositivo_usuario( id , estandar );
		}
		else
		{
			DispositivoInteligente inteligente = new DispositivoInteligente(detalle);
			RepositorioUsuarios.agregar_dispositivo_usuario( id , inteligente );
		}
		
		Map<String, Object> model=new HashMap<>();	
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("dispositivos", cliente.getDispositivos());
		return new ModelAndView(model, "dispositivos.hbs");
	}
	
	
	public ModelAndView modificar(Request request, Response response) {
		
		return this.mostrar(request, response, true);
	}
	
	
	public ModelAndView mostrar(Request request, Response response, boolean permiteEdicion) {
		Map<String, Object> model=new HashMap<>();
		int id_disp = Integer.parseInt(request.params("id"));
		
		int id = request.session().attribute("id");
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);

		DispositivoUsuario dispositivo = RepositorioUsuarios.buscarDispositivo( cliente , id_disp );
		
		model.put("permiteEdicion", permiteEdicion);
		
		/* Chequeo si es inteligente o estandar para verificar cual modal mostrar. */
		if( dispositivo.getClass() == DispositivoInteligente.class ){
			DispositivoInteligente di_inteligente = (DispositivoInteligente) dispositivo ;
			
			model.put("dispositivo", di_inteligente);
			return new ModelAndView(model, "modalDispositivoInteligente.hbs");
		}
		else{
			DispositivoEstandar di_estandar = (DispositivoEstandar) dispositivo ;
			
			model.put("dispositivo", di_estandar);
			return new ModelAndView(model, "modalDispositivoEstandar.hbs");
		}
		
	}
	
	
	
	public ModelAndView update(Request request, Response response) {
		
		Map<String, Object> model=new HashMap<>();
		
		int id_disp = Integer.parseInt(request.params("id"));
		int id = request.session().attribute("id");
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);

		DispositivoUsuario dispositivo = RepositorioUsuarios.buscarDispositivo( cliente , id_disp );
		
		ModelHelper helper = new ModelHelper();
		
		if( dispositivo.getClass() == DispositivoInteligente.class ){
		//SI ES INTELIGENTE
			DispositivoInteligente di_inteligente = (DispositivoInteligente) dispositivo ;
			
			int adaptador =Integer.parseInt( request.queryParams("adaptador"));
			int regla_id =Integer.parseInt( request.queryParams("regla"));
			
			//QUITO ADAPTADOR
			if( adaptador == 1 ){
				
				DispositivoEstandar adapt = di_inteligente.getEstandar();
				cliente.quitarAdaptadorEnDispositivoEstandar(di_inteligente  );
				helper.modificar(adapt);
			}
			
			helper.modificar(di_inteligente);
		}
		else{			
		//SI ES ESTANDAR
			DispositivoEstandar di_estandar = (DispositivoEstandar) dispositivo ;
			di_estandar.setHorasPorDia(Float.parseFloat( request.queryParams("horas")) ) ; 
			
			int adaptador =Integer.parseInt( request.queryParams("adaptador"));
			//CONVIERTO ESTANDAR A INTELIGENTE
			if( adaptador ==1 ){
				
				cliente.convertirEstandarInteligente(di_estandar);
				
				
				helper.modificar(dispositivo);
				//helper.modificar(cliente);				
			}

		}

		response.redirect("/sge/cliente/dispositivos");
		//model.put("dispositivos", cliente.getDispositivos());
		return new ModelAndView(model, "dispositivos.hbs");
	}
	
	
	public ModelAndView eliminar(Request request, Response response) {

		Map<String, Object> model=new HashMap<>();
		int id_disp = Integer.parseInt(request.params("id"));
		
		int id = request.session().attribute("id");
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		
		//// modifico disposisitivo en la db y lo borro de la lista 
		DispositivoUsuario dispositivo = RepositorioUsuarios.buscarDispositivo( cliente , id_disp );
		dispositivo.desactivar();		
		UsuarioModel modelusuario = new UsuarioModel();
		modelusuario.modificar(dispositivo);
		cliente.quitarDispositivo(dispositivo);
		
		response.redirect("/sge/cliente/dispositivos");
		model.put("dispositivos", cliente.getDispositivos());
		return new ModelAndView(model, "dispositivos.hbs");
	}
	

}
