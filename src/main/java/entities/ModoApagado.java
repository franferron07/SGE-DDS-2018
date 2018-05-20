package entities;

import org.apache.log4j.LogManager;

public class ModoApagado extends Modo {

	
	 public ModoApagado() {
		 super();
		}
	 
	@Override
	public boolean encendido() {
		return false;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {	
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta apagarse del modo Apagado");
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		disp.setModo(new ModoEncendido());
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta encenderse del modo Apagado");
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta ahorrarseEnergia del modo Apagado");
	}
	
	public String toString() {
		return "Modo Apagado";
	}

}
