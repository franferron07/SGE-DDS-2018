package server;

import server.Server;

public class init{

	public static void main(String[] args) {
		Thread server = new Server("spark_server");
		AvisoConsumos consumos = new AvisoConsumos("Thread consumos");
		
		server.start();
		consumos.start();
	}

}
