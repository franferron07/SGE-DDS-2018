package geoposicionamiento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import usuarios.Cliente;

@Entity
@Table(name="transformador")
public class Transformador {
	
	@Id
	@GeneratedValue
	public int id;
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST )
	@JoinColumn(name = "ubicable_id")
	public Ubicable ubicable = new Ubicable();
	
	
	@OneToMany(mappedBy="transformador", cascade = CascadeType.PERSIST , fetch = FetchType.LAZY )
	private List<Cliente> clientes;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
	private ZonaGeografica zonaAsignada;
	
	
	//constructor
	public Transformador(){
		clientes= new ArrayList<Cliente>();
	}
	
	//constructor
	public Transformador(ZonaGeografica zona , Coordenada coordenada){
		clientes= new ArrayList<Cliente>();
		this.setZonaAsignada(zona);
		this.getUbicable().addCoordenadas(coordenada);
	}
	
	//constructor
	public Transformador(Coordenada coordenada){
		clientes= new ArrayList<Cliente>();
		this.getUbicable().addCoordenadas(coordenada);
		
	}

	public List<Coordenada> getCoordenadas() {
		return this.ubicable.getCoordenadas();
	}
	
	//me da el consumo total en un determinado instante(se toma 1 hora el minimo)
	public double consumoTotal( LocalDateTime desde , LocalDateTime hasta ){
		
		double consumoTotal = clientes.stream().mapToDouble( c -> c.consumoEnUnPeriodo(desde, hasta)).sum();
		
		return consumoTotal;
	}
	
	public void agregarCliente(Cliente unCliente){
		this.clientes.add(unCliente);
	}

	
	public Coordenada getUbicacion() {
		return this.getUbicable().getUbicacion();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ubicable getUbicable() {
		return this.ubicable;
	}

	public void setUbicable(Ubicable ubicable) {
		this.ubicable = ubicable;
	}
	
	
	

}
