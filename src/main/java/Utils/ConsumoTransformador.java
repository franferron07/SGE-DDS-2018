package Utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class ConsumoTransformador {
	

	protected int id;
	protected double longitud;
	protected double latitud;
	private double consumoTotal;
	
	
	private int identificadorSistema;

	public ConsumoTransformador(int unId, double unaLongitud, double unaLatitud, double unConsumoTotal) {
		
		this.id = unId;
		this.longitud = unaLongitud;
		this.latitud = unaLatitud;
		this.consumoTotal = unConsumoTotal;
	}
	
	
}