package entities;

import java.time.LocalDateTime;

public interface Modo {
	

	public boolean encendido();
	
	public void apagarse(DispositivoInteligente disp);

	public void encenderse(DispositivoInteligente disp);

	public void ahorrarseEnergia(DispositivoInteligente disp);
	
	
	//me da el consumo en un periodo del modo.
	public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal );
	
	
	

}
