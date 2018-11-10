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
		
		Map<String, Object> model=new HashMap<>();
		String username = request.queryParams("usuario");
		String password= request.queryParams("password");
		String mensaje = null;
		Usuario usuario = RepositorioUsuarios.buscarUsuario(username);
		
		if( usuario == null ){
			mensaje="El nombre de usuario es incorrecto";
			return new ModelAndView(model, "login.hbs");
		}
		
		
		if(  password.equals( usuario.getPassword()  ) ){
			//login correcto verifico si es cliente o admin
			
			int validador= 0;
			String salida="";
			if( usuario.getClass() == Cliente.class ){
				Cliente cli = (Cliente) usuario ;
				validador= 1;
				salida="inicioCliente.hbs";
			}
			else{
				validador= 0;
				salida="inicioAdministrador.hbs";
			}
			
			model.put("usuario", usuario);
			
			request.session(true);              
			request.session().attribute("id",usuario.getId());
			request.session().attribute("tipo",validador); //tipo a cliente que recibe el layout. 1 es cliente . 0 Administrador
			return new ModelAndView(model, salida);
			
		}
		else{
			mensaje= "El password es incorrecto";
			model.put("mensaje", mensaje);
			return new ModelAndView(model, "login.hbs");
		}
		
		
		
		
	}
	
	

}
