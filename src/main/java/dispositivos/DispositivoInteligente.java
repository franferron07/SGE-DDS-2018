package dispositivos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import reglasYActuadores.ActuadorBase;


@Entity
@DiscriminatorValue("inteligente")
public class DispositivoInteligente extends DispositivoUsuario {
	

	//contiene los modos, el ultimo modo es el modo actual
	@OneToMany(mappedBy="id" , cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
	private List<Modo> logModos ;

	@ManyToOne
	@JoinColumn( name="estandar_id" , referencedColumnName="id" )
	private DispositivoEstandar estandar;
	@ManyToOne
	@JoinColumn( name="actuador_id" , referencedColumnName="id" )
	private ActuadorBase accionaAutomaticaOptimizador; //activara esta accion el optimizador de horas al dispositivo
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
	private DispositivoUsuario di_padre;
	
	//constructor
    public DispositivoInteligente(Modo m , DispositivoDetalle disp_detalle) {

		this.estandar=null;
		this.logModos =  new ArrayList<Modo>();
		this.logModos.add(m);
		this.detalle = disp_detalle;
		this.activado = true;
	}
    
    //constructor sin modo osea apagado.
    public DispositivoInteligente(DispositivoDetalle disp_detalle) {

		this.estandar=null;
		this.logModos =  new ArrayList<Modo>();
		this.logModos.add(new ModoApagado());
		this.detalle = disp_detalle;
		this.activado = true;
	}
    
    //constructor
    public DispositivoInteligente() {

		this.estandar=null;
		this.logModos =  new ArrayList<Modo>();
		this.logModos.add(new ModoApagado());
		this.activado = true;
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
		
		double consumoTotal=0;
		
		//quito los modos apagados
		//List<Modo> modosConConsumo = filtrarModosConConsumo( this.logModos );
		
		
		//filtro los modos
		List<Modo> modosFiltrados = filtrarModosEnPeriodo( desde , hasta , this.logModos );
		
		//calculo el consumo
		consumoTotal = modosFiltrados.stream().mapToDouble(disp -> disp.consumoEnPeriodo( desde , hasta )).sum();
		
	/*
		//sumo lo consumido por el modo actual
		if( this.estaEncendido() ){
			
			modoActual = (ModoConConsumo) this.getModo();
			//chequeo si esta en intervalo
			if(modoActual.cumpleIntervalo(desde, hasta)){
				consumoActual = modoActual.consumoEnPeriodo(desde, hasta);
			}
		
			consumoTotal = consumoTotal + consumoActual;
		}*/
		
		return (float) consumoTotal;
	}
	
	
	//filtro los modos con consumo
	public List<Modo> filtrarModosConConsumo(List<Modo> modos) {
		
		Stream<Modo> modosFilt = modos.stream().filter( m -> m.encendido());
		
		return modosFilt.collect(Collectors.toList());
	}
	

	//filtra los modos que entren en el intervalo pedido
	public List<Modo> filtrarModosEnPeriodo(LocalDateTime desde, LocalDateTime hasta , List<Modo> modos) {
		
		Stream<Modo> modosFilt = modos.stream().filter( m -> m.cumpleIntervalo(desde,hasta) );
		
		return modosFilt.collect(Collectors.toList());
	}
	
	//me da el consumo del dispositivo en las ultimas N horas. 
	public float consumoUltimasNHoras(int horas){
		
		int horasNegativo = -1*horas;
		/* armo intervalo */
		LocalDateTime inicial = LocalDateTime.now().plusHours(horasNegativo);
		LocalDateTime fin = LocalDateTime.now();
		
		float consumoNHoras = consumoPeriodo(inicial, fin);
		
		return consumoNHoras;
	}
	
	//metodo desde el dispositivo hardware para avisar que se registro el consumo
	public void avisoConsumo( LocalDateTime inicio , LocalDateTime fin , float consumo ){
		
		modoActual().registrarConsumo(inicio, fin, consumo);
	}
	
	//me devuelve el modo actual(ultimo elemento de la lista) si la lista vacia devuelve null
	public Modo modoActual() {
		
		if( !this.getLogModos().isEmpty() ){
		return this.getLogModos().get(this.getLogModos().size() -1 );
		}
		
		return null;
	}

	public boolean estaEncendido(){	
		return modoActual().encendido();
	}
	
	public boolean estaApagado(){	
		return !modoActual().encendido();
	}
	
	public void apagar(){
		modoActual().apagarse(this);
	}
	
	public void encender(){
		modoActual().encenderse(this);
	}
	
	public void ahorrarEnergia(){
		modoActual().ahorrarseEnergia(this);
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

	public List<Modo> getLogModos() {
		return logModos;
	}

	public void setLogModos(List<Modo> logModos) {
		this.logModos = logModos;
	}

	public ActuadorBase getAccionaAutomaticaOptimizador() {
		return accionaAutomaticaOptimizador;
	}

	public void setAccionaAutomaticaOptimizador(ActuadorBase accionaAutomaticaOptimizador) {
		this.accionaAutomaticaOptimizador = accionaAutomaticaOptimizador;
	}

	public void ejecutarAccionAutomatica() {
		
		if( accionaAutomaticaOptimizador != null){
			accionaAutomaticaOptimizador.ejecutarAccion();
		}
		
	}


	



	
	
}
