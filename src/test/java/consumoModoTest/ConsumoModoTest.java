package consumoModoTest;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import entities.ConsumoModo;

public class ConsumoModoTest {
	
	
	private LocalDateTime fecha_inicial ;
	private LocalDateTime fecha_final;
	private ConsumoModo consumo;
	private float consumo_periodo ;
	
	@Before
	public void init(){
		
		fecha_inicial = LocalDateTime.now();
		fecha_final = fecha_inicial.plusDays(1);
		consumo_periodo = 10;
		consumo = new ConsumoModo(fecha_inicial , fecha_final , consumo_periodo);
	}
	
	@Test
	public void testCrearConsumoModo(){
		
		ConsumoModo consumo_test = new ConsumoModo( fecha_inicial , fecha_final , 10);
		assertEquals( 10 , consumo_test.getConsumo() , '=' );
	}
	
	@Test
	public void testCumplePeriodo(){
		
		LocalDateTime periodo_inicia = LocalDateTime.now().plusDays(-1);
		LocalDateTime periodo_fin = LocalDateTime.now().plusDays(3);
		
		assertEquals( true , consumo.cumplePeriodoConsumo(periodo_inicia, periodo_fin));
	}
	
	@Test
	public void testNoCumplePeriodo(){
		
		LocalDateTime periodo_inicia = LocalDateTime.now().plusDays(-1);
		LocalDateTime periodo_fin = LocalDateTime.now();
		
		assertEquals( false , consumo.cumplePeriodoConsumo(periodo_inicia, periodo_fin));
		
	}

}