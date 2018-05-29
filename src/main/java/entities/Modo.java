package entities;

import java.time.LocalDateTime;

public abstract class Modo {
		
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private float consumoKW; 
	
	public abstract boolean encendido();
	
	public abstract float consumoEnPeriodo();

	public abstract void apagarse(DispositivoInteligente disp);

	public abstract void encenderse(DispositivoInteligente disp);

	public abstract void ahorrarseEnergia(DispositivoInteligente disp);
	
	public abstract Modo clone();

	

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public float getConsumoKW() {
		return consumoKW;
	}

	public void setConsumoKW(float consumoKW) {
		this.consumoKW = consumoKW;
	}	
	
}
