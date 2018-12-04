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

import org.hibernate.annotations.Where;

import models.ModelHelper;
import reglasYActuadores.Regla;
import server.SubscriberMQTT;

@Entity
@Table(name="sensor")
public class Sensor {
	
	@Id
	@GeneratedValue
	private int id;
	
	
	@OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
	@Where(clause = "activado = 1")
	private List<Regla> observadores;
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<Medicion> mediciones;
	
	
	public Sensor(){
		observadores= new ArrayList<Regla>();
		mediciones= new ArrayList<Medicion>();

		//suscribo a canal
		Thread subscriber = new SubscriberMQTT(this);
		subscriber.start();

	}
	

	//se realiza de alguna manera de forma externa y se reciben datos de esa medicion
	public void obtenerMedicion( Medicion medicion ){
		
		agregarMedicion(medicion);
		avisarMedicion(medicion);
		
		ModelHelper model = new ModelHelper();
		
		model.modificar(this);
	}

	//metodo que avisa a sus observadores(reglas) que realizo la medicion
	private void avisarMedicion(Medicion medicion) {
		 System.out.println( "*********"+ observadores.size() );
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
		 
		 ModelHelper model = new ModelHelper();
		 model.modificar(this);
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


	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public List<Regla> getObservadores() {
		return observadores;
	}

	public void setObservadores(List<Regla> observadores) {
		this.observadores = observadores;
	}
	
	


	
	
	
}
