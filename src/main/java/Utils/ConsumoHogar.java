package Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ConsumoHogar {
	
	@Id
	public int id;
	
	@Column
	public String nombre;
	@Column
	public String apellido;
	@Column
	public String domicilio;
	@Column
	public double consumo_inteligentes;
	@Column
	public double consumo_estandar;
	@Column
	public float consumo_total;
	
	public ConsumoHogar() {
		
	}

	public ConsumoHogar(String nombre, String apellido, String domicilio, double consumo_inteligentes,
			double consumo_estandar, float consumo_total) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.consumo_inteligentes = consumo_inteligentes;
		this.consumo_estandar = consumo_estandar;
		this.consumo_total = consumo_total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public double getConsumo_inteligentes() {
		return consumo_inteligentes;
	}

	public void setConsumo_inteligentes(double consumo_inteligentes) {
		this.consumo_inteligentes = consumo_inteligentes;
	}

	public double getConsumo_estandar() {
		return consumo_estandar;
	}

	public void setConsumo_estandar(double consumo_estandar) {
		this.consumo_estandar = consumo_estandar;
	}

	public float getConsumo_total() {
		return consumo_total;
	}

	public void setConsumo_total(float consumo_total) {
		this.consumo_total = consumo_total;
	}

	
	
	
}