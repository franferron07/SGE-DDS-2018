package archivosTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.JsonDispositivoListaDAO;
import dispositivos.DispositivoLista;
import junit.framework.Assert;

public class LecturaArchivoDispositivosListaTest {
	
	JsonDispositivoListaDAO daoTest;
	
	@Before
	public void init(){
		
		daoTest = new JsonDispositivoListaDAO("dispositivosLista.json");
	}
	
	@Test
	public void testLecturaDispositivos(){
		
		List<DispositivoLista> dispositivos = daoTest.obtener();
		Assert.assertEquals(22 , dispositivos.size());
	}

}
