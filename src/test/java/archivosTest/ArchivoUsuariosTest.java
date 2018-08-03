package archivosTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.DaoJson;
import excepciones.ArchivoException;
import junit.framework.Assert;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.Usuario;

public class ArchivoUsuariosTest {

	DaoJson<Usuario> daoUsuarios;
	
	@Before
	public void init() {
		
		daoUsuarios = new DaoJson<Usuario>("usuarios.json");

	}

	@Test
	public void testLecturaArchivo() throws ArchivoException {
		List<Usuario> usuarios = daoUsuarios.obtener(); 
		Assert.assertTrue(usuarios.size() > 1);
		/*Cliente usuario1 = (Cliente) usuarios.get(0);
		Assert.assertEquals(usuario1.getNombre() , "cliNombre");*/
	}


}
