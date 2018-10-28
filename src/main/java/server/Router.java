package server;

import controllers.OptimizadorController;

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

		OptimizadorController simplexControler = new OptimizadorController();

		
		Spark.get("/", loginController::loguin, Router.engine);
		
		Spark.get("/inicio", inicioController::inicio, Router.engine);
		
		Spark.get("/mapa", mapController::drawMap, Router.engine);
	
		Spark.get("/api/transformadores", (req, res) -> mapController.getTrafos(req, res));
		Spark.get("/api/zonas", (req, res) -> mapController.getZonas(req, res));




		//Spark.get("/usuarios",simplexControler::mostrarUsuariosController,Router.engine);
		//Spark.get("/usuarios/simplex",(req, res) ->simplexControler.mostrarHorasDeUsuariosSimplexController(req, res));
		//-------Spark.get("/usuarios/optimizador",simplexControler::mostrarHorasDeUsuariosSimplexController,Router.engine);
		//Spark.get("/saludo/*/:nombre", (req,res) -> "Hola "+req.splat()[0]+" "+req.params("nombre"));


	}
	
	
}