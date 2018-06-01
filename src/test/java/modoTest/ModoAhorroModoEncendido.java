package modoTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import entities.ModoEncendido;

public class ModoAhorroModoEncendido {
	
	
	private ModoEncendido modoEncendido;
	
	@Before
	public void init(){
		
		modoEncendido = new ModoEncendido();
		
	}

	@Test
	public void testCrearModo(){
		
		LocalDateTime fecha=LocalDateTime.now();
		ModoEncendido modoEncendidoPrueba= new ModoEncendido();
		
		assertEquals(fecha.getDayOfMonth(), modoEncendidoPrueba.getFechaHoraInicio().getDayOfMonth());
		assertEquals(0, modoEncendidoPrueba.getConsumos().size());
	}
	
	@Test
	public void testLogearConsumo(){
		
		LocalDateTime fecha = LocalDateTime.now();
		float consumo = 30;
		
		modoEncendido.registrarConsumo(fecha, fecha, consumo);
		assertEquals(1 , modoEncendido.getConsumos().size());
		
	}
	

}
