package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("encendido")
public class ModoEncendido extends ModoConConsumo{

	
	//constructor
    public ModoEncendido() {
    	
    	this.fechaHoraInicio= LocalDateTime.now();
    	this.consumos = new ArrayList<Consumo>();
	}
	
	@Override
	public void encenderse(DispositivoInteligente disp) {
       
	}
	
	public String toString() {
		return "Modo Encendido";
	}
	


}
