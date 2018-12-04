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
	public Double valor;
	
	@Enumerated
	@Column(name="magnitud")
	private TipoMagnitud magnitud ;
	
	@Column(name="fechaInicio")
	public LocalDateTime fechaInicio;
	
	@Column(name="fechaFin")
	public LocalDateTime fechaFin;
	
	
	
	public Medicion(  ){

	}
	
	//constructor
	public Medicion( Double valor ){
		
		this.valor = valor;
		
	}
	
	
	//getters y setters
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
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
