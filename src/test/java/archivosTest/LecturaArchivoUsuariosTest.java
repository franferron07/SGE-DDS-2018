package archivosTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.JsonUsuariosDAO;
import entities.Administrador;
import entities.Cliente;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;

public class LecturaArchivoUsuariosTest {

	RepositorioUsuarios repoUsuarios;

	@Before
	public void init() {
		JsonUsuariosDAO jsonUsuariosDAO=new JsonUsuariosDAO("usuarios.json");
		repoUsuarios = new RepositorioUsuarios(jsonUsuariosDAO);
	}

	@Test
	public void testLecturaClientes() throws ArchivoException {
		List<Cliente> clientes = repoUsuarios.obtenerClientes();
		Assert.assertTrue(clientes.size() > 1);
	}

	@Test
	public void testLecturaAdministradores() throws ArchivoException {
		List<Administrador> administradores = repoUsuarios.obtenerAdministradores();
		Assert.assertEquals(1, administradores.size());
	}

}
