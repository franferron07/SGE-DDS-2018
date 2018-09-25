package testEntrega3;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoInteligente;
import junit.framework.Assert;
import models.ModelHelper;

public class DispositivoDbTest2 {
	
	private ModelHelper model;
	private DispositivoDetalle detalle;
	
	@Before
	public void init() {
		this.model = new ModelHelper();

		detalle= model.buscar(DispositivoDetalle.class, 1);
		
	}
	
	@Test
	public void testDispositivoDetalle(){
		
		Assert.assertEquals("Aire Acondicionado", detalle.getNombre());
	}
	
	
	
	@Test
	public void testCrearDispositivo() {
		
		DispositivoInteligente di = new DispositivoInteligente(detalle);
		
		di.encender();
		LocalDateTime inicio = LocalDateTime.now();
		LocalDateTime fin = inicio.plusMinutes(10);

		di.avisoConsumo(inicio, fin, 10);
		
		di.apagar();
		
		model.agregar(di);
	
		
	}
	
	

}
