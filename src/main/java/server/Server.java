package server;

import repositorios.RepositorioDispositivosLista;
import repositorios.RepositorioUsuarios;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server extends Thread{
	
	public Server(String msg) {
		super(msg);
	}
	
	public void run() {
		
		
		RepositorioUsuarios.cargarUsuarios();
		RepositorioDispositivosLista.cargarDispositiosLista();
		
		Spark.port(9000);		
		Router.init();		
		DebugScreen.enableDebugScreen();
	}
}
