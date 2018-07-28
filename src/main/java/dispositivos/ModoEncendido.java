package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class ModoEncendido extends ModoConConsumo{

	
	//constructor
    public ModoEncendido() {
    	
    	this.fechaHoraInicio= LocalDateTime.now();
    	this.consumos = new ArrayList<Consumo>();
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
	
	public String toString() {
		return "Modo Encendido";
	}
	


}
