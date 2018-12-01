package reglasYActuadores;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="actuadorEnum")
public enum ActuadoresEnum {
	
	APAGAR , PRENDER ,  CAMBIAR_INTENSIDAD_LUZ , CAMBIAR_TEMPERATURA ;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="valorEnum")
	private String valorEnum;

	
	ActuadoresEnum(){
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getValorEnum() {
		return valorEnum;
	}


	public void setValorEnum(String valorEnum) {
		this.valorEnum = valorEnum;
	}
	
	
	
}
