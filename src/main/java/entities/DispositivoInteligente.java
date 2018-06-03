package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DispositivoInteligente extends Dispositivo {
	
	private Modo modo;
	private List<Modo> logModos ;
	private int idDispositivo;
	private DispositivoEstandar estandar;
	
	
	
	//constructor
    public DispositivoInteligente(Modo m) {
		this.setModo(m);
		this.estandar=null;
		this.logModos =  new ArrayList<Modo>();
	}
	
	
	@Override
	public boolean esInteligente() {
		return true;
	}
	
	@Override
	public float consumoPeriodo(LocalDateTime desde , LocalDateTime hasta) {
		
		//filtro los modos
		List<ModoConConsumo> modosFiltrados = filtrarModosEnPeriodo( desde , hasta );
		
		//calculo el consumo
		double consumoTotal = modosFiltrados.stream().mapToDouble(disp -> disp.consumoEnPeriodo( desde , hasta )).sum();
		
		return (float) consumoTotal;
	}
	
	//filtra los modos que entren en el intervalo pedido
	@SuppressWarnings("unchecked")
	private List<ModoConConsumo> filtrarModosEnPeriodo(LocalDateTime desde, LocalDateTime hasta) {
		
		return (List<ModoConConsumo>) this.logModos.stream().filter( m -> m.cumpleIntervalo(desde,hasta) );	
	}
	
	//me da el consumo del dispositivo en las ultimas N horas. 
	public float consumoUltimasNHoras(float horas){
		
		return 0;
	}
	
	//metodo desde el dispositivo hardware para avisar que se registro el consumo
	public void avisoConsumo( LocalDateTime inicio , LocalDateTime fin , float consumo ){
		
		this.modo.registrarConsumo( inicio , fin , consumo );
		
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
	
	public void agregarLogModo( Modo modo ){
		this.logModos.add(modo);
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

	public List<Modo> getLogModos() {
		return logModos;
	}

	public void setLogModos(List<Modo> logModos) {
		this.logModos = logModos;
	}



	
	
}
