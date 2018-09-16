package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ahorro_energia")
public class ModoAhorroEnergia extends ModoConConsumo {

	
	//constructor
	public ModoAhorroEnergia() {
		
		this.fechaHoraInicio= LocalDateTime.now();
		this.consumos = new ArrayList<Consumo>();
	}
	
		

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
        
	}

	public String toString() {
		return "Modo Ahorro de Energia";
	}

	
	
	
	
}
