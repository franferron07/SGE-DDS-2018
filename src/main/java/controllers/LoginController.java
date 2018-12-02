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
	
	public ModelAndView home(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		Integer id = request.session().attribute("id");
		String salida = "";
		
		Usuario usuario = RepositorioUsuarios.buscarUsuario(id);

		if( usuario.getClass() == Cliente.class ){
			Cliente cli = (Cliente) usuario ;
			salida="inicioCliente.hbs";
		}
		else{
			salida="inicioAdministrador.hbs";
		}
		model.put("usuario", usuario);
		return new ModelAndView(model, salida);
		
	}
	
	public ModelAndView loguear(Request request, Response response) {
		
		Map<String, Object> model=new HashMap<>();
		String username = request.queryParams("usuario");
		String password= request.queryParams("password");
		String mensaje = null;
		Usuario usuario = RepositorioUsuarios.buscarUsuario(username);
		
		if( usuario == null ){
			mensaje="El nombre de usuario es incorrecto";
			model.put("mensaje", mensaje);
			return new ModelAndView(model, "login.hbs");
		}
		
		
		if(  password.equals( usuario.getPassword()  ) ){
			//login correcto verifico si es cliente o admin
			
			String salida="";
			if( usuario.getClass() == Cliente.class ){
				Cliente cli = (Cliente) usuario ;
				salida="inicioCliente.hbs";
			}
			else{
				salida="inicioAdministrador.hbs";
			}
			
			model.put("usuario", usuario);
			
			request.session(true);              
			request.session().attribute("id",usuario.getId());
			
			return new ModelAndView(model, salida);
			
		}
		else{
			mensaje= "El password es incorrecto";
			model.put("mensaje", mensaje);
			return new ModelAndView(model, "login.hbs");
		}
		
		
		
		
	}
	
	

}
