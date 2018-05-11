package archivosTest;

import org.junit.Before;
import org.junit.Test;

import entities.Cliente;
import entities.DispositivoInteligente;
import entities.ModoEncendido;
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

		DispositivoInteligente disp = new DispositivoInteligente();
		disp.setNombre("Panel");
		ModoEncendido modoEncendido=new ModoEncendido();
		modoEncendido.encenderse(disp);
		
		cli.agregarDispositivo(disp);
		
		
		
		
		int cantidadUsuariosPrevios = repoUsuarios.obtenerClientes().size();
		
		repoUsuarios.agregarUsuario(cli);
		repoUsuarios.guardar();
		repoUsuarios.recargarUsuarios();
		
		Assert.assertEquals(cantidadUsuariosPrevios+1, repoUsuarios.obtenerClientes().size());
	}

}
