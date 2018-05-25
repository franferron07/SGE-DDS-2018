package entities;

public class DispositivoInteligente extends Dispositivo {
	
	private Modo modo;//Aqui se usa el patron State.
	private int idDispositivo;
	
	
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
	
	
	public void cambiarTemperaturaActuador() {
		 System.out.println("Cambiando la temperatura");
	}

	public void cambiarIntensidadLuz() {
		System.out.println("Cambiando la intensidad de la luz");
	}

	public int getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	//calcula la energia consumida en un periodo de dias . DUDA
	public float energiaConsumidaPeriodo(int dias){		
		return this.consumoKWHoras(24)*dias;
	}
	
	@Override
	public float consumoKWHoras(int horas) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
