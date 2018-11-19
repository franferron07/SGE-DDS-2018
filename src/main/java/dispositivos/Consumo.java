package dispositivos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="consumo")
public class Consumo {

	@Id
	@GeneratedValue
	private int id;
	
	
	@Column(name="fechaInicio")
	protected String fechaInicio_s;

	@Column(name="fechaFin")
	protected String fechaFin_s;
	

	@Column(name = "consumo")
	private float consumo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	protected Modo modo;
	
	@Transient
	private LocalDateTime inicio;
	@Transient
	private LocalDateTime fin;
	
	public Consumo( Modo m  , LocalDateTime i , LocalDateTime f , float c  ){
		this.inicio=i;
		this.fin=f;
		this.consumo=c;
		this.modo = m;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.fechaInicio_s= inicio.format(formatter);
		this.fechaFin_s= fin.format(formatter);
	}
	
	
	public Consumo(){
		
	}

	//metodo que indica si esta dentro del periodo pasado o no.por decision de diseño debe estar el inicio y fin en el periodo pasado
	public boolean cumplePeriodoConsumo(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		
		if( this.getInicio().compareTo(fechaInicial) >= 0  && this.getFin().compareTo(fechaFinal) <= 0 )  
			{
				return true;
			}
		return false;
	}
	
	
	//parsea fecha
	public LocalDateTime parsearFecha( String fecha_s ){
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(fecha_s, formatter);
	}
	
	
	//getters y seters
	public double getConsumo() {
		return consumo;
	}
	
	public LocalDateTime getInicio() {
		
		this.inicio = parsearFecha(this.fechaInicio_s);
		return inicio;
	}

	public LocalDateTime getFin() {
		
		this.fin =  parsearFecha(this.fechaFin_s);
		return fin;
	}
	
	public int getId() {
		return id;
	}
	
	
}
