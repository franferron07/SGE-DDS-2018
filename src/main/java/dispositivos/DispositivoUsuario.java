package dispositivos;

import java.time.LocalDateTime;

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
	protected LocalDateTime fecha_alta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
	protected Cliente cliente;
	
	
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
	
	
	//getters and setters
	public DispositivoDetalle getDispositivoDetalle() {
		return detalle;
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