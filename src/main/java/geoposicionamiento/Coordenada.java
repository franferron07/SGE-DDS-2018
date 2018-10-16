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

	@Column(name="orden")
	public int orden;
	
	
	public Coordenada(double lat,double lon) {
		this.latitud=lat;
		this.longitud=lon;
	}

	
	//getters y setters
	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

}
