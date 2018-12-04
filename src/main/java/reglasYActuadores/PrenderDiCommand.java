package reglasYActuadores;


import org.eclipse.paho.client.mqttv3.MqttException;

import dispositivos.DispositivoInteligente;


public class PrenderDiCommand extends ActuadorBase {

	
	@Override
	public void ejecutarAccion(DispositivoInteligente dispositivo) throws MqttException  {
		
		dispositivo.encender();
		this.enviarMensaje("Prender dispositivo:"+dispositivo.getId());
	}


	
	
}
