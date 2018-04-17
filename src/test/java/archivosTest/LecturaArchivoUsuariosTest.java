package archivosTest;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.frba.dds.grupo3.entities.Cliente;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;

public class LecturaArchivoUsuariosTest {
	
	RepositorioUsuarios repo_usuarios ;
	
	@Before
	public void init(){
		
		repo_usuarios= new RepositorioUsuarios();
	}
	
	@Test
	public void testLecturaClientes() throws ArchivoException {
		repo_usuarios.obtenerClientes();
		List<Cliente> clientes = repo_usuarios.getClientes();
		Assert.assertEquals(3, clientes.size());
	}
	
	/*@Test
	public void testLecturaAdministradores() throws ArchivoException{
		repo_
		
	}*/
	

}
