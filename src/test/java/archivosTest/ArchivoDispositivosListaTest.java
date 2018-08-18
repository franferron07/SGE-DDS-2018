package archivosTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.DaoJson;
import dispositivos.DispositivoDetalle;
import junit.framework.Assert;

public class ArchivoDispositivosListaTest {
	
	DaoJson<DispositivoDetalle> daoTest;
	DispositivoDetalle dispositivo;
	
	@Before
	public void init(){
		
		daoTest = new DaoJson<DispositivoDetalle>("dispositivosLista.json");
	    dispositivo= new DispositivoDetalle();
		dispositivo.setNombre("test");
		dispositivo.setDescripcion("test");
		
	}
	
	@Test
	public void testLecturaDispositivos(){
		
		List<DispositivoDetalle> dispositivos = daoTest.obtener();
		Assert.assertEquals(22 , dispositivos.size());
	}
	
	@Test
	public void testDeserealizarArchivo(){
		
		String contenido = daoTest.deserealizarArchivo();
		Assert.assertNotNull(contenido);
	}
	
	/*@Test
	public void testAgregarDato(){

		daoTest.guardar(dispositivo);
		Assert.assertEquals(23, daoTest.datos.size());
		
	}*/
	
	/*@Test
	public void testBorrarDato(){

		daoTest.borrar(dispositivo);
		Assert.assertEquals(22, daoTest.datos.size());
	}*/

}