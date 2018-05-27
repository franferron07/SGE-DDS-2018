package entities;

import java.time.LocalDateTime;

public class ModoAhorroEnergia implements Modo {

	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private float consumoKW; //consumo en horas del dispositivo, lo guardo para el log por si llega a cambiar el consumo del dispositivo
	
	
	//constructor
	public ModoAhorroEnergia(float consumo) {
		
		fechaHoraInicio= LocalDateTime.now();
    	consumoKW = consumo;
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
		//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		disp.agregarLogModo( disp.getModo() );
		
		disp.setModo(new ModoApagado());
		
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		disp.agregarLogModo( disp.getModo() );
		
		disp.setModo(new ModoEncendido( disp.getConsumoEncendidoHora() ));
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
        
	}
	
	public String toString() {
		return "Modo Ahorro de Energia";
	}

	
	//getters y setters
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
