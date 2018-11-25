package server;


import org.eclipse.paho.client.mqttv3.*;

public class SubscriberMQTT extends Thread{
	
	public SubscriberMQTT (String msg) {
		super(msg);
	}

    public void run() {
    	
    	String channel = "dds";

        System.out.println("== START SUBSCRIBER ==");

        MqttClient client;
		try {
			client = new MqttClient("tcp://localhost:1883", "serverClientId");

        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable throwable) {
                System.out.println("Connection to MQTT broker lost!");
            }

            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println("Message received:\t" + new String(mqttMessage.getPayload()));
            }

            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });
        client.connect();

        client.subscribe(channel);
        System.out.println("ok!, listening to: " + channel);
		
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
