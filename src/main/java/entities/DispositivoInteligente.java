package entities;

public class DispositivoInteligente extends Dispositivo {
	
	private Modo modo;//Aqui se usa el patron State
	private ActuadorBase actuador;
	
	
    public DispositivoInteligente(Modo m) {
		this.setModo(m);
	}

	public boolean esInteligente() {
		return true;
	}

	public void setModo(Modo modo) {
		this.modo = modo;
	}
	
	public Modo getModo() {
		return modo;
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
	public float consumoKmHora(long horas){
		return this.getKwHora() * horas ; 
	}
	
	//calcula la energia consumida en un periodo de dias . DUDA
	public float energiaConsumidaPeriodo(int dias){
	
		
		return 0;
	}
	
	public void ejecutar() {
		
	}

}
