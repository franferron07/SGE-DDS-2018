package entities;

import java.time.LocalDateTime;

public class ConsumoModo {

	
	private LocalDateTime inicio;
	private LocalDateTime fin;
	private float consumo;
	
	public ConsumoModo( LocalDateTime i , LocalDateTime f , float c ){
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
	
	
}
