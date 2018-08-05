package entities;

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
	public void asignarTransformador(Transformador unTransformador , Cliente unCliente){
		
	}
	
	//me devuelve el transformador mas cercano al cliente para asignar
	public Transformador transformadorMasCercano( Cliente unCliente ){
		
		return null;
	}
	
	//me da el consumo total de todos los transformadores en un instante
	public float consumoTotal(LocalDateTime unInstante){
		
		return 0;
	}
	
	
	public void agregarTransformador(Transformador unTransformador){
		transformadores.add(unTransformador);
	}
	
	
	
}
