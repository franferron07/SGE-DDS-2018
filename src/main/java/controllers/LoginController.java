package controllers;

import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;
import usuarios.Usuario;

public class LoginController {
	

	
	public ModelAndView ver(Request request, Response response) {

		return new ModelAndView(null, "login.hbs");
	}
	
	public ModelAndView logout(Request req, Response res){
		
		req.session().removeAttribute("id");
		/*res.redirect("/login");
		return null;*/
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
			return new ModelAndView(model, "login.hbs");
		}
		
		if(  password.equals( usuario.getPassword()  ) ){
		
			//login correcto verifico si es cliente o admin.
			mensaje= "Inicio de sesión correcto";
			request.session(true);              
			request.session().attribute("id",usuario.getId());
			request.session().attribute("tipo",1); //tipo a cliente que recibe el layout. 1 es cliente . 0 Administrador
			return new ModelAndView(null, "inicio.hbs");
		}
		else{
			mensaje= "El password es incorrecto";
			model.put("mensaje", mensaje);
			return new ModelAndView(model, "login.hbs");
		}
		
		
		
		
	}
	
	

}
