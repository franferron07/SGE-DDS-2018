package server;

import repositorios.RepositorioUsuarios;
import spark.Spark;
import spark.debug.DebugScreen;
import usuarios.Usuario;

public class Server {
	public static void main(String[] args) {
		Spark.port(9000);
		
		Router.init();
		
		/*Usuario us1 = RepositorioUsuarios.buscarUsuario("test");
		System.out.println("TEST************" + us1.id );*/
		
		
		DebugScreen.enableDebugScreen();
	}
}
