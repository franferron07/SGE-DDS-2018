package modoTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import entities.ModoEncendido;

public class ModoConConsumoTest {
	
	
	private ModoEncendido modoEncendido;
	
	@Before
	public void init(){		
		//creo modo encendido con 2 consumos
		
		LocalDateTime fin = LocalDateTime.now().plusDays(3);
		modoEncendido = new ModoEncendido();
		modoEncendido.setFechaHoraFin(fin);
		
		LocalDateTime inicio_1 = modoEncendido.getFechaHoraInicio();
		LocalDateTime fin_1 = inicio_1.plusHours(4);
		float consumo1 = 10;
		
		LocalDateTime inicio2 = modoEncendido.getFechaHoraInicio().plusDays(1);
		LocalDateTime fin2 = inicio2.plusHours(2);
		float consumo2 = 20;
		
		modoEncendido.registrarConsumo(inicio_1, fin_1, consumo1);
		modoEncendido.registrarConsumo(inicio2, fin2, consumo2);
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
		assertEquals(3 , modoEncendido.cantidadConsumos());
		
	}
	
	public void testCumpleIntervalo(){
		
		LocalDateTime inicio_intervalo = LocalDateTime.now().plusDays(-1);
		LocalDateTime fin_intevalo = inicio_intervalo.plusDays(2);
		
		assertEquals(true, modoEncendido.cumpleIntervalo(inicio_intervalo, fin_intevalo));
		
	}
	
	public void testNoCumpleIntervalo(){
		
		LocalDateTime inicio_intervalo = LocalDateTime.now().plusDays(-2);
		LocalDateTime fin_intevalo = inicio_intervalo.plusDays(1);
		
		assertEquals(false, modoEncendido.cumpleIntervalo(inicio_intervalo, fin_intevalo));
		
	}
	
	
	

}