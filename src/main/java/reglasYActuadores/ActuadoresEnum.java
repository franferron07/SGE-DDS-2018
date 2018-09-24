package reglasYActuadores;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="actuadorEnum")
public enum ActuadoresEnum {
	
	APAGAR , PRENDER ,  CAMBIAR_INTENSIDAD_LUZ , CAMBIAR_TEMPERATURA  

}
