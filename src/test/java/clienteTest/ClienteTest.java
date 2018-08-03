package clienteTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import dispositivos.ModoApagado;
import excepciones.ArchivoException;
import junit.framework.Assert;
import repositorios.RepositorioUsuarios;
import usuarios.Cliente;

public class ClienteTest {

	private Cliente cliente;

	@Before
	public void init() {
		List<DispositivoUsuario> dispositivos=new ArrayList<DispositivoUsuario>();
        DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(new ModoApagado());
        dispositivos.add(dispositivoInteligente);
               
		cliente = new Cliente(dispositivos);
	}

	@Test
	public void testUsuarioCreacion() {

		cliente.setNombre("Carlos");
		cliente.setApellido("Lopez");
		Assert.assertEquals("Carlos", cliente.getNombre());

	}

	@Test
	public void testCantidadDesipositivos() {
		Assert.assertEquals(0, cliente.cantidadDispositivos());
	}

	

}
