package entities;

import java.util.List;

public class Sensor  {

	
	private List<Regla> reglas;
	
	//se realiza de alguna manera de forma externa y se reciben datos de esa medicion
	public void realizarMedicion(){
		/*procedimiento aun no definido*/
		avisarMedicion();
	}

	//metodo que avisa a sus observadores(reglas) que realizo la medicion
	private void avisarMedicion() {
		
		this.reglas.forEach( r -> r.evaluarMedicion(this));
		
	}


}
