package entities;

import org.apache.log4j.LogManager;

public class ModoAhorroEnergia extends Modo {

	 public ModoAhorroEnergia() {
		 super();
		}
	 
	@Override
	public boolean encendido() {
		return true;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {	
		disp.setModo(new ModoApagado());//No se menciona en el tp,pero esun estado posible
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta apagarse del Modo Ahorro de Energia");
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		disp.setModo(new ModoEncendido());
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta encenderse del Modo Ahorro de Energia");
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta ahorrarseEnergia del Modo Ahorro de Energia");
	}
	
	public String toString() {
		return "Modo Ahorro de Energia";
	}

}
