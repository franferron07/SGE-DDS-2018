package entities;

import java.time.LocalDateTime;

public class ModoApagado implements Modo {

	
	
	 //constructor
	 public ModoApagado() {
		
	}
	 
	@Override
	public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
    
    	return 0;
    } 
	 
	@Override
	public boolean encendido() {
		return false;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {	
        
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo() );
		
		disp.setModo(new ModoEncendido());       
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo() );
		
		disp.setModo(new ModoAhorroEnergia());
        
	}
	
	//en apagado no hace nada
	@Override
	public void registrarConsumo(LocalDateTime inicio, LocalDateTime fin , float consumo) {
	
	}
	
	@Override
	public boolean cumpleIntervalo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
		
		return false;
	}
	
	
	public String toString() {
		return "Modo Apagado";
	}
	
	
	

}
