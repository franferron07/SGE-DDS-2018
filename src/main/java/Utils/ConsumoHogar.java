package Utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class ConsumoHogar {
	

	protected String nombre;
	protected String apellido;
	protected String domicilio;
	private float consumoTotal;
	
	
	private int identificadorSistema;

	public ConsumoHogar(String unNombre, String unApellido, String unDomicilio, float unConsumoTotal) {
		
		this.nombre = unNombre;
		this.apellido = unApellido;
		this.domicilio = unDomicilio;
		this.consumoTotal = unConsumoTotal;
	}
	
	
}