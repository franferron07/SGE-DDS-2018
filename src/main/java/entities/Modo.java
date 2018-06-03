package entities;

import java.time.LocalDateTime;

public interface Modo {
	

	public boolean encendido();
	
	public void apagarse(DispositivoInteligente disp);

	public void encenderse(DispositivoInteligente disp);

	public void ahorrarseEnergia(DispositivoInteligente disp);
	
	
	//me da el consumo en un periodo del modo.
	public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal );

	//registra el consumo en los modos. en modo apagado no hace nada.
	public void registrarConsumo(LocalDateTime inicio, LocalDateTime fin, float consumo);

	//me indica si en un periodo se cumple o no el intervalo
	public boolean cumpleIntervalo(LocalDateTime desde, LocalDateTime hasta);
	
	
	

}
