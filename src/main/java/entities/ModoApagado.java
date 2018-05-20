package entities;

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
        System.out.println("Se ejecuta apagarse del modo Apagado");
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		disp.setModo(new ModoEncendido());
        System.out.println("Se ejecuta encenderse del modo Apagado");
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
        System.out.println("Se ejecuta ahorrarseEnergia del modo Apagado");
	}
	
	public String toString() {
		return "Modo Apagado";
	}

}
