package server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
		
		Spark.get("/", loginController::loguin, Router.engine);
		
		Spark.get("/inicio", inicioController::inicio, Router.engine);
		
		Spark.get("/mapa", mapController::drawMap, Router.engine);
	
		Spark.get("/api/transformadores", (req, res) -> mapController.getTrafos(req, res));
		Spark.get("/api/zonas", (req, res) -> mapController.getZonas(req, res));
		
		
		
		
	}
	
	
}