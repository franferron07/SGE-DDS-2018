package testEntrega3;

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
		
		cliente.agregarDispositivo(new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1)) );
		
		model.agregar(categoria);
		model.agregar(cliente);
	}
	
	@Test
	public void testRecuperarCliente(){
		Cliente cliente2 = model.buscar(Cliente.class, 1);
		
		cliente2.agregarDispositivo(di_estandar);
		model.modificar(cliente2);
		
		Assert.assertEquals(cliente.getNombre(), cliente2.getNombre());
	}
	
	
	@Test
	public void testRecuperarClienteFechaCorrecta(){
		Cliente cliente2 = model.buscar(Cliente.class, 6);
		
		
		System.out.println("TEST" + " " + cliente2.getFechaAltaServicio());
	}
	
	

}
