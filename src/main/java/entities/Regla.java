package entities;

public abstract class Regla {

	//habria que ver si es una lista o un solo actuador(creo que es lista)
	private ActuadorBase actuador;
	
	//evalua segun la regla si va a ejecutar el actuador o no.
	public abstract void evaluarMedicion(Sensor sensor);
	
	//ejecuto actuador
	private void ejecutarActuador(){
		this.actuador.ejecutarAccion();
	}

}
