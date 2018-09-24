package geoposicionamiento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//subir

@Entity
@Table(name="coordenada")
public class Coordenada {
	@Id
//	@GeneratedValue
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@Column(name="longitud")
	public double longitud;//x
	@Column(name="latitud")
	public double latitud;//y 
//	@GeneratedValue
	public int orden;
	
	
	
	public Coordenada() {
	}

}
