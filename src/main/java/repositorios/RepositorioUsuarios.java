package repositorios;

import java.util.List;

import ar.frba.dds.grupo3.entities.Administrador;
import ar.frba.dds.grupo3.entities.Cliente;
import dao.JsonClientesDAO;
import excepciones.ArchivoException;

public class RepositorioUsuarios {

	private List<Cliente> clientes;
	private List<Administrador> administradores;

	public void obtenerClientes() throws ArchivoException {

		JsonClientesDAO daoClientes = new JsonClientesDAO("usuarios.json");
		clientes = daoClientes.obtenerTodos();
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

}
