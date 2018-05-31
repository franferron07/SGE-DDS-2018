package entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ModoEncendido implements Modo{

	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private List<ConsumoModo> consumos;
	
	
	
	//constructor
    public ModoEncendido() {
    	
    	this.fechaHoraInicio= LocalDateTime.now();
    	this.consumos = new ArrayList<ConsumoModo>();
	}
    
    @Override
    public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
    
    	long horas;
    	horas = periodoEnHoras();
    	
    	return 0;
    }
    
    public long periodoEnHoras(){
    	
    	Duration duracion = Duration.between(fechaHoraInicio, fechaHoraFin);
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
		disp.agregarLogModo( disp.getModo() );
				
		disp.setModo(new ModoApagado());
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
       
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		disp.agregarLogModo( disp.getModo() );
		
		disp.setModo(new ModoAhorroEnergia());   	
	}
	

	@Override
	public void registrarConsumo(LocalDateTime inicio, LocalDateTime fin , float consumo) {
		
		ConsumoModo consumoModo = new ConsumoModo( inicio , fin , consumo );
		agregarConsumo( consumoModo );
	}
	
	private void agregarConsumo(ConsumoModo consumoModo) {
		
		this.consumos.add(consumoModo);
	}
	
	//metodo que se utiliza para filtrar los modos en el DI. Con que una de las fechas este en el intervalo , devuelve true
	public boolean cumpleIntervalo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
		
		if(  ( this.fechaHoraInicio.compareTo(fechaInicial) >= 0  && this.fechaHoraInicio.compareTo(fechaFinal) < 0   ) || 
		     ( this.fechaHoraFin.compareTo(fechaInicial) >= 0  && this.fechaHoraFin.compareTo(fechaFinal) < 0 )  )  
		{
			return true;
		}
		
		return false;
	}
	
	
	public String toString() {
		return "Modo Encendido";
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


}
