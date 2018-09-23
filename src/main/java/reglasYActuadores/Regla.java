package reglasYActuadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import entities.Medicion;
import entities.ObservadorSensor;

@Entity
@Table(name="regla")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo")
public abstract class Regla implements ObservadorSensor {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="nombre")
	private String nombreRegla;
	
	@OneToMany(mappedBy="id" , cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
	protected List<ActuadorBase> actuadores;
	
	public Regla(String nombre){
		this.nombreRegla=nombre;
		this.actuadores= new ArrayList<ActuadorBase>();
	}
	
	@Override
	public void notificacionDeMedicion(Medicion medicion) {
		evaluarMedicion(medicion);
	}
	
	//ejecuto si se cumplen todas las condiciones de la regla , sea simple o compuesta.
	public void evaluarMedicion(Medicion medicion) {
		
		if( cumpleCondiciones(medicion.getValor()) ){
			ejecutarActuadores();
		}
		
	}
		
	public abstract boolean cumpleCondiciones(double d);
	
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
