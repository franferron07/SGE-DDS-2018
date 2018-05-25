package entities;

public class DispositivoEstandar extends Dispositivo {
	

	private DispositivoInteligente adaptador;
	
	
	//constructor
	public DispositivoEstandar(){
		adaptador=null;
	}
	
	@Override
	public boolean esInteligente() {
		
		if( this.adaptador != null ) return true;
		
		return false;
	}

	public boolean estaEncendido(){	
		boolean estaEncen=false;
		if( this.adaptador != null )
			estaEncen=adaptador.estaEncendido();
		return estaEncen;
	}
	
	//obtengo cuanto consume en el dia el dispositivo.
	public float consumoKWHoras(int horas){		
		return this.getKwHora() * horas ; 
	}
	
	
	public void quitarAdaptador(){
		
		this.adaptador=null;
	}

	public DispositivoInteligente getAdaptador() {
		return adaptador;
	}

	public void setAdaptador(DispositivoInteligente adaptador) {
		this.adaptador = adaptador;
	}


}
