package entities;

public class Sensor extends DispositivoInteligente {
 private Actuador actuador;
 
	@Override
	public void ejecutar() {
		actuador.accion();
	}
		
	public Actuador getActuador() {
		return actuador;
	}
	
	public void setActuador(Actuador actuador) {
		this.actuador = actuador;
	}

}
