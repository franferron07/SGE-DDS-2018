package reglasYActuadores;

import dispositivos.DispositivoInteligente;


public class CambiarTemperaturaActuadorCommand extends ActuadorBase{
	


	
	@Override
	public void ejecutarAccion( DispositivoInteligente dispositivo ) {
		dispositivo.cambiarTemperaturaActuador();		
	}


}
