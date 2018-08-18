package archivosTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.DaoJson;
import excepciones.ArchivoException;
import geoposicionamiento.ZonaGeografica;
import junit.framework.Assert;
import usuarios.Administrador;


public class ArchivoAdministradoresTest {
	
	DaoJson<Administrador> daoAdministrador;
	List<Administrador> administradores;
	
	@Before
	public void init() {
		
		daoAdministrador = new DaoJson<Administrador>("administradores.json");
		List<Administrador> administradores = daoAdministrador.obtener();
	}

	@Test
	public void testLecturaArchivo() throws ArchivoException {
		 
		Assert.assertTrue(administradores.size() > 1);
	}
	
	@Test
	public void testLecturaCorrecta(){
		
		Administrador admin = administradores.get(0);
		assertEquals("admNombre", admin.getNombre());
		
	}
	
	
	


}
