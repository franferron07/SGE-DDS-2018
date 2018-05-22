package entities;

import java.util.List;

public class Regla {
	
	private List<ActuadorBase> actuadores;
	private String nombreRegla;
	
	public Regla(String nombre){
		super();
		this.nombreRegla=nombre;
	}
	
	//evalua segun la regla si va a ejecutar el actuador o no.
	public void evaluarMedicion(Sensor sensor){

		
		
	}
	
	//ejecuto actuador
	public void ejecutarActuador(ActuadorBase actuador){
		actuador.ejecutarAccion();
		
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
