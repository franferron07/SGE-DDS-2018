package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("encendido")
public class ModoEncendido extends Modo{

	
    
	public ModoEncendido(DispositivoInteligente di) {
		super(di);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean encendido() {
		return true;
	}

	//metodo que se utiliza para filtrar los modos en el DI. Con que una de las fechas este en el intervalo , devuelve true
	@Override
	public boolean cumpleIntervalo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
		
		if(  ( this.fechaHoraInicio.compareTo(fechaInicial) >= 0  && this.fechaHoraInicio.compareTo(fechaFinal) <= 0   ) || 
		     ( this.fechaHoraFin.compareTo(fechaInicial) >= 0  && this.fechaHoraFin.compareTo(fechaFinal) <= 0 )  )  
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public void registrarConsumo(LocalDateTime inicio, LocalDateTime fin , float consumo) {
		
		Consumo consumoModo = new Consumo( this , inicio , fin , consumo );
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
	public List<Consumo> filtrarConsumosEnPeriodo(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		
		Stream<Consumo> consumos = this.consumos.stream().filter(c -> c.cumplePeriodoConsumo(fechaInicial,fechaFinal));
		//conviverto la stream en list
		return consumos.collect(Collectors.toList());
	}

	public void agregarConsumo(Consumo consumoModo) {
		
		this.consumos.add(consumoModo);
	}

	public int cantidadConsumos(){
		
		return this.consumos.size();
	}
	
	//getters y setters


	public List<Consumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	
	@Override
	public void encenderse(DispositivoInteligente disp) {
       
	}
	
	public String toString() {
		return "Modo Encendido";
	}
	


}
