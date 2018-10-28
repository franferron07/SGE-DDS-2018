package testEntrega3;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import geoposicionamiento.Coordenada;
import junit.framework.Assert;
import models.ModelHelper;
import usuarios.Categoria;
import usuarios.Cliente;


public class UsuarioDbTest1 {
	
	private ModelHelper model;
	private Cliente cliente;
	private Categoria categoria;
	private DispositivoEstandar di_estandar;
	
	@Before
	public void init() {
		this.model = new ModelHelper();
		
		categoria = new Categoria("Baja");
		
		this.cliente = new Cliente();
		cliente.setNombre("Francisco");
		cliente.setApellido("Ferron");
		cliente.setTipoDocumento("DNI");
		cliente.setUsername("test");
		cliente.setPassword("test");
		//cliente.setDomicilio("calle falsa 1234");
		cliente.setTelefonoContacto("44442222");
		cliente.setPuntaje(0);
		cliente.setAccionadoAutomatico(false);
		cliente.setCategoria( categoria );
		cliente.addCoordenadas(new Coordenada(cliente, 3333,3333 ));
		
		di_estandar = new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1));
		
		cliente.agregarDispositivo(di_estandar);
		
		
		
	}
	
	
	@Test
	public void testCrearCliente(){
		
		//CREO CLIENTE
		model.agregar(categoria);
		model.agregar(cliente);
		Assert.assertEquals("Francisco", cliente.getNombre());
		
		//BUSCO CLIENTE
		Cliente cliente2 = model.buscar(Cliente.class, cliente.id);
		Assert.assertEquals(cliente.id, cliente2.id);
		
		//MODIFICO CLIENTE
		cliente2.agregarDispositivo(new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1)) );
		model.modificar(cliente2);
	}

	/*@Test
	public void test2CrearClienteRecuperarYModificar(){
		
		cliente.agregarDispositivo(new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1)) );
		
		Cliente cliente2 = model.buscar(Cliente.class, cliente.id);
		
		Coordenada c = new Coordenada(cliente2, 54545,54545 );
		cliente2.addCoordenadas( c );
		
		//model.modificar(cliente2);
		
		Coordenada c2 = cliente2.getCoordenada();
		Assert.assertEquals(54545.0, c.latitud);
		Assert.assertEquals(54545.0, c.longitud);		
	}*/
	
	
	@Test
	public void test1RecuperarClienteFechaCorrecta(){
		Cliente cliente2 = model.buscar(Cliente.class, cliente.id);

		System.out.println("TEST" + " " + cliente2.getFechaAltaServicio_s() + "--"+cliente2.getFechaAltaServicio());
	}

	

}
