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
	
	
	//obtener y asignar transformadores a las zonas existentes
	public void leerTransformador(){
		
		List<Transformador> transformadores = daoTransformador.obtener();
		if( !transformadores.isEmpty() ){
			//agrego transformador a las zonas.
			transformadores.forEach(t->t.getZonaAsignada().agregarTransformador(t));	
		}
	}
	
	//le asigna la zona perteneciente a la ubicacion del transformador
	public void asignarZonaTransformador(Transformador unTransformador){
		
	}
	
	// busca la zona a la que puede pertenecer el cliente y luego la zona le asignara el transformador correspondiente.
	public void asignarZonaCliente( Cliente unCliente ){
		
		ZonaGeografica zonaPert = zonaPerteneciente(unCliente.getCoordenadas());
		zonaPert.asignarTransformador(unCliente);
	}
	
	//metodo que dada una coordenada me devolvera la zona a la que pertenece.
	public ZonaGeografica zonaPerteneciente( Point coordenada ){
		
		List<ZonaGeografica >zonas = (List<ZonaGeografica>) zonasGeograficas.stream().filter(z -> z.coordenadaEnZona(coordenada));
		
		if( !zonas.isEmpty() ) {
			return zonas.get(0);
		}
		
		return null;
	}


}
