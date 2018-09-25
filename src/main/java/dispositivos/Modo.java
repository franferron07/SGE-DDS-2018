package dispositivos;

import java.time.LocalDateTime;
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

@Entity
@Table(name="modo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
public abstract class Modo {
	
	@Id
	@GeneratedValue
	protected int id;
	@Column(name="fechaHoraInicio")
	protected LocalDateTime fechaHoraInicio;
	@Column(name="fechaHoraFin")
	protected LocalDateTime fechaHoraFin;
	
	@ManyToOne
	@JoinColumn( name="inteligente_id" , referencedColumnName="id" )
	protected DispositivoInteligente di;
	
	@OneToMany(mappedBy="modo",cascade=CascadeType.PERSIST , fetch=FetchType.LAZY)
	protected List<Consumo> consumos;
	
	
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
		
		disp.agregarLogModo(new ModoApagado() );
	}
	
	public void encenderse(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo
		setFechaHoraFin(LocalDateTime.now());
		disp.agregarLogModo( new ModoEncendido() );
	}
	
	public void ahorrarseEnergia(DispositivoInteligente disp) {
		//agrego log de modo antes de cambiarlo y le seteo la fecha final
		setFechaHoraFin(LocalDateTime.now());
		
		disp.agregarLogModo( new ModoAhorroEnergia() );
	}
	
	
	
	
	public int cantidadConsumos(){
		
		return 0;
	}
	

	
	//getters y setters
	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public int getId() {
		return id;
	}
	

}
