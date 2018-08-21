package geoposicionamiento;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.DaoJson;
import usuarios.Cliente;

public class Mapa {
	
	private DaoJson<Transformador> daoTransformador;
	private DaoJson<ZonaGeografica> daoZonaGeografica;
	private List<ZonaGeografica> zonasGeograficas;
	
	
	//constructor
	public Mapa(){
		daoZonaGeografica = new DaoJson<ZonaGeografica>("zonasGeograficas.json");
		daoTransformador = new DaoJson<Transformador>("transformadores.json");
		
		zonasGeograficas = new ArrayList<ZonaGeografica>();
		//zonasGeograficas = daoZonaGeografica.obtener();
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
		
		ZonaGeografica zona = zonasGeograficas.stream().filter( z -> z.coordenadaEnZona( unTransformador.getCoordenadas())).findAny().orElse(null);
		
		if( zona!=null )
		{
			zona.agregarTransformador(unTransformador);
		}
		
	}
	
	// busca la zona a la que puede pertenecer el cliente y luego la zona le asignara el transformador correspondiente.
	public void asignarZonaCliente( Cliente unCliente ){
		
		ZonaGeografica zonaPert = zonaPerteneciente(unCliente.getCoordenadas());
		
		if( zonaPert != null ){
			zonaPert.asignarTransformador(unCliente);
		}
		
	}
	
	//metodo que dada una coordenada me devolvera la zona a la que pertenece.
	public ZonaGeografica zonaPerteneciente( Point coordenada ){
		
		ZonaGeografica zona = null;
		zona = zonasGeograficas.stream().filter( z -> z.coordenadaEnZona( coordenada )).findAny().orElse(null);

		return zona;
	}


	
	//getters y setters
	public DaoJson<Transformador> getDaoTransformador() {
		return daoTransformador;
	}


	public void setDaoTransformador(DaoJson<Transformador> daoTransformador) {
		this.daoTransformador = daoTransformador;
	}


	public DaoJson<ZonaGeografica> getDaoZonaGeografica() {
		return daoZonaGeografica;
	}


	public void setDaoZonaGeografica(DaoJson<ZonaGeografica> daoZonaGeografica) {
		this.daoZonaGeografica = daoZonaGeografica;
	}


	public List<ZonaGeografica> getZonasGeograficas() {
		return zonasGeograficas;
	}


	public void setZonasGeograficas(List<ZonaGeografica> zonasGeograficas) {
		this.zonasGeograficas = zonasGeograficas;
	}
	

}
