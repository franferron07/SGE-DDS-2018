package reglasYActuadores;


import org.eclipse.paho.client.mqttv3.MqttException;

import dispositivos.DispositivoInteligente;


public class CambiarTemperaturaActuadorCommand extends ActuadorBase{
	


	
	@Override
	public void ejecutarAccion( DispositivoInteligente dispositivo ) throws MqttException  {
		
		dispositivo.cambiarTemperaturaActuador();
		this.enviarMensaje("Cambiar temperatura:"+dispositivo.getId());
	}


}
