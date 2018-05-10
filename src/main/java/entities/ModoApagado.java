package entities;

public class ModoApagado extends Modo {

	
	
	@Override
	public boolean encendido() {
		return false;
	}

	//no hace nada
	@Override
	public void apagarse(DispositivoInteligente disp) {

	}


}
