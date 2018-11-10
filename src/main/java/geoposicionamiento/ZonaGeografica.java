package geoposicionamiento;


import java.awt.geom.Path2D;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import usuarios.Cliente;


@Entity
@Table(name="zona_geografica")
public class ZonaGeografica{
	
	
	@Id
	@GeneratedValue
	public int id;
	
	@OneToMany(mappedBy= "zonaAsignada",cascade = CascadeType.PERSIST , fetch = FetchType.EAGER )
	private List<Transformador> transformadores;

	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL  )
	@JoinColumn(name = "ubicable_id")
	public Ubicable ubicable = new Ubicable();
	
	
	//constructor
	public ZonaGeografica( ){
		transformadores = new ArrayList<Transformador>();
	}
	
	public ZonaGeografica(List<Coordenada> xy) {
		super();
		this.getUbicable().setCoordenadas(xy);
		transformadores = new ArrayList<Transformador>();
	}
	
	public ZonaGeografica(List<Coordenada> xy, List<Transformador> transformadores) {
		super();
		this.getUbicable().coordenadas=xy;
		this.transformadores=transformadores;
	}
	
	public List<Coordenada> getCoordenadas() {
		return this.ubicable.getCoordenadas();
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
			distancia = this.getUbicable().distanciaCoordenadas(unCliente.getUbicacion(), transformador.getUbicacion() );
			//entra a if si es la primera vez o si la distancia es menor a la minima
			if( tMinimo == null || distancia < distanciaMinima ){
				tMinimo = transformador;
				distanciaMinima = distancia;			
			}
			
		}
		return tMinimo;
	}
	
	
	//me da el consumo total de todos los transformadores en un instante
	public double consumoTotal(LocalDateTime desde , LocalDateTime hasta){
		
		return transformadores.stream().mapToDouble(t->t.consumoTotal(desde , hasta)).sum();
	}
	
	//me dice si la coordenada esta dentro de la zona
	public boolean coordenadaEnZona(Coordenada coordenada){
		Path2D poligono = this.getUbicable().getPoligono();
		boolean isInPoligono = poligono.contains(coordenada.getLatitud(),coordenada.getLongitud());
		return isInPoligono;
	}
	
	public boolean coordenadaEnZona(double x, double y){
		
		return this.getUbicable().getPoligono().contains(x,y);
	}
	
	public void agregarTransformador(Transformador unTransformador){
		transformadores.add(unTransformador);
		unTransformador.setZonaAsignada(this);
	}


	//getters y setters
	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public void setTransformadores(ArrayList<Transformador> transformadores) {
		this.transformadores = transformadores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ubicable getUbicable() {
		return ubicable;
	}

	public void setUbicable(Ubicable ubicable) {
		this.ubicable = ubicable;
	}

	
	
	
	
}
