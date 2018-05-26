package entities;

public class ModoApagado implements Modo {

	
	
	 //constructor
	 public ModoApagado() {
		
	}
	 
	@Override
    public float consumoEnPeriodo(){
    
		
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
	
	public String toString() {
		return "Modo Apagado";
	}
	
	
	

}
