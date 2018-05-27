package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DispositivoInteligente extends Dispositivo {
	
	private Modo modo;
	private List<Modo> logModos ;
	private int idDispositivo;
	private DispositivoEstandar estandar;
	
	//parametros de consumo en kw de los dispositivos en horas. 
	private float consumoEncendidoHora;
	private float consumoAhorroHora;
	
	
	@Override
	public float consumoPeriodo(LocalDateTime desde , LocalDateTime hasta) {
		
		List<Modo> modosFiltrados = filtrarModosEnPeriodo( desde , hasta );
		
		modosFiltrados.stream().forEach(disp -> disp.consumoEnPeriodo() );
		
		return 0;
	}
	
	private List<Modo> filtrarModosEnPeriodo(LocalDateTime desde, LocalDateTime hasta) {
		
		
		
		return null;
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
	
	public void agregarLogModo( Modo modo ){
		this.logModos.add(modo);
	}
	
	
	
	//constructor
    public DispositivoInteligente(Modo m) {
		this.setModo(m);
		this.estandar=null;
		this.logModos =  new ArrayList<Modo>();
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

	public float getConsumoEncendidoHora() {
		return consumoEncendidoHora;
	}

	public void setConsumoEncendidoHora(float consumoEncendidoHora) {
		this.consumoEncendidoHora = consumoEncendidoHora;
	}

	public float getConsumoAhorroHora() {
		return consumoAhorroHora;
	}

	public void setConsumoAhorroHora(float consumoAhorroHora) {
		this.consumoAhorroHora = consumoAhorroHora;
	}


	
	
}
