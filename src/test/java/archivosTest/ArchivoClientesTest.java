package archivosTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.DaoJson;
import excepciones.ArchivoException;
import junit.framework.Assert;
import usuarios.Cliente;

public class ArchivoClientesTest {
	
	
	DaoJson<Cliente> daoCliente;
	List<Cliente> clientes;
	
	@Before
	public void init() {
		
		daoCliente = new DaoJson<Cliente>("clientes.json");
		clientes = daoCliente.obtener();
	}

	@Test
	public void testLecturaArchivo() throws ArchivoException {
		 
		Assert.assertTrue(clientes.size() > 1);
	}
	
	@Test
	public void testLecturaCorrecta(){
		
		Cliente cli = clientes.get(0);
		assertEquals("cliNombre", cli.getNombre());
		
	}
	

}
