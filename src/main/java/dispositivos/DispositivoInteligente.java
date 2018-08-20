package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DispositivoInteligente extends DispositivoUsuario {
	
	private Modo modo;
	private List<Modo> logModos ;
	private int idDispositivo;
	private DispositivoEstandar estandar;
	
	//constructor
    public DispositivoInteligente(Modo m , DispositivoDetalle disp_detalle) {
		this.setModo(m);
		this.estandar=null;
		this.logModos =  new ArrayList<Modo>();
		detalle = disp_detalle;
	}
	
	
	@Override
	public boolean esInteligente() {
		return true;
	}
	
	
	@Override
	public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
		
		float consumo = consumoPeriodo(desde,hasta);
		double horasDeConsumo  = consumo / detalle.getConsumoKwHora();
		
		return horasDeConsumo;
	}
	
	
	@Override
	public float consumoPeriodo(LocalDateTime desde , LocalDateTime hasta) {
		
		ModoConConsumo modoActual;
		float consumoActual=0;
		double consumoTotal=0;
		
		//filtro los modos
		List<Modo> modosFiltrados = filtrarModosEnPeriodo( desde , hasta );
		
		//calculo el consumo
		consumoTotal = modosFiltrados.stream().mapToDouble(disp -> disp.consumoEnPeriodo( desde , hasta )).sum();
		
		//sumo lo consumido por el modo actual
		if( this.estaEncendido() ){
			
			modoActual = (ModoConConsumo) this.getModo();
			//chequeo si esta en intervalo
			if(modoActual.cumpleIntervalo(desde, hasta)){
				consumoActual = modoActual.consumoEnPeriodo(desde, hasta);
			}
		
			consumoTotal = consumoTotal + consumoActual;
		}
		
		return (float) consumoTotal;
	}
	
	//filtra los modos que entren en el intervalo pedido
	public List<Modo> filtrarModosEnPeriodo(LocalDateTime desde, LocalDateTime hasta) {
		
		Stream<Modo> modos = this.logModos.stream().filter( m -> m.cumpleIntervalo(desde,hasta) );
		
		return modos.collect(Collectors.toList());
	}
	
	//me da el consumo del dispositivo en las ultimas N horas. 
	public float consumoUltimasNHoras(int horas){
		
		int horasNegativo = -1*horas;
		LocalDateTime inicial = LocalDateTime.now().plusHours(horasNegativo);
		LocalDateTime fin = LocalDateTime.now();
		
		float consumoNHoras = this.consumoPeriodo(inicial, fin);
		
		return consumoNHoras;
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
	
	public int cantidadLogModo(){
	
		return this.logModos.size();
	}

	
	public void cambiarTemperaturaActuador() {
         System.out.println("Cambiando la temperatura");
    }

    public void cambiarIntensidadLuz() {
        System.out.println("Cambiando la intensidad de la luz");
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
