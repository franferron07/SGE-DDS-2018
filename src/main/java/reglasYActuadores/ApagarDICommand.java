package reglasYActuadores;



import org.eclipse.paho.client.mqttv3.MqttException;

import dispositivos.DispositivoInteligente;


public class ApagarDICommand extends ActuadorBase {



	
	@Override
	public void ejecutarAccion( DispositivoInteligente dispositivo ) throws MqttException  {
		
		dispositivo.apagar();
		this.enviarMensaje("Apagar dispositivo:"+dispositivo.getId());
	}

	
	
	

}
