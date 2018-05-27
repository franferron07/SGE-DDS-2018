package entities;

public class ModoEncendido extends Modo{
	//constructor
    public ModoEncendido() {
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
		disp.agregarLogModo( disp.getModo().clone() );				
		disp.setModo(new ModoApagado());
	}

	@Override
	public void encenderse(DispositivoInteligente disp) {
       
	}

	@Override
	public void ahorrarseEnergia(DispositivoInteligente disp) {		
		//agrego log de modo antes de cambiarlo
		disp.agregarLogModo( disp.getModo().clone() );		
		disp.setModo(new ModoAhorroEnergia());   	
	}
	
	public String toString() {
		return "Modo Encendido";
	}

	@Override
	public ModoEncendido clone() {
		ModoEncendido modoEncendido = new ModoEncendido();
		modoEncendido.setConsumoKW(this.getConsumoKW());
		modoEncendido.setFechaHoraInicio(this.getFechaHoraInicio());
		modoEncendido.setFechaHoraFin(this.getFechaHoraFin());
		return modoEncendido;
	}

}
