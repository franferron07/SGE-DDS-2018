package entities;

public class ModoEncendido extends Modo{

	
	
	@Override
	public boolean encendido() {
		return true;
	}

	//no hace nada
	@Override
	public void encenderse(DispositivoInteligente disp) {
		
	}

	

}
