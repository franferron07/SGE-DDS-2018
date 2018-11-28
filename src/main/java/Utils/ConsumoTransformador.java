package Utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class ConsumoTransformador {
	

	public int id;
	public double longitud;
	public double latitud;
	public double consumoTotal;
	
	
	private int identificadorSistema;

	public ConsumoTransformador(int unId, double unaLongitud, double unaLatitud, double unConsumoTotal) {
		
		this.id = unId;
		this.longitud = unaLongitud;
		this.latitud = unaLatitud;
		this.consumoTotal = unConsumoTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getConsumoTotal() {
		return consumoTotal;
	}

	public void setConsumoTotal(double consumoTotal) {
		this.consumoTotal = consumoTotal;
	}

	public int getIdentificadorSistema() {
		return identificadorSistema;
	}

	public void setIdentificadorSistema(int identificadorSistema) {
		this.identificadorSistema = identificadorSistema;
	}
	
	
}