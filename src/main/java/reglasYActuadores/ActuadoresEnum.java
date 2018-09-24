package reglasYActuadores;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="actuadorEnum")
public enum ActuadoresEnum {
	
	APAGAR , PRENDER ,  CAMBIAR_INTENSIDAD_LUZ , CAMBIAR_TEMPERATURA ;
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Regla> reglas;

}
