package dispositivos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="modo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
public abstract class Modo {
	
	@Id
	@GeneratedValue
	protected int id;
	
	@Column(name="fechaHoraInicio")
	protected String fechaHoraInicio_s;

	@Column(name="fechaHoraFin")
	protected String fechaHoraFin_s;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	protected DispositivoInteligente dispositivo_inteligente;

	@OneToMany(mappedBy="modo", cascade = CascadeType.PERSIST , fetch = FetchType.EAGER )
	protected List<Consumo> consumos;
	
	@Transient
	protected LocalDateTime fechaHoraInicio;
	@Transient
	protected LocalDateTime fechaHoraFin;
	
	//constructor
	public Modo( DispositivoInteligente di ){
		this.fechaHoraInicio= LocalDateTime.now();
    	this.consumos = new ArrayList<Consumo>();
		this.dispositivo_inteligente = di;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		fechaHoraInicio_s= fechaHoraInicio.format(formatter);
	}
	
	public Modo(){
		
	}
	
	
	public abstract boolean encendido();
	
	//me da el consumo en un periodo del modo.
	public abstract float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal );

	//registra el consumo en los modos. en modo apagado no hace nada.
	public abstract void registrarConsumo(LocalDateTime inicio, LocalDateTime fin, float consumo);

	//me indica si en un periodo se cumple o no el intervalo
	public abstract boolean cumpleIntervalo(LocalDateTime desde, LocalDateTime hasta);

	
	public void apagarse(DispositivoInteligente disp) {		
		//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		
		disp.agregarLogModo(new ModoApagado( getDispositivo_inteligente() ) );
	}
	
	public void encenderse(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo
		setFechaHoraFin(LocalDateTime.now());
		disp.agregarLogModo( new ModoEncendido( getDispositivo_inteligente() ) );
	}
	
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		
		disp.agregarLogModo( new ModoAhorroEnergia( getDispositivo_inteligente() ) );
	}
	
	
	
	//parsea fecha
	public LocalDateTime parsearFecha( String fecha_s ){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(fecha_s, formatter);
	}
	
	
	public int cantidadConsumos(){
		
		return 0;
	}
	

	
	//getters y setters
	
	public LocalDateTime getFechaHoraInicio() {
		
		this.fechaHoraInicio = parsearFecha(fechaHoraInicio_s);
		return fechaHoraInicio;
	}
	
	public LocalDateTime getFechaHoraFin() {
		
		this.fechaHoraFin = parsearFecha(fechaHoraFin_s);
		return fechaHoraFin;
	}
	
	public String getFechaHoraInicio_s() {
		return fechaHoraInicio_s;
	}


	public void setFechaHoraInicio_s(String fechaHoraInicio_s) {
		this.fechaHoraInicio_s = fechaHoraInicio_s;
	}


	public String getFechaHoraFin_s() {
		return fechaHoraFin_s;
	}


	public void setFechaHoraFin_s(String fechaHoraFin_s) {
		this.fechaHoraFin_s = fechaHoraFin_s;
	}

	
	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
		
		/* seteo hora parseada final */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		fechaHoraFin_s= fechaHoraFin.format(formatter);
		
	}

	public int getId() {
		return id;
	}
	
	public DispositivoInteligente getDispositivo_inteligente() {
		return dispositivo_inteligente;
	}

	public void setDispositivo_inteligente(DispositivoInteligente dispositivo_inteligente) {
		this.dispositivo_inteligente = dispositivo_inteligente;
	}

}
