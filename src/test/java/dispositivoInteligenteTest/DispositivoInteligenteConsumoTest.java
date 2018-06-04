package dispositivoInteligenteTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.DispositivoInteligente;
import entities.Modo;
import entities.ModoAhorroEnergia;
import entities.ModoApagado;
import entities.ModoEncendido;

public class DispositivoInteligenteConsumoTest {
	
	
	private DispositivoInteligente di;
	private Modo apagado;
	
	@Before
	public void init(){
		
		apagado = new ModoApagado();
		
		di = new DispositivoInteligente(apagado);
		
		di.encender();
		di.ahorrarEnergia();
		
	}
	
	@Test
	public void testCambiosDeModo()
	{
		
		assertEquals( 2 , di.cantidadLogModo() );
	}
	
	@Test
	public void testFiltrarModoEnPeriodo(){
		
		LocalDateTime inicia= LocalDateTime.now().plusHours(-1);
		LocalDateTime fin=inicia.plusHours(3);
		
		List<Modo> modos = di.filtrarModosEnPeriodo( inicia,fin );
		
		assertEquals(1, modos.size());
	}
	
	@Test
	public void testConsumoPeriodo(){
		
		LocalDateTime inicio = LocalDateTime.now();
		LocalDateTime fin = inicio.plusMinutes(10);
		
		di.avisoConsumo(inicio, fin, 10);
		
	}

}
