package geoposicionamiento;

import java.awt.Point;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import usuarios.Cliente;

@Entity
@Table(name="transformador")
public class Transformador {
	@Id
	@GeneratedValue
	private int id;
	
	
	
	@ManyToMany(mappedBy="cliente", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Cliente> clientes;
	
	@Column(name="coodenada")
	private Point coordenadas;
	@Column(name="zona_geografica")
	private ZonaGeografica zonaAsignada;
	
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
	
	public Point getCoordenadas() {
		return coordenadas;
	}


	public void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}

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
	
	
	public int getId() {
		return this.id;
	}

}
