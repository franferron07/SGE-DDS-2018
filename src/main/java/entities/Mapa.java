package entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import usuarios.Cliente;

public class Mapa {
	
	private DaoJson<Transformador> daoTransformador;
	private DaoJson<ZonaGeografica> daoZonaGeografica;
	private List<ZonaGeografica> zonasGeograficas;
	
	
	//constructor
	public Mapa(){
		zonasGeograficas = new ArrayList<ZonaGeografica>();
		daoZonaGeografica = new DaoJson<ZonaGeografica>("zonasGeograficas.json");
		daoTransformador = new DaoJson<Transformador>("transformadores.json");
		
		zonasGeograficas = daoZonaGeografica.obtener();
	}
	
	
	//obtener y asignar transofmares
	public void leerTransformador(){
		
		
		
	}
	
	//le asigna la zona perteneciente a la ubicacion del transformador
	public void asignarZonaTransformador(Transformador unTransformador){
		
	}
	
	// busca la zona a la que puede pertenecer el cliente y luego la zona le asignara el transformador correspondiente.
	public void asignarZonaCliente( Cliente unCliente ){
		
	}
	
	//metodo que dada una coordenada me devolvera la zona a la que pertenece.
	public ZonaGeografica zonaPerteciente( Point coordenada ){
		
		return null;
	}


}
