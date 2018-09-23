package reglasYActuadores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dispositivos.DispositivoInteligente;


@Entity
@DiscriminatorValue("luz")
public class CambiarIntensidadLuzCommand extends ActuadorBase {


	
	public CambiarIntensidadLuzCommand(DispositivoInteligente d) {
		dispositivo=d;
	}
	
	@Override
	public void ejecutarAccion() {
		dispositivo.cambiarIntensidadLuz();		
	}



}
