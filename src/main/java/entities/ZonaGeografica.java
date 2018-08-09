package entities;

import java.awt.Point;
import java.awt.Polygon;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import usuarios.Cliente;

public class ZonaGeografica {
	
	private List<Transformador> transformadores;
	private Polygon limitesZona;

	
	//constructor
	public ZonaGeografica( ){
		transformadores = new ArrayList<Transformador>();
		
	}
	
	//asigna al transofmrador mas cerca un cliente
	public void asignarTransformador(Cliente unCliente){
		
	}
	
	//me devuelve el transformador mas cercano al cliente para asignar
	public Transformador transformadorMasCercano( Cliente unCliente ){
		
		return null;
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
	
	
	
}
