package entities;

import org.apache.log4j.LogManager;

public class ModoEncendido extends Modo{

	
    public ModoEncendido() {
		super();
	}

	@Override
	public boolean encendido() {
		return true;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {		
		disp.setModo(new ModoApagado());
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta apagarse del modo encendido");
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta encenderse del modo encendido");
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		disp.setModo(new ModoAhorroEnergia());//No se menciona en el tp,pero esun estado posible
		LogManager.getLogger(this.getClass().getName()).info("Se ejecuta ahorrarseEnergia del modo encendido");
	}
	public String toString() {
		return "Modo Encendido";
	}

}
