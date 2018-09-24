package entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="observador")
@DiscriminatorColumn(name="tipo")
public abstract class ObservadorSensor {
	
	@Id
	@GeneratedValue
	protected int id;

	public abstract void notificacionDeMedicion(Medicion medicion);

}
