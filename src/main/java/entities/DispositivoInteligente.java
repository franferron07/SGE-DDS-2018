package entities;

public class DispositivoInteligente implements Dispositivo {
	
	private String nombre;
	private Modo modo;
	
	
	
	@Override
	public boolean esInteligente() {
		return true;
	}


	public boolean estaEncendido(){
		
		return this.modo.encendido();
	}
	
	public boolean estaApagado(){
		
		return !this.modo.encendido();
	}
	
	
	public void apagar(){
		this.modo.apagarse(this);
	}
	
	public void encender(){
		this.modo.encenderse(this);
	}
	
	public void ahorrarEnergia(){
		this.modo.ahorrarseEnergia(this);
	}
	
	//calcula cuando energia se consumio en las ultimas n horas
	public float energiaConsumidaTiempo(float horas){
	
		return 0;
	}
	
	//calcula la energia consumida en un periodo de dias . DUDA
	public float energiaConsumidaPeriodo(int dias){
	
		
		return 0;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Modo getModo() {
		return modo;
	}

	public void setModo(Modo modo) {
		this.modo = modo;
	}
	

}
