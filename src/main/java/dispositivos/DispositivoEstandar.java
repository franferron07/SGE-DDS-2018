package dispositivos;

import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("estandar")
public class DispositivoEstandar extends DispositivoUsuario {
	
	@Column(name = "horasPorDia")
	private float horasPorDia; //horas encendido por dia
	

	//constructor
	public DispositivoEstandar( DispositivoDetalle disp_detalle ){
		
		detalle = disp_detalle;
		activado = true;
		this.fecha_alta = LocalDateTime.now();
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
