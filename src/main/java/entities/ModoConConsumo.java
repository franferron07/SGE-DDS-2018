package entities;

import java.time.LocalDateTime;
import java.util.List;

public abstract class ModoConConsumo implements Modo {
	
	protected LocalDateTime fechaHoraInicio;
	protected LocalDateTime fechaHoraFin;
	protected List<Consumo> consumos;
	
	public abstract void encenderse(DispositivoInteligente disp);
	public abstract void ahorrarseEnergia(DispositivoInteligente disp);
	

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
	
	//metodo que se utiliza para filtrar los modos en el DI. Con que una de las fechas este en el intervalo , devuelve true
	@Override
	public boolean cumpleIntervalo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
		
		if(  ( this.fechaHoraInicio.compareTo(fechaInicial) >= 0  && this.fechaHoraInicio.compareTo(fechaFinal) < 0   ) || 
		     ( this.fechaHoraFin.compareTo(fechaInicial) >= 0  && this.fechaHoraFin.compareTo(fechaFinal) < 0 )  )  
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public void registrarConsumo(LocalDateTime inicio, LocalDateTime fin , float consumo) {
		
		Consumo consumoModo = new Consumo( inicio , fin , consumo );
		this.agregarConsumo( consumoModo );
	}
	
	@Override
	public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
		
		//filtrarConsumoDePeriodo
		List<Consumo> consumosFiltrados = this.filtrarConsumosEnPeriodo(fechaInicial , fechaFinal);
		
		//calculo el consumo
		double consumoTotal = consumosFiltrados.stream().mapToDouble(consumo -> consumo.getConsumo()).sum();
    
    	return (float) consumoTotal;
    } 
	
	//filtro los consumos que cumplen el periodo
	@SuppressWarnings("unchecked")
	public List<Consumo> filtrarConsumosEnPeriodo(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		
		List<Consumo> consumos = (List<Consumo>) this.consumos.stream().filter(c -> c.cumplePeriodoConsumo(fechaInicial,fechaFinal));
		return consumos;
	}

	public void agregarConsumo(Consumo consumoModo) {
		
		this.consumos.add(consumoModo);
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

	public List<Consumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}
	

}
