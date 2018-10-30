package dispositivos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//dispositivo con datos genericos del dispositivo que tendrá el usuario.

@Entity
@Table(name="dispositivoDetalle")
public class DispositivoDetalle {
	
	@Id
	@GeneratedValue
	public int id;
	
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name="hsMensualMinimo")
	private int hsMensualMinimo;
	@Column(name="hsMensualMaximo")
	private int hsMensualMaximo;
	@Column(name="consumoKwHora")
	private float consumoKwHora;
	@Column(name="bajoConsumo")
	private boolean esBajoConsumo;
	@Column(name="inteligente")
	private boolean esInteligente;
	@Column(name="esencial")
	private boolean esEsencial;
	
	//constructor
	public DispositivoDetalle(){
		
	}
	
	
	//getters and setters
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getHsMensualMinimo() {
		return hsMensualMinimo;
	}
	public void setHsMensualMinimo(int hsMensualMinimo) {
		this.hsMensualMinimo = hsMensualMinimo;
	}
	public int getHsMensualMaximo() {
		return hsMensualMaximo;
	}
	public void setHsMensualMaximo(int hsMensualMaximo) {
		this.hsMensualMaximo = hsMensualMaximo;
	}
	public float getConsumoKwHora() {
		return consumoKwHora;
	}
	public void setConsumoKwHora(float consumoKwHora) {
		this.consumoKwHora = consumoKwHora;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public boolean isEsBajoConsumo() {
		return esBajoConsumo;
	}
	public boolean isEsInteligente() {
		return esInteligente;
	}


	public boolean isEsEsencial() {
		return esEsencial;
	}

	public void setEsInteligente(boolean esInteligente) {
		this.esInteligente = esInteligente;
	}

	public void setEsEsencial(boolean esEsencial) {
		this.esEsencial = esEsencial;
	}


	public int getId() {
		return id;
	}


	
	
	
	

}
