package entities;

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
        System.out.println("Se ejecuta apagarse del modo encendido");
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
        System.out.println("Se ejecuta encenderse del modo encendido");
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		disp.setModo(new ModoAhorroEnergia());//No se menciona en el tp,pero esun estado posible        
		System.out.println("Se ejecuta ahorrarseEnergia del modo encendido");
	}
	public String toString() {
		return "Modo Encendido";
	}

}
