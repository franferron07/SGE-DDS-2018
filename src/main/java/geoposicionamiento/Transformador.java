package geoposicionamiento;

import java.awt.Point;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import usuarios.Cliente;

public class Transformador {
	
	private List<Cliente> clientes;
	private Point coordenadas;
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
	
	
	//me da el consumo total en un determinado instante
	public float consumoTotal( LocalDateTime unInstante ){
		
		return 0;
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
	
	
	

}
