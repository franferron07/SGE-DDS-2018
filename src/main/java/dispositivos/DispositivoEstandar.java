package dispositivos;

import java.time.LocalDateTime;
import java.time.Period;

public class DispositivoEstandar extends DispositivoUsuario {
	
	private float horasPorDia; //horas encendido por dia
	

	//constructor
	public DispositivoEstandar(){
		
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
		return periodo.getDays();
		
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
