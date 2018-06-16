package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Regla implements ObservadorSensor {
	
	protected List<ActuadorBase> actuadores;
	private String nombreRegla;
	
	
	
	@Override
	public void notificacionDeMedicion(Float valor) {
		evaluarMedicion(valor);
	}
	
	public Regla(String nombre){
		this.nombreRegla=nombre;
		this.actuadores= new ArrayList<ActuadorBase>();
	}
	
	
	public abstract boolean cumpleCondiciones(Float valor);
	
	public abstract void ejecutarActuadores();
	
	
	//ejecuto si se cumplen todas las condiciones de la regla , sea simple o compuesta.
	public void evaluarMedicion(Float valor) {
		
		if( cumpleCondiciones(valor) ){
			ejecutarActuadores();
		}
		
	}
	
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
