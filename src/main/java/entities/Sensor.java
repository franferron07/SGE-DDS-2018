package entities;

public class Sensor extends DispositivoInteligente {
 public Sensor(Modo m) {
		super(m);
	}

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
