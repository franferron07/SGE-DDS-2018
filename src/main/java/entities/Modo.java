package entities;

import java.time.LocalDateTime;

public interface Modo {
	

	public boolean encendido();
	
	public void apagarse(DispositivoInteligente disp);

	public void encenderse(DispositivoInteligente disp);

	public void ahorrarseEnergia(DispositivoInteligente disp);
	
	public void registrarConsumo( LocalDateTime inicio , LocalDateTime fin , float consumo );
	
	//me da el consumo en un periodo del modo.
	public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal );
	
	//me indica si el modo cumple con ese parametro, si alguna de las fechas del modo esta en ese intervalo devuelve true. Si es modo Apagado es false
	public boolean cumpleIntervalo( LocalDateTime fechaInicial , LocalDateTime fechaFinal );
	

}
