package archivosTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.JsonUsuariosDAO;
import entities.Cliente;
import entities.DispositivoInteligente;
import entities.ModoApagado;
import entities.ModoEncendido;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;

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
		   List<DispositivoInteligente> dispositivos=new ArrayList<DispositivoInteligente>();
	        DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(new ModoApagado());
	        dispositivoInteligente.setNombre("DispositivoInteligente1");
	        dispositivos.add(dispositivoInteligente);
	               
			Cliente cliente1 = new Cliente(dispositivos);
			
			cliente1.setApellido("User");

		DispositivoInteligente disp = new DispositivoInteligente(new ModoApagado());
		disp.setNombre("Panel");
		ModoEncendido modoEncendido=new ModoEncendido();
		modoEncendido.encenderse(disp);
		
		cliente1.agregarDispositivo(disp);
		
		
		
		
		int cantidadUsuariosPrevios = repoUsuarios.obtenerClientes().size();
		
		repoUsuarios.guardar(cliente1);
		
		Assert.assertEquals(cantidadUsuariosPrevios+1, repoUsuarios.obtenerClientes().size());
	}

}
