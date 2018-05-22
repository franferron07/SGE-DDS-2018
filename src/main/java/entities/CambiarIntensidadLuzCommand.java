package entities;

public class CambiarIntensidadLuzCommand implements ActuadorBase {

	private DispositivoInteligente dispositivo;
	
	public CambiarIntensidadLuzCommand(DispositivoInteligente d) {
		dispositivo=d;
	}
	
	@Override
	public void ejecutarAccion() {
		dispositivo.cambiarIntensidadLuz();		
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}


	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

}
