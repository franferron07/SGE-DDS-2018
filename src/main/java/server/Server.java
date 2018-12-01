package server;

import repositorios.RepositorioActuadoresString;
import repositorios.RepositorioDispositivosLista;
import repositorios.RepositorioRegla;
import repositorios.RepositorioUsuarios;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server extends Thread implements spark.servlet.SparkApplication{
	
	public Server(String msg) {
		super(msg);
	}
	
	public void run() {
		
		
		this.init();
	}

	@Override
	public void init() {
		
		RepositorioUsuarios.cargarUsuarios();
		RepositorioDispositivosLista.cargarDispositiosLista();
		RepositorioActuadoresString.cargarActuadoresEnum();
		RepositorioRegla.cargarReglas();
		
		Spark.port(9000);		
		Router.init();		
		DebugScreen.enableDebugScreen();
		
	}
}
