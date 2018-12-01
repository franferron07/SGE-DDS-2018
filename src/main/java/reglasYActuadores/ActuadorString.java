package reglasYActuadores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actuadorString")
public class ActuadorString {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	public ActuadorString(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
