package clienteTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.JsonUsuariosDAO;
import entities.Cliente;
import entities.Dispositivo;
import entities.DispositivoInteligente;
import entities.ModoApagado;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;

public class ClienteTest {

	private Cliente cliente;

	@Before
	public void init() {
		List<Dispositivo> dispositivos=new ArrayList<Dispositivo>();
        DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(new ModoApagado());
        dispositivoInteligente.setNombre("DispositivoInteligente1");
        dispositivos.add(dispositivoInteligente);
               
		cliente = new Cliente(dispositivos);
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
		JsonUsuariosDAO jsonUsuariosDAO=new JsonUsuariosDAO("usuarios.json");
		RepositorioUsuarios repoUsuarios = new RepositorioUsuarios(jsonUsuariosDAO);
		List<Cliente> clientes = repoUsuarios.obtenerClientes();
		Assert.assertEquals(2, clientes.get(0).cantidadDispositivos());
	}

	@Test
	public void testClienteCantDispositivosEncendidos() throws ArchivoException {
		JsonUsuariosDAO jsonUsuariosDAO=new JsonUsuariosDAO("usuarios.json");
		RepositorioUsuarios repoUsuarios = new RepositorioUsuarios(jsonUsuariosDAO);
		List<Cliente> clientes = repoUsuarios.obtenerClientes();
		Assert.assertEquals(1, clientes.get(0).cantidadDispositivosEncendidos());
	}

	@Test
	public void testClienteCantDispositivosApagados() throws ArchivoException {
		JsonUsuariosDAO jsonUsuariosDAO=new JsonUsuariosDAO("usuarios.json");
		RepositorioUsuarios repoUsuarios = new RepositorioUsuarios(jsonUsuariosDAO);
		List<Cliente> clientes = repoUsuarios.obtenerClientes();
		Assert.assertEquals(1, clientes.get(0).cantidadDispositivosApagados());
	}

}
