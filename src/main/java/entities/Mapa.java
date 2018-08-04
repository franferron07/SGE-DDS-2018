package entities;

import java.util.ArrayList;
import java.util.List;

import usuarios.Cliente;

public class Mapa {
	
	private List<ZonaGeografica> zonasGeograficas;
	
	//constructor
	public Mapa(){
		zonasGeograficas = new ArrayList<ZonaGeografica>();
	}
	
	//le asigna la zona perteneciente a la ubicacion del transformador
	public void asignarZonaTransformador(Transformador unTransformador){
		
	}
	
	// busca la zona a la que puede pertenecer el cliente y luego la zona le asignara el transformador correspondiente.
	public void asignarZonaCliente( Cliente unCliente ){
		
	}
	
	//metodo que dada una coordenada me devolvera la zona a la que pertenece.
	public ZonaGeografica zonaPerteciente(  ){
		
		return null;
	}


}
