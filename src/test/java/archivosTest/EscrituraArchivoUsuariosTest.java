package archivosTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.Administrador;
import entities.Cliente;
import entities.Dispositivo;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;

public class EscrituraArchivoUsuariosTest {

	RepositorioUsuarios repoUsuarios;

	@Before
	public void init() {

		repoUsuarios = new RepositorioUsuarios();
	}

	@Test
	public void testEscrituraUsuarios() throws ArchivoException {

		Cliente cli = new Cliente();

		cli.setApellido("User");

		Dispositivo disp = new Dispositivo();
		disp.setEncendido(false);
		disp.setKwHora(50);
		disp.setNombre("Panel");
		cli.agregarDispositivo(disp);
		
		int cantidadUsuariosPrevios = repoUsuarios.obtenerClientes().size();
		
		repoUsuarios.agregarUsuario(cli);
		repoUsuarios.guardar();
		repoUsuarios.recargarUsuarios();
		
		Assert.assertEquals(cantidadUsuariosPrevios+1, repoUsuarios.obtenerClientes().size());
	}

}
