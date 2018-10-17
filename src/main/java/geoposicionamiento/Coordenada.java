package geoposicionamiento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//subir


@Entity
@Table(name="coordenada")
public class Coordenada {
	@Id
	@GeneratedValue
	public int id;
	
	@Column(name="longitud")
	public double longitud;//x
	@Column(name="latitud")
	public double latitud;//y 

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
	public Ubicable ubicable;
	
	@Column(name="orden")
	public int orden;
	
	
	public Coordenada(double lat,double lon) {
		this.latitud=lat;
		this.longitud=lon;
		this.ubicable = null;
	}
	
	public Coordenada(Ubicable ubicable , double lat,double lon) {
		this.latitud=lat;
		this.longitud=lon;
		this.ubicable = ubicable;
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
