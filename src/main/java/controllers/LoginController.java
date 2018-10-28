package controllers;

import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Usuario;

public class LoginController {
	
	
	public ModelAndView ver(Request request, Response response) {

		return new ModelAndView(null, "login.hbs");
	}
	
	
	public ModelAndView loguear(Request request, Response response) {
		
		RepositorioUsuarios repo = new RepositorioUsuarios();
		
		
		Map<String, Object> model=new HashMap<>();
		String username = request.queryParams("usuario");
		String password= request.queryParams("password");
		String mensaje = null;
		Usuario usuario = repo.buscarUsuario(username);
		
		if( usuario == null ){
			mensaje="El nombre de usuario es incorrecto";
		}
		
		if( password == usuario.getPassword() ){
		
			//login correcto verifico si es cliente o admin.
			mensaje= "Inicio de sesión correcto";
			request.session(true);                     // create and return session
			request.session().attribute("user");       // Get session attribute 'user'
			request.session().attribute("user","foo"); 
			
		}
		else{
			mensaje= "El password es incorrecto";
		}
		
		model.put("mensaje", mensaje);
		
		return new ModelAndView(model, "login.hbs");
	}
	
	

}
