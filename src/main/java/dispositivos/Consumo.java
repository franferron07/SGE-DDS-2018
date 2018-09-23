package dispositivos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="consumo")
public class Consumo {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "fechaInicio")
	private LocalDateTime inicio;
	@Column(name = "fechaFin")
	private LocalDateTime fin;
	@Column(name = "consumo")
	private float consumo;
	
	public Consumo( LocalDateTime i , LocalDateTime f , float c ){
		this.inicio=i;
		this.fin=f;
		this.consumo=c;
	}

	//metodo que indica si esta dentro del periodo pasado o no.por decision de diseño debe estar el inicio y fin en el periodo pasado
	public boolean cumplePeriodoConsumo(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		
		if( this.inicio.compareTo(fechaInicial) >= 0  && this.fin.compareTo(fechaFinal) <= 0 )  
			{
				return true;
			}
		return false;
	}
	
	
	public double getConsumo() {
		return consumo;
	}
	
	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}
	
	public int getId() {
		return id;
	}
	
	
}
