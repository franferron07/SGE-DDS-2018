package reglasYActuadores;


import dispositivos.DispositivoInteligente;


public class CambiarIntensidadLuzCommand extends ActuadorBase {


	
	
	@Override
	public void ejecutarAccion( DispositivoInteligente dispositivo ) {
		dispositivo.cambiarIntensidadLuz();		
	}



}
