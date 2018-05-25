package entities;

public class DispositivoInteligente extends Dispositivo {
	
	private Modo modo;//Aqui se usa el patron State.
	private int idDispositivo;
	private DispositivoEstandar estandar;
	
	
	@Override
	public float consumoPeriodo(int dias) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean esInteligente() {
		return true;
	}
	
	//me da el consumo del dispositivo en las ultimas N horas. 
	public float consumoUltimasNHoras(float horas){
		
		return 0;
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
	
	
	
	//constructor
    public DispositivoInteligente(Modo m) {
		this.setModo(m);
		this.estandar=null;
	}

    
    //getters y setters
    
	
	public DispositivoEstandar getEstandar() {
		return estandar;
	}

	public void setEstandar(DispositivoEstandar estandar) {
		this.estandar = estandar;
	}
	
	public void setModo(Modo modo) {
		this.modo = modo;
	}

	public Modo getModo() {
		return modo;
	}
	
	public int getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}




	
	
	
}
