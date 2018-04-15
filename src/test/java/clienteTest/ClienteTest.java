package clienteTest;

import org.junit.Before;
import org.junit.Test;

import ar.frba.dds.grupo3.entities.Cliente;
import junit.framework.Assert;

public class ClienteTest {
	
	
	private Cliente cliente;
	
	@Before
	public void init(){
		cliente = new Cliente();
	}
	
	@Test
	public void testUsuarioCreacion(){
		
		cliente.setNombre("Carlos");
		cliente.setApellido("Lopez");
		Assert.assertEquals("Carlos", cliente.getNombre());
		
	}
	
	@Test
	public void testCantidadDesipositivos(){
		Assert.assertEquals(0, cliente.cantidadDispositivos());
	}

}
