package testEntrega3;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoInteligente;
import geoposicionamiento.Coordenada;
import junit.framework.Assert;
import models.ModelHelper;
import usuarios.Cliente;

public class DispositivoDbTest2 {
	
	private ModelHelper model;
	private DispositivoDetalle detalle;
	private Cliente cliente;
	private DispositivoInteligente di;
	
	@Before
	public void init() {
		this.model = new ModelHelper();

		cliente = new Cliente();
		cliente.setNombre("Francisco");
		cliente.setApellido("Ferron");
		cliente.setTipoDocumento("DNI");
		cliente.setTelefonoContacto("44442222");
		cliente.setPuntaje(0);
		cliente.setAccionadoAutomatico(false);
		cliente.addCoordenadas(new Coordenada(cliente, 3333,3333 ));
		
		detalle= model.buscar(DispositivoDetalle.class, 1);
		
		di = new DispositivoInteligente(detalle);
		
		di.encender();
		di.apagar();
		di.encender();
		di.apagar();
		
		cliente.agregarDispositivo(di);
		model.agregar(cliente);
		
	}
	
	@Test
	public void testDiCreado(){
		
		Assert.assertEquals(true, di.esInteligente());
	}
	
	
	@Test
	public void testIntervalosEncendidos(){
		
		
		Assert.assertEquals(5, di.cantidadLogModo());
	}
	
	@Test
	public void testModificarAtributoYGrabarlo(){
		
		DispositivoInteligente di2 = model.buscar(DispositivoInteligente.class, di.getId());
		
		di2.setFecha_alta_s("2018-10-10 22:30");
		
		model.modificar(di2);
		
		Assert.assertEquals("2018-10-10 22:30", di2.getFecha_alta_s());
		
	}
	
	

}
