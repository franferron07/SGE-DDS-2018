package composite;

import java.util.ArrayList;
import java.util.List;

import entities.ActuadorBase;
import entities.Sensor;
import observers.Observer;

public abstract class Regla implements Observer{

    public abstract int operate();

	private List<ActuadorBase> actuadores;
	private String nombreRegla;
	 private Sensor subject;
	 
	 
	public Regla(String nombre){
		this.nombreRegla=nombre;
		this.actuadores= new ArrayList<ActuadorBase>();
	}
	
	

	    public Regla(Sensor subject) {
	        this.subject = subject;
	        this.subject.addObserver(this);
	    }

	    @Override
	    public void update() {
	        System.out.println("Observer A: sujeto a cambiado: " + this.subject.getState());
	    }
	
	
	
	public void agregarActuador(ActuadorBase unActuador){
		this.actuadores.add(unActuador);
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
