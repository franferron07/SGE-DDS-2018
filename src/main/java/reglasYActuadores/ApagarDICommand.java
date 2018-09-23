package reglasYActuadores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue("apagar")
public class ApagarDICommand extends ActuadorBase {

	
	public ApagarDICommand(DispositivoInteligente di) {
		dispositivo = di;
	}
	
	@Override
	public void ejecutarAccion() {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
