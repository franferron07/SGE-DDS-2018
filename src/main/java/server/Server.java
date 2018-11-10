package server;

import repositorios.RepositorioDispositivosLista;
import repositorios.RepositorioUsuarios;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		
		
		RepositorioUsuarios.cargarUsuarios();
		RepositorioDispositivosLista.cargarDispositiosLista();
		
		Spark.port(9000);		
		Router.init();		
		DebugScreen.enableDebugScreen();
	}
}
