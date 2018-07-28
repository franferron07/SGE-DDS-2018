package reglasYActuadores;

import dispositivos.DispositivoInteligente;

public class CambiarTemperaturaActuadorCommand implements ActuadorBase{
	
	private DispositivoInteligente dispositivo;

	
	public CambiarTemperaturaActuadorCommand(DispositivoInteligente d) {
		dispositivo=d;
	}
	
	
	@Override
	public void ejecutarAccion() {
		dispositivo.cambiarTemperaturaActuador();		
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}


	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

}
