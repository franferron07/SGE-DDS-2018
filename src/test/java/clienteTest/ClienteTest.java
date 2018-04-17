package clienteTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.Cliente;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;

public class ClienteTest {

	private Cliente cliente;

	@Before
	public void init() {
		cliente = new Cliente();
	}

	@Test
	public void testUsuarioCreacion() {

		cliente.setNombre("Carlos");
		cliente.setApellido("Lopez");
		Assert.assertEquals("Carlos", cliente.getNombre());

	}

	@Test
	public void testCantidadDesipositivos() {
		Assert.assertEquals(0, cliente.cantidadDispositivos());
	}

	@Test
	public void testClienteCantDispositivos() throws ArchivoException {
		RepositorioUsuarios repo = new RepositorioUsuarios();
		repo.obtenerClientes();
		List<Cliente> clientes = repo.obtenerClientes();
		Assert.assertEquals(2, clientes.get(0).cantidadDispositivos());
	}

	@Test
	public void testClienteCantDispositivosEncendidos() throws ArchivoException {
		RepositorioUsuarios repo = new RepositorioUsuarios();
		repo.obtenerClientes();
		List<Cliente> clientes = repo.obtenerClientes();
		Assert.assertEquals(1, clientes.get(0).cantidadDispositivosEncendidos());
	}

	@Test
	public void testClienteCantDispositivosApagados() throws ArchivoException {
		RepositorioUsuarios repo = new RepositorioUsuarios();
		repo.obtenerClientes();
		List<Cliente> clientes = repo.obtenerClientes();
		Assert.assertEquals(1, clientes.get(0).cantidadDispositivosApagados());
	}

}
