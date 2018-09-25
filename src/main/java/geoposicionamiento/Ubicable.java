package geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ubicable")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Ubicable {
	
	@Id
	@GeneratedValue
	public int id;
	
	@OneToMany(mappedBy="ubicable",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	public List<Coordenada> coordenadas;
	
	
	public Ubicable() {
		this.coordenadas=new ArrayList<Coordenada>();
		
	}
	public int getId() {
		return this.id;
	}

}
