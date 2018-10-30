package server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controllers.ClienteController;
import controllers.DispositivoUsuarioController;
import controllers.AdministradorController;
import controllers.InicioController;
import controllers.LoginController;
import controllers.MapController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

private static HandlebarsTemplateEngine engine;
	
	private static void initEngine() {
		Router.engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
	}
	
	public static void init() {
		Router.initEngine();
		Spark.staticFileLocation("/public");
		Router.configure();
	}
	
	private static void configure(){
		
		
		LoginController loginController = new LoginController();
		InicioController inicioController = new InicioController();
		MapController mapController = new MapController();
		ClienteController clienteController = new ClienteController();
		DispositivoUsuarioController dispositivoUsuarioController = new DispositivoUsuarioController();
		AdministradorController administradorController = new AdministradorController();
		
		Spark.get("/", loginController::ver, Router.engine);
		Spark.get("/login", loginController::ver, Router.engine);
		Spark.post("/login",loginController::loguear,engine);
		Spark.get("/logout",loginController::logout,engine);
		
		Spark.get("/inicio", inicioController::inicio, Router.engine);
		
		Spark.get("/mapa", mapController::drawMap, Router.engine);
	
		Spark.get("/api/transformadores", (req, res) -> mapController.getTrafos(req, res));
		Spark.get("/api/zonas", (req, res) -> mapController.getZonas(req, res));
		
		Spark.get("/cliente" , clienteController::ver, Router.engine);
		Spark.get("/cliente/estado/:id", clienteController::estado , Router.engine);
		Spark.get("/cliente/estado/:id", clienteController::estado , Router.engine);
		Spark.get("/cliente/consumo/:id/:inicio/:fin", clienteController::consumoPeriodo , Router.engine);
		
		Spark.get("/cliente/dispositivos" , dispositivoUsuarioController::dispositivos, Router.engine);
		Spark.get("/cliente/dispositivo", dispositivoUsuarioController::crear, Router.engine);
		Spark.post("/cliente/dispositivo", dispositivoUsuarioController::guardar, Router.engine);
		
		
		Spark.get("/cliente/dispositivo/:id" , dispositivoUsuarioController::ver, Router.engine);
		Spark.put("/cliente/dispositivo/:id", dispositivoUsuarioController::modificar, Router.engine);
		Spark.post("/cliente/dispositivo/:id", dispositivoUsuarioController::update, Router.engine);
		
	    Spark.get("/administrador/dispositivo" , administradorController::crearDispositivo, Router.engine);
	    Spark.get("/administrador/dispositivo", administradorController::crear, Router.engine);
		Spark.post("/administrador/dispositivo", administradorController::guardar, Router.engine);
		
	}
	
	
}