package geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import repositorios.RepositorioTransformadores;
import repositorios.RepositorioZonas;
import usuarios.Cliente;
//@Entity
//@Table(name ="mapa")
public class Mapa {
	
	
	private List<ZonaGeografica> zonasGeograficas;
	private List<Transformador> transformadores;
	
	
	//constructor
	public Mapa(){
		
		zonasGeograficas = new ArrayList<ZonaGeografica>();
		transformadores=new ArrayList<Transformador>();
		//zonasGeograficas = daoZonaGeografica.obtener();
	}
	
	public ZonaGeografica factoryZona() {
		ZonaGeografica zona = new ZonaGeografica();
		this.zonasGeograficas.add(zona);
		return zona;
	}

	public Transformador factoryTransformador() {
		Transformador trafo = new Transformador();
		this.transformadores.add(trafo);
		return trafo;
	}

	//obtener y asignar transformadores a las zonas existentes
	public void leerTransformador(){
		
		if( !transformadores.isEmpty() ){
			//agrego transformador a las zonas.
			transformadores.forEach(t->t.getZonaAsignada().agregarTransformador(t));	
		}
	}
	
	//le asigna la zona perteneciente a la ubicacion del transformador
	public void asignarZonaTransformador(Transformador unTransformador){
		
		ZonaGeografica zona = zonasGeograficas.stream().filter( z -> z.coordenadaEnZona( unTransformador.getUbicacion())).findAny().orElse(null);
		
		if( zona!=null )
		{
			zona.agregarTransformador(unTransformador);
		}
		
	}
	
	// busca la zona a la que puede pertenecer el cliente y luego la zona le asignara el transformador correspondiente.
	public void asignarZonaCliente( Cliente unCliente ){
		
		ZonaGeografica zonaPert = zonaPerteneciente(unCliente.getUbicacion());
		
		if( zonaPert != null ){
			zonaPert.asignarTransformador(unCliente);
		}
		
	}
	
	//metodo que dada una coordenada me devolvera la zona a la que pertenece.
	public ZonaGeografica zonaPerteneciente( Coordenada coordenada ){
		ZonaGeografica zona = null;
		zona = zonasGeograficas.stream().filter( z -> z.coordenadaEnZona( coordenada )).findAny().orElse(null);

		return zona;
	}


	
	//getters y setters

	public List<ZonaGeografica> getZonasGeograficas() {
		return zonasGeograficas;
	}


	public void setZonasGeograficas(List<ZonaGeografica> zonasGeograficas) {
		this.zonasGeograficas = zonasGeograficas;
	}
	
	
	

}
