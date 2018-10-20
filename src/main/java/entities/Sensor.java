package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import reglasYActuadores.Regla;

@Entity
@Table(name="sensor")
public class Sensor  {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
	private List<Regla> observadores;
	@OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
	private List<Medicion> mediciones;
	
	
	public Sensor(){
		observadores= new ArrayList<Regla>();
		mediciones= new ArrayList<Medicion>();
	}
	
	//se realiza de alguna manera de forma externa y se reciben datos de esa medicion
	public void obtenerMedicion( Medicion medicion ){
		/* procedimiento no definido. es externo*/	
		agregarMedicion(medicion);
		avisarMedicion(medicion);
	}

	//metodo que avisa a sus observadores(reglas) que realizo la medicion
	private void avisarMedicion(Medicion medicion) {
		
		this.observadores.forEach( r -> r.notificacionDeMedicion(medicion));
		
	}

	
	
	public void agregarMedicion(Medicion unaMedicion){
		 if (!mediciones.contains(unaMedicion)) {
			 this.mediciones.add(unaMedicion); 
		 }
	}
	
	
	
	
	public void agregarObservador(Regla regla){
		 if (!observadores.contains(regla)) {
			 this.observadores.add(regla); 
		 }
	}
	
	public int cantidadObservadores(){
		return observadores.size();
	}
	
	
	//getters y setters

	public List<Regla> getObservadorSensor() {
		return observadores;
	}

	public void setObservadorSensor(List<Regla> observadores) {
		this.observadores = observadores;
	}

	public List<Medicion> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<Medicion> mediciones) {
		this.mediciones = mediciones;
	}


	
	
	
}
