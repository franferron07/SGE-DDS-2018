package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ModoAhorroEnergia extends ModoConConsumo {

	
	//constructor
	public ModoAhorroEnergia() {
		
		this.fechaHoraInicio= LocalDateTime.now();
		this.consumos = new ArrayList<Consumo>();
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
	
	

	public String toString() {
		return "Modo Ahorro de Energia";
	}

	
	
	
	
}
