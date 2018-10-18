package dispositivos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import usuarios.Cliente;

@Entity
@Table(name="dispositivo")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo")
public abstract class DispositivoUsuario {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="dispositivoDetalle_id" , referencedColumnName="id")
	public DispositivoDetalle detalle;
	
	@Column(name="activado")
	protected boolean activado;
	
	@Column(name="fecha_alta")
	protected String fecha_alta_s;
	
	@Transient
	protected LocalDateTime fecha_alta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
	protected Cliente cliente;
	
	
	
	public DispositivoUsuario(){
		
		this.activado = true;
		this.fecha_alta = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		fecha_alta_s= fecha_alta.format(formatter);
	}
	
	//Me da el consumo en un determinado periodo de tiempo. 
	public abstract float consumoPeriodo( LocalDateTime desde , LocalDateTime hasta );
	
	public abstract boolean esInteligente(); // esEsencial() lo reemplaza para el simplex

	//Me da horas de encendido en un periodo de tiempo
	public abstract double horasDeUso( LocalDateTime desde, LocalDateTime hasta);
	
	public boolean esEsencial(){
		return detalle.isEsEsencial();
	}
	
	
	public void activar(){
		this.activado=true;
	}
	
	public void desactivar(){
		
		this.activado=false;
	}
	
	//setea la fecha del alta correctamente
	public LocalDateTime parsearFecha( String fecha_s ){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(fecha_s, formatter);
	}
	
	//getters and setters
	
	
	
	public DispositivoDetalle getDispositivoDetalle() {
		return detalle;
	}

	public String getFecha_alta_s() {
		return fecha_alta_s;
	}

	public void setFecha_alta_s(String fecha_alta_s) {
		this.fecha_alta_s = fecha_alta_s;
	}

	public LocalDateTime getFecha_alta() {
		
		this.fecha_alta = parsearFecha(fecha_alta_s);
		return fecha_alta;
	}

	public void setFecha_alta(LocalDateTime fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDispositivoLista(DispositivoDetalle dispositivoLista) {
		this.detalle = dispositivoLista;
	}
	
	
	public int getId() {
		return id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
//	//------ simplex
	// en lugar de llamar cada momento a su atributo detalle ,esto lo simplifica haciendolo mas cohesivo ,
	
	public  double getConsumoKwHora() {
		return this.detalle.getConsumoKwHora();
	}
	public  String getIdentificacion() {
		return this.detalle.getDescripcion();
	}
	public  double getHsMensualMinimo() {
		return this.detalle.getHsMensualMinimo();
	}
	public  double getHsMensualMaximo(){
		return this.detalle.getHsMensualMaximo();
	}



}