package usuarios;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name="categoria")
public class Categoria {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="consumoMinimo")
	private float consumoMinimo;
	@Column(name="consumoMaximo")
	private float consumoMaximo;
	@Column(name="concepto")
	private String concepto; //Valores que puede tener Cargo fijo, Cargo variable u otro si hubiera en el futuro.
	@Column(name="unidad")
	private String unidad;
	@Column(name="costoNormal")
	private float costoNormal;

	public Categoria() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(float consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public float getConsumoMaximo() {
		return consumoMaximo;
	}

	public void setConsumoMaximo(float consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public float getCostoNormal() {
		return costoNormal;
	}

	public void setCostoNormal(float costoNormal) {
		this.costoNormal = costoNormal;
	}

	public int getId() {
		return id;
	}
	
}