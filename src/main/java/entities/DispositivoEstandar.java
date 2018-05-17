package entities;

public class DispositivoEstandar implements Dispositivo {
	
	private String nombre;
	private float kwHora;
	private DispositivoInteligente adaptador;
	
	
	//constructor
	public DispositivoEstandar(){
		adaptador=null;
	}
	
	@Override
	public boolean esInteligente() {
		
		if( this.adaptador == null ) return false;
		
		return true;
	}

	public boolean estaEncendido(){	
		boolean estaEncen=false;
		if( this.adaptador != null )
			estaEncen=adaptador.estaEncendido();
		return estaEncen;
	}
	
	//obtengo cuanto consume en el dia el dispositivo. Duda si estimativoDeHora se obtiene por parametro o es atributo
	public float consumoDia(long estimativoUso){
		
		return this.kwHora * estimativoUso ; 
	}
	
	
	public void quitarAdaptador(){
		
		this.adaptador=null;
	}
	
	//getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getKwHora() {
		return kwHora;
	}

	public void setKwHora(float kwHora) {
		this.kwHora = kwHora;
	}

	public DispositivoInteligente getAdaptador() {
		return adaptador;
	}

	public void setAdaptador(DispositivoInteligente adaptador) {
		this.adaptador = adaptador;
	}

}
