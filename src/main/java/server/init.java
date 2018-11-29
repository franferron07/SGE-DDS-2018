package server;

import server.Server;
import server.SubscriberMQTT;

public class init{

	public static void main(String[] args) {
		Thread server = new Server("spark_server");
		Thread subscriber = new SubscriberMQTT("mqtt_subscriber");

		
		server.start();
		subscriber.start();
	}

}
