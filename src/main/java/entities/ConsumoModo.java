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
	
}
