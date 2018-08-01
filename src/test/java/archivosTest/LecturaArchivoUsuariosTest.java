package archivosTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.DaoJson;
import dao.JsonUsuariosDAO;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.Usuario;

public class LecturaArchivoUsuariosTest {

	RepositorioUsuarios repoUsuarios;

	@Before
	public void init() {
		//DaoJson<Usuario> jsonUsuariosDAO=new DaoJson("usuarios.json");
		//repoUsuarios = new RepositorioUsuarios(jsonUsuariosDAO);
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
