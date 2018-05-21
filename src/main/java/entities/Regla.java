package entities;

import java.util.List;

public class Regla {
	
	private List<ActuadorBase> actuadores;
	
	//evalua segun la regla si va a ejecutar el actuador o no.
	public void evaluarMedicion(Sensor sensor){
		
	}
	
	//ejecuto actuador
	private void ejecutarActuador(ActuadorBase actuador){
		actuador.ejecutarAccion();
	}

}
