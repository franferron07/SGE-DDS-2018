package entities;

import java.util.List;

public class Sensor  {

	//si mide temparatura , movimiento, humedad,intensidad de luz
	private String tipoMagnitud;
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

	
	//getters y setters
	public String getTipoMagnitud() {
		return tipoMagnitud;
	}

	public void setTipoMagnitud(String tipoMagnitud) {
		this.tipoMagnitud = tipoMagnitud;
	}

	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}


	
	
	
}
