package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import usuarios.Cliente;

public class Transformador {
	
	private List<Cliente> clientes;
	//private coordenada
	
	//constructor
	public Transformador(){
		clientes= new ArrayList<Cliente>();
	}
	
	
	//me da el consumo total en un determinado instante
	public float consumoTotal( LocalDateTime unInstante ){
		
		return 0;
	}
	
	public void agregarCliente(Cliente unCliente){
		this.clientes.add(unCliente);
	}
	

}
