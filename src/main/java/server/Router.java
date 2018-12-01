package server;

import controllers.*;

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
		ReglaController reglaController = new ReglaController();
		AdministradorController administradorController = new AdministradorController();
		OptimizadorController optimizadorController = new OptimizadorController();
		CLIController cliController = new CLIController();
		
		
		Spark.before("/sge/*",(req,res)->{
			Integer id = req.session().attribute("id");
			if (id==null) {
				res.redirect("/login");
			}
		});
		
		/* LOGIN */
		Spark.get("/", loginController::ver, Router.engine);
		Spark.get("/login", loginController::ver, Router.engine);
		Spark.post("/login",loginController::loguear,engine);
		Spark.get("/logout",loginController::logout,engine);
		Spark.get("/sge/home", loginController::home, engine);

		/* MAPA */
		Spark.get("/mapa", mapController::drawMap, Router.engine);
		Spark.get("/api/transformadores", (req, res) -> mapController.getTrafos(req, res));
		Spark.get("/api/zonas", (req, res) -> mapController.getZonas(req, res));
		
		/* CLIENTE */
		Spark.get("/sge/cliente" , clienteController::ver, Router.engine);
		Spark.get("/sge/cliente/estado", clienteController::estado , Router.engine);
		Spark.get("/sge/cliente/consumo", clienteController::consumoPeriodo , Router.engine);
		Spark.post("/sge/cliente/consumo", clienteController::consumoPeriodoResultado , Router.engine);
		
		Spark.get("/sge/cliente/dispositivos" , dispositivoUsuarioController::dispositivos, Router.engine);
		Spark.get("/sge/cliente/dispositivo", dispositivoUsuarioController::crear, Router.engine);
		Spark.post("/sge/cliente/dispositivo", dispositivoUsuarioController::guardar, Router.engine);
		
		Spark.get("/sge/cliente/dispositivo/:id" , dispositivoUsuarioController::ver, Router.engine);
		Spark.put("/sge/cliente/dispositivo/:id", dispositivoUsuarioController::modificar, Router.engine);
		Spark.post("/sge/cliente/dispositivo/:id", dispositivoUsuarioController::update, Router.engine);
		Spark.delete("/sge/cliente/dispositivo/:id", dispositivoUsuarioController::eliminar , Router.engine);
		
		Spark.get("/sge/cliente/reglas" , reglaController::reglas, Router.engine);
		Spark.get("/sge/cliente/regla", reglaController::crear, Router.engine);
		Spark.post("/sge/cliente/regla", reglaController::guardar, Router.engine);
		
		Spark.put("/sge/cliente/regla/:id", reglaController::modificar, Router.engine);
		Spark.post("/sge/cliente/regla/:id", reglaController::update, Router.engine);
		Spark.delete("/sge/cliente/regla/:id", reglaController::eliminar , Router.engine);
		
		
		
		/* ADMINISTRADOR */
	    Spark.get("/sge/administrador/dispositivo" , administradorController::crearDispositivo, Router.engine);
	    Spark.get("/sge/administrador/dispositivo", administradorController::crear, Router.engine);
		Spark.post("/sge/administrador/dispositivo", administradorController::guardar, Router.engine);
		
		//Reportes
		Spark.get("/sge/reportes", administradorController::reportes, Router.engine);
		Spark.post("/api/reportes", administradorController::reportes_results, Router.engine);

		//CLI
		Spark.get("/api/dispositivos/:id", (req, res) -> cliController.getDispositivos(req, res));
		Spark.get("/api/apagar_dispositivo/:id/:dispositivo", (req, res) -> cliController.apagarDispositivo(req, res));
		Spark.get("/api/encender_dispositivo/:id/:dispositivo", (req, res) -> cliController.encenderDispositivo(req, res));
		Spark.get("/api/ahorro_dispositivo/:id/:dispositivo", (req, res) -> cliController.ahorroDispositivo(req, res));
		Spark.get("/api/consumo_ultimo_mes/:id", (req, res) -> cliController.consumoUltimoMes(req, res));
		
		
		Spark.get("/sge/optimizador",optimizadorController::mostrarResultadosOptimizador,Router.engine);


	}
	
	
}