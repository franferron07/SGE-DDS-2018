package geoposicionamiento;

import java.awt.Point;
import java.awt.Polygon;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import usuarios.Cliente;

public class ZonaGeografica {
	
	private ArrayList<Transformador> transformadores;
	private Polygon limitesZona;

	
	//constructor
	public ZonaGeografica( ){
		transformadores = new ArrayList<Transformador>();
		limitesZona = new Polygon();
	}
	
	//constructor inicializando los limites.
	public ZonaGeografica( int[] puntosX , int[] puntosY ){
		transformadores = new ArrayList<Transformador>();
		limitesZona = new Polygon(puntosX , puntosY , 4);
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
	public double consumoTotal(LocalDateTime unInstante){
		
		return transformadores.stream().mapToDouble(t->t.consumoTotal(unInstante)).sum();
	}
	
	//me dice si la coordenada esta dentro del transformador
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
