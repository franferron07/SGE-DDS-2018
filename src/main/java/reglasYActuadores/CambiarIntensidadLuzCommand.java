package reglasYActuadores;



import org.eclipse.paho.client.mqttv3.MqttException;

import dispositivos.DispositivoInteligente;


public class CambiarIntensidadLuzCommand extends ActuadorBase {


	
	
	@Override
	public void ejecutarAccion( DispositivoInteligente dispositivo ) throws MqttException  {
		
		dispositivo.cambiarIntensidadLuz();
		this.enviarMensaje("Cambiar intensidad luz:"+dispositivo.getId());
	}



}
