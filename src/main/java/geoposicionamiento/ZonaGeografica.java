package geoposicionamiento;

import java.awt.Point;
import java.awt.Polygon;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import usuarios.Cliente;
import usuarios.Usuario;
//subir

@Entity
@Table(name="zona_geografica")
@DiscriminatorValue("zona_geografica")

public class ZonaGeografica extends Ubicable{
	
	
	@OneToMany(mappedBy="id",cascade=CascadeType.PERSIST,fetch=FetchType.LAZY)
	private List<Transformador> transformadores;
	
	
	private Polygon limitesZona;

	
	//constructor
	public ZonaGeografica( ){
		transformadores = new ArrayList<Transformador>();
		limitesZona = new Polygon();
	}
	
	//constructor inicializando los limites.
	public ZonaGeografica( int[] puntosX , int[] puntosY , int cantidad ){
		transformadores = new ArrayList<Transformador>();
		limitesZona = new Polygon(puntosX , puntosY , cantidad);
	}
	
	
	//asigna al transofmrador mas cerca un cliente
	public void asignarTransformador(Cliente unCliente){
		
		Transformador transformadorCercano = transformadorMasCercano(unCliente);
		transformadorCercano.agregarCliente(unCliente);
		
	}
	
	//me devuelve el transformador mas cercano al cliente para asignar
	public Transformador transformadorMasCercano( Cliente unCliente ){
		
		Transformador tMinimo=null;
		double distanciaMinima= 0;
		double distancia =0 ;
		
		Iterator<Transformador> it = transformadores.iterator();
		// recorro lista de transformadores buscando la distancia minima.
		while(it.hasNext()){
			
			Transformador transformador =it.next();
			distancia = this.calcularDistancia(unCliente.getCoordenadas(), transformador.getCoordenadas() );
			//entra a if si es la primera vez o si la distancia es menor a la minima
			if( tMinimo == null || distancia < distanciaMinima ){
				tMinimo = transformador;
				distanciaMinima = distancia;			
			}
			
		}
		return tMinimo;
	}
	
	//calculo la distancia entre dos puntos
	public double calcularDistancia(Point p1 , Point p2){
		
		double distancia = Math.pow( p1.x - p2.x , 2 ) + Math.pow( p1.y - p2.y , 2 );
		distancia = Math.sqrt(distancia);
		
		return distancia;
	}
	
	//me da el consumo total de todos los transformadores en un instante
	public double consumoTotal(LocalDateTime desde , LocalDateTime hasta){
		
		return transformadores.stream().mapToDouble(t->t.consumoTotal(desde , hasta)).sum();
	}
	
	//me dice si la coordenada esta dentro de la zona
	public boolean coordenadaEnZona(Point coordenada){
		
		if( limitesZona.contains(coordenada) ) return true;
		
		return false;
	}
	
	
	public void agregarTransformador(Transformador unTransformador){
		transformadores.add(unTransformador);
	}


	//getters y setters
	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public void setTransformadores(ArrayList<Transformador> transformadores) {
		this.transformadores = transformadores;
	}

	public Polygon getLimitesZona() {
		return limitesZona;
	}

	public void setLimitesZona(Polygon limitesZona) {
		this.limitesZona = limitesZona;
	}
	
	
}
