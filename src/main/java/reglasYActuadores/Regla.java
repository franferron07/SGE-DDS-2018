package reglasYActuadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import dispositivos.DispositivoInteligente;
import entities.Medicion;
import entities.ObservadorSensor;

@Entity
@Table(name="regla")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
public abstract class Regla {
	

	@Id
	@GeneratedValue
	public int id;
	
	@Column(name="nombre")
	private String nombreRegla;
	
	@Transient
	private List<ActuadorBase> actuadores;
	
	
	@ManyToMany(cascade = { 
	        CascadeType.DETACH
	    })
	    @JoinTable(name = "regla_actuadorstring",
	        joinColumns = @JoinColumn(name = "regla_id"),
	        inverseJoinColumns = @JoinColumn(name = "actuador_id")
	    )
	protected List<ActuadorString> actuadores_string;
	
	
	//@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)	
	@ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "regla_dispositivointeligente",
        joinColumns = @JoinColumn(name = "regla_id"),
        inverseJoinColumns = @JoinColumn(name = "dispositivo_id")
    )
	protected List<DispositivoInteligente> dispositivos;
	
	@Column(name="activado")
	protected boolean activado;
	
	
	public Regla(){
		
	}
	
	public Regla(String nombre){
		this.nombreRegla=nombre;
		this.actuadores= new ArrayList<ActuadorBase>();
		this.actuadores_string = new ArrayList<ActuadorString>();
		
		this.dispositivos = new ArrayList<DispositivoInteligente>();
		this.activado=true;
		
	}
	
	
	public void crearActuadoresBase(){
		
		this.actuadores= new ArrayList<ActuadorBase>();
		
		ActuadorFactory factory = new ActuadorFactory();
		
		for (ActuadorString act_s : actuadores_string) {
			
			ActuadorBase actuador = factory.crearActuador(act_s.getNombre());
			this.actuadores.add(actuador);
		}
	}
	
	public void notificacionDeMedicion(Medicion medicion) {

		evaluarMedicion(medicion);
	}
	
	//ejecuto si se cumplen todas las condiciones de la regla , sea simple o compuesta.
	public void evaluarMedicion(Medicion medicion) {

		if( cumpleCondiciones(medicion.getValor()) ){
			
			ejecutarAccionDispositivosActuadores();
		}
		
	}
		
	
	public abstract boolean cumpleCondiciones(Double d);

	
	//accion que se ejecuta cuando se cumple la condicion. a partir de los dispoisitivos llama a los actuadores para ejecutarse
	public void ejecutarAccionDispositivosActuadores() {
		
		this.dispositivos.stream().forEach(d->ejecutarActuadores(d));	
	}
	
	public abstract void ejecutarActuadores(DispositivoInteligente d) ;
	

	public void activar(){
		this.activado=true;
	}
	
	public void desactivar(){
		
		this.activado=false;
	}
	
	public void agregarDispositivo(DispositivoInteligente inteligente) {
		this.dispositivos.add(inteligente);
	}
	
	
	//getters y setters

	public void agregarActuador(ActuadorBase unActuador){
		if (!actuadores.contains(unActuador)) {
			this.actuadores.add(unActuador); 
		}		
	}
	
	/*public void agregarActuadorEnum(ActuadoresEnum unActuador){
		
		this.actuadores_enums.add(unActuador);
	}*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<DispositivoInteligente> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<DispositivoInteligente> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public List<ActuadorBase> getActuadores() {
		
		crearActuadoresBase();
		
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

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	
	
	
	public List<ActuadorString> getActuadores_string() {
		return actuadores_string;
	}

	public void setActuadores_string(List<ActuadorString> actuadores_string) {
		this.actuadores_string = actuadores_string;
	}

	public void agregarActuadores_string(ActuadorString actuador){
		
		this.actuadores_string.add(actuador);
		
		//agrego actuador base a lista
		ActuadorFactory factory = new ActuadorFactory();
		ActuadorBase act = factory.crearActuador(actuador.getNombre());
		this.actuadores.add(act);
		
	}
	
	
	

	
	

}
