package entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class DispositivoEstandar extends Dispositivo {
	
	protected float kwHora; //consumo de kw por hora
	private float horasPorDia; //horas encendido por dia
	
	
	
	//constructor
	public DispositivoEstandar(){
		
	}
	
	@Override
	public boolean esInteligente() {
		return false;
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
		return periodo.getDays();
		
	}
	
	//me devuelve el consumo estimativo para un dia
	private float consumoEnElDia() {
		return horasPorDia * kwHora;
	}


	//getters y setters
	public float getHorasPorDia() {
		return horasPorDia;
	}
	
	public void setHorasPorDia(float horasPorDia) {
		this.horasPorDia = horasPorDia;
	}
	
	public float getKwHora() {
		return kwHora;
	}

	public void setKwHora(float kwHora) {
		this.kwHora = kwHora;
	}

	


}
