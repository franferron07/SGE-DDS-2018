package reglasYActuadores;

import dispositivos.DispositivoInteligente;

public class ApagarDICommand implements ActuadorBase {

	DispositivoInteligente dispositivo;
	
	public ApagarDICommand(DispositivoInteligente di) {
		dispositivo = di;
	}
	
	@Override
	public void ejecutarAccion() {
		// TODO Auto-generated method stub
		
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	

}
