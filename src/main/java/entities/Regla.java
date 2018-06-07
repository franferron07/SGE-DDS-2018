package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Regla implements ObservadorSensor {
	
	private List<ActuadorBase> actuadores;
	private String nombreRegla;
	
	
	
	@Override
	public void notificacionDeMedicion(Sensor unSensor) {
		evaluarMedicion(unSensor);
	}
	
	public Regla(String nombre){
		this.nombreRegla=nombre;
		this.actuadores= new ArrayList<ActuadorBase>();
	}
	
	//evalua segun la regla si va a ejecutar el actuador o no.
	public abstract void evaluarMedicion(Sensor sensor);
	
	
	public void agregarActuador(ActuadorBase unActuador){
		this.actuadores.add(unActuador);
	}
	
	

	//getters y setters
	public List<ActuadorBase> getActuadores() {
		return actuadores;
	}

	public void setActuadores(List<ActuadorBase> actuadores) {
		this.actuadores = actuadores;
	}

	public String getNombreRegla() {
		return nombreRegla;
	}

	public void setNombreRegla(String nombreRegla) {
		this.nombreRegla = nombreRegla;
	}

	
	

}
