package reglasYActuadores;



import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import dispositivos.DispositivoInteligente;


/*@Entity
@Table(name="actuador")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")*/
public abstract class ActuadorBase {

	
	public abstract void ejecutarAccion( DispositivoInteligente dispositivo ) throws MqttException ;
	
	
	public void enviarMensaje( String msg ) throws MqttException {

        MqttClient client;

		client = new MqttClient("tcp://test.mosquitto.org:1883", MqttClient.generateClientId());
		
		client.connect();

        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());
        client.publish("dds-actuadores", message);

        client.disconnect();

        System.out.println("== END PUBLISHER ==");
		
	}

	
}
