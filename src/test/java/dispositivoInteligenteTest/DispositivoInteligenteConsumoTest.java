
package dispositivoInteligenteTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoInteligente;
import dispositivos.Modo;
import dispositivos.ModoAhorroEnergia;
import dispositivos.ModoApagado;
import dispositivos.ModoConConsumo;
import dispositivos.ModoEncendido;

public class DispositivoInteligenteConsumoTest {
	
	
	private DispositivoInteligente di;
	private Modo apagado;
	
	@Before
	public void init(){
		
		apagado = new ModoApagado();
		
		di = new DispositivoInteligente(apagado , new DispositivoDetalle());
		
		di.encender();
		di.ahorrarEnergia();
		
	}
	
	@Test
	public void testCambiosDeModo()
	{
		
		assertEquals( 3 , di.cantidadLogModo() );
	}
	
	@Test
	public void testFiltrarModoEnPeriodo(){
		
		LocalDateTime inicia= LocalDateTime.now().plusHours(-1);
		LocalDateTime fin=inicia.plusHours(3);
		
		List<Modo> modos = di.filtrarModosEnPeriodo( inicia,fin , di.getLogModos());
		
		assertEquals(2, modos.size());
	}
	
	@Test
	public void testConsumoPeriodo(){
		
		LocalDateTime inicio = LocalDateTime.now();
		LocalDateTime fin = inicio.plusMinutes(10);

		di.avisoConsumo(inicio, fin, 10);
		
		di.encender();
		
		di.avisoConsumo(inicio, fin, 10);
		
		float consumoTotal = di.consumoPeriodo(inicio, fin);
		ModoConConsumo encendido = (ModoConConsumo) di.modoActual();
		assertEquals(1,encendido.cantidadConsumos());
		
		assertEquals(20, consumoTotal  , 0);
		
	}
	
	@Test
	public void testConsumoUltimasNHoras(){
		
		LocalDateTime inicio = LocalDateTime.now().plusMinutes(-30);
		LocalDateTime fin = inicio.plusMinutes(-10);

		di.avisoConsumo(inicio, fin, 10);
		
		di.encender();
		
		di.avisoConsumo(inicio, fin, 10);
		
		float consumoTotal = di.consumoUltimasNHoras(6);
		
		assertEquals(20, consumoTotal  , 0);
		
	}

}