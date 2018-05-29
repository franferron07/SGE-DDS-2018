package entities;

import java.time.LocalDateTime;

public abstract class Dispositivo {
	
	private String nombre;
	
	//seguramente esto sea un date time, desde y hasta. Me da el consumo en un determinado periodo de tiempo. 
	public abstract float consumoPeriodo( LocalDateTime desde , LocalDateTime hasta );
	
	public abstract boolean esInteligente();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}