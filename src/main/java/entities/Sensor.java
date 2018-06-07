package entities;

import java.util.ArrayList;
import java.util.List;

import enums.TipoMagnitud;

public class Sensor  {

	//si mide temparatura , movimiento, humedad,intensidad de luz
	private TipoMagnitud magnitud ;
	private List<ObservadorSensor> observadores;
	
	
	public Sensor(){
		observadores= new ArrayList<ObservadorSensor>();
	}
	
	//se realiza de alguna manera de forma externa y se reciben datos de esa medicion
	public void realizarMedicion(){
		/* procedimiento no definido. es externo*/
		avisarMedicion();
	}

	//metodo que avisa a sus observadores(reglas) que realizo la medicion
	private void avisarMedicion() {
		
		this.observadores.forEach( r -> r.notificacionDeMedicion(this));
		
	}

	
	public void agregarObservador(ObservadorSensor unObservador){
		this.observadores.add(unObservador);
	}
	
	public int cantidadObservadores(){
		return observadores.size();
	}
	
	
	//getters y setters
	public TipoMagnitud getTipoMagnitud() {
		return magnitud;
	}

	public void setTipoMagnitud(TipoMagnitud tipoMagnitud) {
		this.magnitud = tipoMagnitud;
	}

	public List<ObservadorSensor> getObservadorSensor() {
		return observadores;
	}

	public void setObservadorSensor(List<ObservadorSensor> observadores) {
		this.observadores = observadores;
	}


	
	
	
}
