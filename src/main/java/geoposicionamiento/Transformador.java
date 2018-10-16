package geoposicionamiento;

import java.awt.Point;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import usuarios.Cliente;
//subir
@Entity
@Table(name="transformador")
@DiscriminatorValue(value="transformador")
public class Transformador extends Ubicable {
	
	
	@OneToMany(mappedBy="transformador",cascade=CascadeType.PERSIST,fetch=FetchType.LAZY)
	private List<Cliente> clientes;
	
//	@OneToOne( mappedBy="transformador",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Transient
	private Point coordenadas;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
	private ZonaGeografica zonaAsignada;

	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="ubicable_id")
	protected Ubicable ubicable;
	
	//constructor
	public Transformador(){
		clientes= new ArrayList<Cliente>();
	}
	
	//constructor
	public Transformador(ZonaGeografica zona , Point coordenada){
		clientes= new ArrayList<Cliente>();
		zonaAsignada = zona;
		coordenadas = coordenada;
	}
	
	//constructor
	public Transformador(Point coordenada){
		clientes= new ArrayList<Cliente>();
		coordenadas = coordenada;
		
	}
	
	
	//me da el consumo total en un determinado instante(se toma 1 hora el minimo)
	public double consumoTotal( LocalDateTime desde , LocalDateTime hasta ){
		
		double consumoTotal = clientes.stream().mapToDouble( c -> c.consumoEnUnPeriodo(desde, hasta)).sum();
		
		return consumoTotal;
	}
	
	public void agregarCliente(Cliente unCliente){
		this.clientes.add(unCliente);
	}

	
	
	//getters y setters
	

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ZonaGeografica getZonaAsignada() {
		return zonaAsignada;
	}

	public void setZonaAsignada(ZonaGeografica zonaAsignada) {
		this.zonaAsignada = zonaAsignada;
	}
	
	
	

}
