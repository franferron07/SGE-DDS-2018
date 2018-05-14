package entities;

import java.time.LocalTime;

public class Administrador extends Usuario {

	private LocalTime fechaAltaSistema;
	private int identificadorSistema;

	public Administrador() {

	}
	
	//metodo que deberia hacer el calculo 
	public void periodoDeAlta(){
		
	}

	public LocalTime getFechaAltaSistema() {
		return fechaAltaSistema;
	}

	public int getIdentificadorSistema() {
		return identificadorSistema;
	}

	public void setFechaAltaSistema(LocalTime fechaAltaSistema) {
		this.fechaAltaSistema = fechaAltaSistema;
	}

	public void setIdentificadorSistema(int identificadorSistema) {
		this.identificadorSistema = identificadorSistema;
	}
	
	
}