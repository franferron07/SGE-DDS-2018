package server;


import org.eclipse.paho.client.mqttv3.*;

import entities.Medicion;
import entities.Sensor;

public class SubscriberMQTT extends Thread{
	
	public Sensor sensor;
	
	
	public SubscriberMQTT (Sensor sensor) {
		
		super( String.valueOf(sensor.getId() ) );
		this.sensor= sensor;
	}
	
	
	

    public void run() {
    	
    	String channel = "dds-sensores-"+ String.valueOf(this.sensor.getId() ) ;

        System.out.println("== START SUBSCRIBER ==");

        MqttClient client;
		try {
			client = new MqttClient("tcp://test.mosquitto.org:1883", "serverClientId");
		//	client = new MqttClient("tcp://localhost:1883", "serverClientId");

        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable throwable) {
                System.out.println("Connection to MQTT broker lost!");
            }

            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println("Message received:\t" + new String(mqttMessage.getPayload()));
                
                //aviso al sensor
                Double valor = Double.parseDouble( new String(mqttMessage.getPayload()) ) ;
                Medicion medicion = new Medicion(valor);
                sensor.obtenerMedicion(medicion);
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
