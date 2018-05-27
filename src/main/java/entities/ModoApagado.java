package entities;

public class ModoApagado extends Modo {	
	 //constructor
	 public ModoApagado() {
		
	}
	 
	@Override
    public float consumoEnPeriodo(){		
    	return 0;//Valor por default,se supone que en la bd tendria que estar este valor
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
		disp.agregarLogModo( disp.getModo().clone() );		
		disp.setModo(new ModoEncendido());       
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo().clone() );		
		disp.setModo(new ModoAhorroEnergia());       
	}
	
	public String toString() {
		return "Modo Apagado";
	}
	
	@Override
	public ModoApagado clone() {
		ModoApagado modoApagado = new ModoApagado();
		modoApagado.setConsumoKW(this.getConsumoKW());
		modoApagado.setFechaHoraInicio(this.getFechaHoraInicio());
		modoApagado.setFechaHoraFin(this.getFechaHoraFin());
		return modoApagado;
	}
}
