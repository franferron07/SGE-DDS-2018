package geoposicionamiento;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.DaoJson;
import repositorios.RepositorioTransformadores;
import repositorios.RepositorioZonas;
import usuarios.Cliente;
@Entity
@Table(name ="mapa")
public class Mapa {
	@Id
	@GeneratedValue
	private int id;
	
	
	private List<ZonaGeografica> zonasGeograficas;
	private RepositorioZonas repoZonas;
	private RepositorioTransformadores repoTransformadores;
	
	
	//constructor
	public Mapa(){
		
		repoZonas = new RepositorioZonas();
		repoTransformadores = new RepositorioTransformadores();
		
		
		zonasGeograficas = new ArrayList<ZonaGeografica>();
		//zonasGeograficas = daoZonaGeografica.obtener();
	}
	

	//obtener y asignar transformadores a las zonas existentes
	public void leerTransformador(){
		
		List<Transformador> transformadores = repoTransformadores.getTransformadores();
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

	public List<ZonaGeografica> getZonasGeograficas() {
		return zonasGeograficas;
	}


	public void setZonasGeograficas(List<ZonaGeografica> zonasGeograficas) {
		this.zonasGeograficas = zonasGeograficas;
	}
	
	public int getId() {
		return this.id;
	}
	

}
