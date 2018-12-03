package server;

import server.Server;
import server.SubscriberMQTT;

public class init{

	public static void main(String[] args) {
		Thread server = new Server("spark_server");
		Thread subscriber = new SubscriberMQTT("mqtt_subscriber");

		AvisoConsumos consumos = new AvisoConsumos("Thread consumos");
		
		server.start();
		subscriber.start();
		consumos.start();
	}

}
