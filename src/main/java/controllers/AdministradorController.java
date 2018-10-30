package controllers;

import java.util.HashMap;
import java.util.Map;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import repositorios.RepositorioUsuarios;
import server.SessionUtils;
import repositorios.RepositorioClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Administrador;
import usuarios.Cliente;
import dispositivos.DispositivoDetalle;

public class AdministradorController {
	
	public RepositorioUsuarios repositorio_usuarios;
	public RepositorioClientes repositorio_clientes;
	
	public AdministradorController(){
		repositorio_usuarios = new RepositorioUsuarios();
	}
	
	public ModelAndView reportes (Request request,Response response) {
		Map<String, Object> model=new HashMap<>();
		
		return new ModelAndView(model, "reportes.hbs");
	}
	
	public ModelAndView postReporte (Request request,Response response) {
		Map<String, Object> model=new HashMap<>();
		
		response = SessionUtils.checkSessionAndRedirect(request, response);
		
		return new ModelAndView(model, "reportes.hbs");
	}
	
	
	public ModelAndView verHogares(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		LocalDateTime desde = LocalDateTime.parse(request.queryParams("fechaDesde"), formatter);
		LocalDateTime hasta = LocalDateTime.parse(request.queryParams("fechaHasta"), formatter);
		
		Cliente cliente = new Cliente();
		
		int id = request.session().attribute("id");
		Administrador admin = (Administrador) repositorio_usuarios.buscarUsuario(id);
		model.put("admin", admin);
		return new ModelAndView(model, "administrador.hbs");
	}
	
/*
	public ModelAndView mostrar(Request request, Response response, boolean permiteEdicion) {
		Map<String, Object> model=new HashMap<>();
		int id = Integer.parseInt(request.params("id"));
		Usuario usuario = this.buscarUsuario(id);
		model.put("usuario", usuario);
		model.put("permiteEdicion", permiteEdicion);
		return new ModelAndView(model, "modalUsuario.hbs");
}

	public ModelAndView reporte(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		int id = Integer.parseInt(request.params("id"));
		Administrador admin =  (Administrador) repo.buscarUsuario(id);
		model.put("admin", admin);
		return new ModelAndView(model, "modalEstado.hbs");
	}
	
*/
	
	public ModelAndView crearDispositivo(Request request, Response response) {
		
		return new ModelAndView(null, "dispositivoDetalle.hbs");
		
	}
	
	public ModelAndView crear(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		model.put("dispositivo", "Nuevo dispositivo");
		return new ModelAndView(model, "dispositivoDetalle.hbs");
	}
	
	public ModelAndView guardar(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		DispositivoDetalle nuevoDispositivo = new DispositivoDetalle();
		nuevoDispositivo.setNombre(request.queryParams("nombre"));
		nuevoDispositivo.setDescripcion(request.queryParams("descripcion"));
		nuevoDispositivo.setHsMensualMinimo(Integer.parseInt(request.queryParams("hsMensualMinimo")));
		nuevoDispositivo.setHsMensualMaximo(Integer.parseInt(request.queryParams("hsMensualMaximo")));
		nuevoDispositivo.setConsumoKwHora(Float.parseFloat(request.queryParams("ConsumoKwHora")));
		nuevoDispositivo.setEsEsencial(Boolean.parseBoolean(request.queryParams("esEsencial")));
		nuevoDispositivo.setEsInteligente(Boolean.parseBoolean(request.queryParams("esInteligente")));
		
		model.put("dispositivos", 4);
		return new ModelAndView(model, "dispositivoDetalle.hbs");
	}

}
