package server;

import controllers.*;
import optimizacion_horas.Optimizador;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
		MapController mapController = new MapController();
		ClienteController clienteController = new ClienteController();
		DispositivoUsuarioController dispositivoUsuarioController = new DispositivoUsuarioController();
		AdministradorController administradorController = new AdministradorController();

		OptimizadorController optimizadorController = new OptimizadorController();
		
		/* LOGIN */
		Spark.get("/", loginController::ver, Router.engine);
		Spark.get("/login", loginController::ver, Router.engine);
		Spark.post("/login",loginController::loguear,engine);
		Spark.get("/logout",loginController::logout,engine);

		/* MAPA */
		Spark.get("/mapa", mapController::drawMap, Router.engine);
		Spark.get("/api/transformadores", (req, res) -> mapController.getTrafos(req, res));
		Spark.get("/api/zonas", (req, res) -> mapController.getZonas(req, res));
		
		/* CLIENTE */
		Spark.get("/cliente" , clienteController::ver, Router.engine);
		Spark.get("/cliente/estado", clienteController::estado , Router.engine);
		Spark.post("/cliente/consumo", clienteController::consumoPeriodo , Router.engine);
		
		Spark.get("/cliente/dispositivos" , dispositivoUsuarioController::dispositivos, Router.engine);
		Spark.get("/cliente/dispositivo", dispositivoUsuarioController::crear, Router.engine);
		Spark.post("/cliente/dispositivo", dispositivoUsuarioController::guardar, Router.engine);
		
		Spark.get("/cliente/dispositivo/:id" , dispositivoUsuarioController::ver, Router.engine);
		Spark.put("/cliente/dispositivo/:id", dispositivoUsuarioController::modificar, Router.engine);
		Spark.post("/cliente/dispositivo/:id", dispositivoUsuarioController::update, Router.engine);
		
		/* ADMINISTRADOR */
	    Spark.get("/administrador/dispositivo" , administradorController::crearDispositivo, Router.engine);
	    Spark.get("/administrador/dispositivo", administradorController::crear, Router.engine);
		Spark.post("/administrador/dispositivo", administradorController::guardar, Router.engine);

		Spark.get("/optimizador/:id",optimizadorController::mostrarResultadosOptimizador,Router.engine);


	}
	
	
}