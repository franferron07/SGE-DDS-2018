package geoposicionamiento;

import java.util.ArrayList;
import java.util.List;

import repositorios.RepositorioTransformadores;
import repositorios.RepositorioZonas;
import usuarios.Cliente;
//@Entity
//@Table(name ="mapa")
public abstract class Mapa {
	
	
	private static List<ZonaGeografica> zonasGeograficas=new ArrayList<ZonaGeografica>();;
	private static List<Transformador> transformadores=new ArrayList<Transformador>();;
	
	

	public static ZonaGeografica factoryZona() {
		ZonaGeografica zona = new ZonaGeografica();
		zonasGeograficas.add(zona);
		return zona;
	}

	public static Transformador factoryTransformador() {
		Transformador trafo = new Transformador();
		asignarZonaTransformador(trafo);
		transformadores.add(trafo);
		return trafo;
	}

	//obtener y asignar transformadores a las zonas existentes
	public static void leerTransformador(){
		
		if( !transformadores.isEmpty() ){
			//agrego transformador a las zonas.
			transformadores.forEach(t->t.getZonaAsignada().agregarTransformador(t));	
		}
	}
	
	//le asigna la zona perteneciente a la ubicacion del transformador
	public static void asignarZonaTransformador(Transformador unTransformador){
		
		ZonaGeografica zona = zonasGeograficas.stream().filter( z -> z.coordenadaEnZona( unTransformador.getUbicacion())).findAny().orElse(null);
		
		if( zona!=null )
		{
			zona.agregarTransformador(unTransformador);
		}
		
	}
	
	// busca la zona a la que puede pertenecer el cliente y luego la zona le asignara el transformador correspondiente.
	public static void asignarZonaCliente( Cliente unCliente ){
		
		ZonaGeografica zonaPert = zonaPerteneciente(unCliente.getUbicacion());
		
		if( zonaPert != null ){
			zonaPert.asignarTransformador(unCliente);
		}
		
	}
	
	//metodo que dada una coordenada me devolvera la zona a la que pertenece.
	public static ZonaGeografica zonaPerteneciente( Coordenada coordenada ){
		ZonaGeografica zona = null;
		zona = zonasGeograficas.stream().filter( z -> z.coordenadaEnZona( coordenada )).findAny().orElse(null);

		return zona;
	}


	
	//getters y setters

	public static List<ZonaGeografica> getZonasGeograficas() {
		return zonasGeograficas;
	}


	public static void setZonasGeograficas(List<ZonaGeografica> zonas) {
		zonasGeograficas = zonas;
	}
	
	
	

}
