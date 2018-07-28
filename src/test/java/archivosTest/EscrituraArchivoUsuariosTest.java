package archivosTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.JsonUsuariosDAO;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import dispositivos.ModoApagado;
import dispositivos.ModoEncendido;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;
import usuarios.Cliente;

public class EscrituraArchivoUsuariosTest {

	RepositorioUsuarios repoUsuarios;

	@Before
	public void init() {
		JsonUsuariosDAO jsonUsuariosDAO=new JsonUsuariosDAO("usuarios.json");
		repoUsuarios = new RepositorioUsuarios(jsonUsuariosDAO);
	}

	@Test
	public void testEscrituraUsuarios() throws ArchivoException {

		//Cliente cli = new Cliente();
		   List<DispositivoUsuario> dispositivos=new ArrayList<DispositivoUsuario>();
	        DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(new ModoApagado());
	        dispositivos.add(dispositivoInteligente);
	               
			Cliente cliente1 = new Cliente(dispositivos);
			
			cliente1.setApellido("User");

		DispositivoInteligente disp = new DispositivoInteligente(new ModoApagado());
		ModoEncendido modoEncendido=new ModoEncendido();
		modoEncendido.encenderse(disp);
		
		cliente1.agregarDispositivo(disp);
		
		
		
		
		int cantidadUsuariosPrevios = repoUsuarios.obtenerClientes().size();
		
		repoUsuarios.guardar(cliente1);
		
		Assert.assertEquals(cantidadUsuariosPrevios+1, repoUsuarios.obtenerClientes().size());
	}

}
