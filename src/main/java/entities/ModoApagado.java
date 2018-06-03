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
	public boolean cumpleIntervalo(LocalDateTime desde, LocalDateTime hasta) {

		return false;
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
	
	
	public String toString() {
		return "Modo Apagado";
	}




	@Override
	public void registrarConsumo(LocalDateTime inicio, LocalDateTime fin, float consumo) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	

}
