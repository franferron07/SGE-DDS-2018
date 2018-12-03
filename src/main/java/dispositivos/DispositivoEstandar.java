package dispositivos;

import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.Days;
import java.time.temporal.ChronoUnit;

@Entity
@DiscriminatorValue("estandar")
public class DispositivoEstandar extends DispositivoUsuario {
	
	@Column(name = "horasPorDia")
	private Float horasPorDia; //horas encendido por dia
	

	//constructor
	public DispositivoEstandar( DispositivoDetalle disp_detalle ){
		super();
		detalle = disp_detalle;
	}
	
	public DispositivoEstandar(){
		super();
	}
	
	@Override
	public boolean esInteligente() {
		return false;
	}
	
	
	@Override
	public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
		
		return horasPorDia * periodoEnDias(desde ,hasta);
	}
	
	@Override
	public float consumoPeriodo( LocalDateTime desde , LocalDateTime hasta  ){	
		
		int dias;
		dias= periodoEnDias( desde , hasta );
		


		return consumoEnElDia() * dias ; 
	}
	
	
	
	//calcula cuantos dias dura el periodo. 
	public int periodoEnDias( LocalDateTime desde , LocalDateTime hasta ){
		
		Period periodo= Period.between(desde.toLocalDate(), hasta.toLocalDate());
		
		System.out.println("Consumo desde la funcion PeriodoEnDias");
		
		Integer daysBetween = (int) java.time.temporal.ChronoUnit.DAYS.between(desde.toLocalDate(), hasta.toLocalDate());
		
		System.out.println(daysBetween);
		
		return daysBetween;
		
	}
	
	//me devuelve el consumo estimativo para un dia
	private float consumoEnElDia() {
		return (float) (horasPorDia * this.getConsumoKwHora());
	}


	//getters y setters
	public float getHorasPorDia() {
		return horasPorDia;
	}
	
	public void setHorasPorDia(float horasPorDia) {
		this.horasPorDia = horasPorDia;
	}

	
	
	


	


}
