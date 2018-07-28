package reglasYActuadores;

import java.util.ArrayList;
import java.util.List;

import entities.ObservadorSensor;

public abstract class Regla implements ObservadorSensor {
	
	protected List<ActuadorBase> actuadores;
	private String nombreRegla;
	
	public Regla(String nombre){
		this.nombreRegla=nombre;
		this.actuadores= new ArrayList<ActuadorBase>();
	}
	
	@Override
	public void notificacionDeMedicion(Float valor) {
		evaluarMedicion(valor);
	}
	//ejecuto si se cumplen todas las condiciones de la regla , sea simple o compuesta.
	public void evaluarMedicion(Float valor) {
		
		if( cumpleCondiciones(valor) ){
			ejecutarActuadores();
		}
		
	}
		
	public abstract boolean cumpleCondiciones(Float valor);
	
	public abstract void ejecutarActuadores();
	

	//getters y setters
	
	public void agregarActuador(ActuadorBase unActuador){
		if (!actuadores.contains(unActuador)) {
			this.actuadores.add(unActuador); 
		}		
	}
	
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
