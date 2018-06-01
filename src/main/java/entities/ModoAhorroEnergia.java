package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModoAhorroEnergia implements Modo {

	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private List<ConsumoModo> consumos;
	
	//constructor
	public ModoAhorroEnergia() {
		
		this.fechaHoraInicio= LocalDateTime.now();
		this.consumos = new ArrayList<ConsumoModo>();
	}
	
	@Override
	public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
		
		//filtrarConsumoDePeriodo
		List<ConsumoModo> consumosFiltrados = filtrarConsumosEnPeriodo(fechaInicial , fechaFinal);
		
		//calculo el consumo
		double consumoTotal = consumosFiltrados.stream().mapToDouble(consumo -> consumo.getConsumo()).sum();
    
    	return (float) consumoTotal;
    } 
	 
	//filtro los consumos que cumplen el periodo
	private List<ConsumoModo> filtrarConsumosEnPeriodo(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		
		List<ConsumoModo> consumos = (List<ConsumoModo>) this.consumos.stream().filter(c -> c.cumplePeriodoConsumo(fechaInicial,fechaFinal));
		return consumos;
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
		
		disp.setModo(new ModoEncendido());
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
        
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


	public List<ConsumoModo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<ConsumoModo> consumos) {
		this.consumos = consumos;
	}
	
	
}
