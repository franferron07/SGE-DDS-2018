package entities;

public class ModoAhorroEnergia extends Modo {
	
	//constructor
	public ModoAhorroEnergia() {
		 
	}
	
	@Override
    public float consumoEnPeriodo(){  
    	return this.getConsumoKW();//Este va hacer el valor de un registro,en su periodo fechaHoraInicio y fechaHoraFin
    } 
	 
	@Override
	public boolean encendido() {
		return true;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {	
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo().clone());		
		disp.setModo(new ModoApagado());		
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo().clone());		
		disp.setModo(new ModoEncendido());
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {
        
	}
	
	public String toString() {
		return "Modo Ahorro de Energia";
	}
	
	@Override
	public ModoAhorroEnergia clone() {
		ModoAhorroEnergia modoAhorroEnergia = new ModoAhorroEnergia();
		modoAhorroEnergia.setConsumoKW(this.getConsumoKW());
		modoAhorroEnergia.setFechaHoraInicio(this.getFechaHoraInicio());
		modoAhorroEnergia.setFechaHoraFin(this.getFechaHoraFin());
		return modoAhorroEnergia;
	}

}
