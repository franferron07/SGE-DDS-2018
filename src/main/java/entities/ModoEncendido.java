package entities;

import java.time.LocalDateTime;

public class ModoEncendido implements Modo{

	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private float consumoKW;
	
	
	
	//constructor
    public ModoEncendido() {
    	
    	fechaHoraInicio= LocalDateTime.now();
    	
		
	}
    
    @Override
    public float consumoEnPeriodo(){
    
    	
    	return 0;
    }

	@Override
	public boolean encendido() {
		return true;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {		
		
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo() );
				
		disp.setModo(new ModoApagado());
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
       
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo() );
		
		disp.setModo(new ModoAhorroEnergia());   	
	}
	
	public String toString() {
		return "Modo Encendido";
	}
	

	//getters y setters
	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		fechaHoraFin = fechaHoraFin;
	}

	public float getConsumoKW() {
		return consumoKW;
	}

	public void setConsumoKW(float consumoKW) {
		this.consumoKW = consumoKW;
	}

}
