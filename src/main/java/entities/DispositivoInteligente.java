package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.OrdenaFechaInicioModo;

public class DispositivoInteligente extends Dispositivo {
	
	private Modo modo;
	private List<Modo> logModos ;
	private int idDispositivo;
	private DispositivoEstandar estandar;
	
	//parametros de consumo en kw de los dispositivos en horas. 
	private float consumoEncendidoHora;
	private float consumoAhorroHora;

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
		
		List<Modo> modosFiltrados = filtrarModosEnPeriodo( desde , hasta );
		
		modosFiltrados.stream().forEach(disp -> disp.consumoEnPeriodo() );
		
		return 0;
	}
	
	private List<Modo> filtrarModosEnPeriodo(LocalDateTime desde, LocalDateTime hasta) {
		
		
		
		return null;
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
		Collections.sort(logModos, new OrdenaFechaInicioModo());
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

	 public void cambiarTemperaturaActuador() {
         System.out.println("Cambiando la temperatura");
    }

    public void cambiarIntensidadLuz() {
        System.out.println("Cambiando la intensidad de la luz");
    }





















    /**
     * 
	@Override
	public float consumoPeriodo(int dias) {
		float consumo=0;
		Date fechaFin = null;
		List<Modo> lista=getLogModos();//Viene Ordenado de mayor fecha a menor fecha
		if (!lista.isEmpty()) {
			fechaFin=sumarRestarDiasFecha(lista.get(0).getFechaHoraFin(),dias);
		}		
		for (int i = 1; i <= lista.size(); i++) {
			Calendar calendarInicio = Calendar.getInstance();
			calendarInicio.setTime(lista.get(i).getFechaHoraInicio());
			Date fechaInicio=calendarInicio.getTime();			
			if (fechaInicio.after(fechaFin)) {
				consumo=consumo+lista.get(i).getConsumoKW();
			}			
		}
		return consumo;
	}
		 
	private Date sumarRestarDiasFecha(Date fecha, int dias){	
	       Calendar calendar = Calendar.getInstance();	
	       calendar.setTime(fecha); // Configuramos la fecha que se recibe	
	       calendar.add(Calendar.DAY_OF_YEAR, -dias);  // numero de días a sumados, o restar en caso de días<0
	       return calendar.getTime(); // Devuelve el objeto Date con los nuevos días sumados o restados
	  }
	//me da el consumo del dispositivo en las ultimas N horas. 
	public float consumoUltimasNHoras(int horas){
		float consumo=0;
		int horaFin =0;
		List<Modo> lista=getLogModos();
		if (!lista.isEmpty()) {
			Calendar calendarFin = Calendar.getInstance();
			calendarFin.setTime(lista.get(0).getFechaHoraFin());
			calendarFin.add(Calendar.HOUR_OF_DAY, -horas);  // numero de horas a añadir, o restar en caso de horas<0
			horaFin = calendarFin.get(Calendar.HOUR_OF_DAY);				
		}		
		for (int i = 1; i <= lista.size(); i++) {
			Calendar calendarInicio = Calendar.getInstance();
			calendarInicio.setTime(lista.get(i).getFechaHoraInicio());
			int horaInicio = calendarInicio.get(Calendar.HOUR_OF_DAY);
			if (horaFin>horaInicio) {
				//regla 3 simple para obtener kw y break;
			}else {
				consumo=consumo+lista.get(i).getConsumoKW();
			}			
		}
		return consumo;
	}

     */
	
}
