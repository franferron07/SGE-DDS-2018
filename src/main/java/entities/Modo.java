package entities;

public abstract class Modo {
	
	
	abstract boolean encendido();


	public void apagarse(DispositivoInteligente disp) {
		
		disp.setModo(new ModoApagado());
	}


	public void encenderse(DispositivoInteligente disp) {
		
		disp.setModo(new ModoEncendido());
	}

	public void ahorrarseEnergia(DispositivoInteligente disp) {
		
		disp.setModo(new ModoAhorroEnergia());
	}
	

}
