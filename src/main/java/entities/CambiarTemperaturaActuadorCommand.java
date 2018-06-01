package entities;

public class CambiarTemperaturaActuadorCommand implements ActuadorBase{
	
	private DispositivoInteligente dispositivo;

	
	public CambiarTemperaturaActuadorCommand(DispositivoInteligente d) {
		dispositivo=d;
	}
	
	
	@Override
	public void ejecutarAccion() {
			
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}


	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

}
