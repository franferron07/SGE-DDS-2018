package reglasYActuadores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dispositivos.DispositivoInteligente;


@Entity
@DiscriminatorValue("temperatura")
public class CambiarTemperaturaActuadorCommand extends ActuadorBase{
	

	
	public CambiarTemperaturaActuadorCommand(DispositivoInteligente d) {
		dispositivo=d;
	}
	
	
	@Override
	public void ejecutarAccion() {
		dispositivo.cambiarTemperaturaActuador();		
	}


}
