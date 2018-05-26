package entities;

public interface Modo {
	

	public boolean encendido();
	
	public float consumoEnPeriodo();

	public void apagarse(DispositivoInteligente disp);

	public void encenderse(DispositivoInteligente disp);

	public void ahorrarseEnergia(DispositivoInteligente disp);
		

}
