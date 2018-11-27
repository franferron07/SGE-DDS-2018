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


	
}