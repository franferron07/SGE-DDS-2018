package testEntrega3;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
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
		cliente.setTelefonoContacto("44442222");
		cliente.setPuntaje(0);
		cliente.setAccionadoAutomatico(false);
		cliente.setCategoria( categoria );
		
		di_estandar = new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1));
		
		cliente.agregarDispositivo(di_estandar);
		
	}
	
	
	@Test
	public void testCrearCliente(){
		
		model.agregar(categoria);
		
		di_estandar.cliente = cliente;
		
		model.agregar(cliente);
	}
	
	/*@Test
	public void testRecuperarCliente(){
		Cliente cliente2 = model.buscar(Cliente.class, 1);
		
		Assert.assertEquals(cliente.getNombre(), cliente2.getNombre());
	}*/
	

}
