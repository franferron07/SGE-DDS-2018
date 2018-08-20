package repositorios;

import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import usuarios.Cliente;

//esto podria ser singleton y que se cargue todo en el main
public class RepositorioClientes {
	
	
	private DaoJson<Cliente> daoCliente;
	private List<Cliente> clientes;
	
	
	//constructor
	public RepositorioClientes(){
		this.daoCliente= new DaoJson<Cliente>("clientes.json");
		this.clientes = new ArrayList<Cliente>();
		
		cargarDatos();
	}
	
	//carga datos del archivo
	public void cargarDatos(){
		if( this.clientes.isEmpty() ){
			this.clientes = daoCliente.obtener();
		}
	}
	
	//filtro los clientes que tengan accion automatica.
	public List<Cliente> filtrarClientesAccionAutomatica(){
		
		if( clientes.isEmpty() ) return null;
		
		return (List<Cliente>) this.clientes.stream().filter(c -> c.isAccionadoAutomatico()) ; 
	}

	
	//getters y setters
	public DaoJson<Cliente> getDaoCliente() {
		return daoCliente;
	}

	public void setDaoCliente(DaoJson<Cliente> daoCliente) {
		this.daoCliente = daoCliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	
	

}
