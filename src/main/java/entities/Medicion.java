package entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.TipoMagnitud;

@Entity
@Table(name="medicion")
public class Medicion {
	
	@Id
	@GeneratedValue
	public int id;
	
	@Column(name="valor")
	public double valor;
	
	@Enumerated
	@Column(name="magnitud")
	private TipoMagnitud magnitud ;
	
	@Column(name="fechaInicio")
	public LocalDateTime fechaInicio;
	
	@Column(name="fechaFin")
	public LocalDateTime fechaFin;
	
	
	
	
	//getters y setters
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public TipoMagnitud getMagnitud() {
		return magnitud;
	}
	public void setMagnitud(TipoMagnitud magnitud) {
		this.magnitud = magnitud;
	}
	public int getId() {
		return id;
	}
	
	
	
	

}
