package reglasYActuadores;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import dispositivos.DispositivoInteligente;


@Entity
@Table(name="actuador")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
public abstract class ActuadorBase {

	@Id
	@GeneratedValue
	public int id;
	
	@Transient
	protected DispositivoInteligente dispositivo;
	
	
	public abstract void ejecutarAccion();

	
	
	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	

}
