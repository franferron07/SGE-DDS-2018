package entities;

public class Regla {

	//habria que ver si es una lista o un solo actuador(creo que es lista)
	private ActuadorBase actuador;
	
	//evalua segun la regla si va a ejecutar el actuador o no.
	public void evaluarMedicion(Sensor sensor) {
		
	}
	
	//
	private void ejecutarActuador(){
		this.actuador.ejecutarAccion();
	}

}
