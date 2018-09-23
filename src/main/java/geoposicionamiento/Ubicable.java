package geoposicionamiento;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Ubicable {
	@Id
	@GeneratedValue
	public int id;
	
	@Column(name="x")
	public int x;
	@Column(name="y")
	public int y; 
	
	
	public Ubicable() {
	}
	public int getId() {
		return this.id;
	}

}
