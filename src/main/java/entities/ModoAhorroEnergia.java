package entities;


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
        System.out.println("Se ejecuta apagarse del Modo Ahorro de Energia");
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		disp.setModo(new ModoEncendido());
        System.out.println("Se ejecuta encenderse del Modo Ahorro de Energia");
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
        System.out.println("Se ejecuta ahorrarseEnergia del Modo Ahorro de Energia");
	}
	
	public String toString() {
		return "Modo Ahorro de Energia";
	}

}
