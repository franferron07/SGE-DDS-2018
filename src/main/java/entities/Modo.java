package entities;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Modo {
		
	private Date fechaHoraInicio;
	private Date fechaHoraFin;
	private float consumoKW; 
	
	public abstract boolean encendido();
	
	public abstract float consumoEnPeriodo();

	public abstract void apagarse(DispositivoInteligente disp);

	public abstract void encenderse(DispositivoInteligente disp);

	public abstract void ahorrarseEnergia(DispositivoInteligente disp);
	
	public abstract Modo clone();

	public Date getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public float getConsumoKW() {
		return consumoKW;
	}

	public void setConsumoKW(float consumoKW) {
		this.consumoKW = consumoKW;
	}	
	
}
