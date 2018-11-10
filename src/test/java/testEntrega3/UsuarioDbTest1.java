package testEntrega3;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Ubicable;
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
		cliente.setUbicable(new Ubicable());
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
		cliente.getUbicable().addCoordenadas(new Coordenada(cliente.getUbicable(), 3333,3333 ));
		
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
		
		//MODIFICO CLIENTE AGREGANDO DISPOSITIVO
		cliente2.agregarDispositivo(new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1)) );
		model.modificar(cliente2);
		
		
		//MODIFICO CLIENTE AGREGANDO COORDENADA
		Coordenada c = new Coordenada(cliente2.getUbicable(), 54545,54545 );
		cliente2.getUbicable().addCoordenadas( c );
		
		model.modificar(cliente2);
		
		Coordenada c2 = cliente2.getUbicable().getCoordenada();
		Assert.assertEquals(54545.0, c2.latitud);
		Assert.assertEquals(54545.0, c2.longitud);	
		
	}

	/*@Test
	public void test2ClienteRecuperarYModificarCoordenada(){
		
		Cliente cliente2 = model.buscar(Cliente.class, cliente.id);
		
		Coordenada c = new Coordenada(cliente2.getUbicable(), 54545,54545 );
		cliente2.getUbicable().addCoordenadas( c );
		
		model.modificar(cliente2);
		
		Coordenada c2 = cliente2.getUbicable().getCoordenada();
		Assert.assertEquals(54545.0, c2.latitud);
		Assert.assertEquals(54545.0, c2.longitud);		
	}
	*/


}
