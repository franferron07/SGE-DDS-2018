package entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class ModoEncendido extends Modo{
	//constructor
     public ModoEncendido(float consumo) {
    	this.setFechaHoraInicio(LocalDateTime.now());
    	this.setConsumoKW(consumo);
	}
    
    @Override
   public float consumoEnPeriodo(){
    
    	long horas;
    	horas = periodoEnHoras();
    	
    	return this.getConsumoKW() * horas;    	
    }
    
    public long periodoEnHoras(){
    	
    	Duration duracion = Duration.between(this.getFechaHoraInicio(),this.getFechaHoraFin());
    	long horas = duracion.getSeconds()/3600;  //obtengo la diferencia del periodo en horas.
    	
    	return horas;
    }

	@Override
	public boolean encendido() {
		return true;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {		
			//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		disp.agregarLogModo( disp.getModo().clone() );				
		disp.setModo(new ModoApagado());
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
       
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {		
	//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		disp.agregarLogModo( disp.getModo().clone() );		
		disp.setModo(new ModoAhorroEnergia(disp.getConsumoAhorroHora()));   	
	}
	
	public String toString() {
		return "Modo Encendido";
	}

	@Override
	public ModoEncendido clone() {
		ModoEncendido modoEncendido = new ModoEncendido(this.getConsumoKW());
		modoEncendido.setConsumoKW(this.getConsumoKW());
		modoEncendido.setFechaHoraInicio(this.getFechaHoraInicio());
		modoEncendido.setFechaHoraFin(this.getFechaHoraFin());
		return modoEncendido;
	}

}
