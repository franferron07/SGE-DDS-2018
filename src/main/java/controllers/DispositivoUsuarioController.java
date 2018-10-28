package controllers;

import java.util.HashMap;
import java.util.Map;

import dispositivos.DispositivoUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class DispositivoUsuarioController {

	
	
	
	public ModelAndView dispositivos(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		Cliente cliente = this.buscarUsuario(id);
		model.put("cliente", cliente);
		return new ModelAndView(model, "modalDispositivos.hbs");
	}
	
	public ModelAndView ver(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		DispositivoUsuario dispositivo = this.buscarDispositivo(id);
		model.put("dispositivo", dispositivo);
		return new ModelAndView(model, "modalDispositivo.hbs");
	}
	
	
	public ModelAndView modificar(Request request, Response response) {
		
		return new ModelAndView(null, "modalDispositivos.hbs");
	}
	
	public ModelAndView update(Request request, Response response) {
		
		return new ModelAndView(null, "modalDispositivos.hbs");
	}
	

	private DispositivoUsuario buscarDispositivo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private Cliente buscarUsuario(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
