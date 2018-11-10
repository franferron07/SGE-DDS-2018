package controllers;

import java.util.HashMap;
import java.util.Map;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import repositorios.RepositorioDispositivosLista;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class DispositivoUsuarioController {

	//public RepositorioDispositivosLista dispositivos;
	
	public DispositivoUsuarioController(){
		
		//dispositivos = new RepositorioDispositivosLista();
	}
	
	
	//listamos dispositivos
	public ModelAndView dispositivos(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();	
		int id = request.session().attribute("id");
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("dispositivos", cliente.getDispositivos());
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
		String tipo = request.queryParams("tipo");
		
		DispositivoDetalle detalle = RepositorioDispositivosLista.buscarDispositivoDetalle(id_detalle);
		

		if(tipo == "estandar"){
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
		
		return new ModelAndView(null, "modalDispositivos.hbs");
	}

	
}
