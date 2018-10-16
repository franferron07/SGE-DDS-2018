package geoposicionamiento;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import usuarios.Cliente;
//subir

@Entity
@Table(name="zona_geografica")
@DiscriminatorValue("zona_geografica")

public class ZonaGeografica extends Ubicable{
	
	
	@OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
	@JoinColumn( name="zonaAsignada_id" , referencedColumnName="id" )
	private List<Transformador> transformadores;


	//constructor
	public ZonaGeografica( ){
		transformadores = new ArrayList<Transformador>();
	}
	
	public ZonaGeografica(List<Coordenada> xy) {
		super();
		this.coordenadas=xy;
	}
	
	public ZonaGeografica(List<Coordenada> xy, List<Transformador> transformadores) {
		super();
		this.coordenadas=xy;
		this.transformadores=transformadores;
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
			distancia = super.distanciaCoordenadas(unCliente.getUbicacion(), transformador.getUbicacion() );
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
		
		return this.getPoligono().contains(coordenada.getLatitud(),coordenada.getLongitud());
	}
	
	public boolean coordenadaEnZona(double x, double y){
		
		return this.getPoligono().contains(x,y);
	}
	
	public void agregarTransformador(Transformador unTransformador){
		transformadores.add(unTransformador);
	}


	//getters y setters
	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public void setTransformadores(ArrayList<Transformador> transformadores) {
		this.transformadores = transformadores;
	}

	
	
}
