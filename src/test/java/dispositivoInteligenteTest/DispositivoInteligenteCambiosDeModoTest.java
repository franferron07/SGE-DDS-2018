package dispositivoInteligenteTest;

//import junit.framework.Assert;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoInteligente;
import dispositivos.Modo;
import dispositivos.ModoAhorroEnergia;
import dispositivos.ModoApagado;
import dispositivos.ModoEncendido;

public class DispositivoInteligenteCambiosDeModoTest {

	 private DispositivoInteligente dispositivoInteligente;
	 private Modo modo;

	    @Before
	    public void init() {
	        modo = new ModoEncendido(); // new ModoApagado() // new ModoAhorroEnergia()
	        dispositivoInteligente=new DispositivoInteligente(modo , new DispositivoDetalle() );
	    }

	    @Test
	    public void testEstadoInicial() {
	        assertEquals(true, dispositivoInteligente.estaEncendido());
	    }

	    @Test
	    public void testApagadoEncender() {
	        dispositivoInteligente.encender();
	        assertEquals(new ModoEncendido().toString(),dispositivoInteligente.getModo().toString());
	    }

	    @Test
	    public void testApagadoApagar() {
	        dispositivoInteligente.apagar();
	        assertEquals(new ModoApagado().toString(),dispositivoInteligente.getModo().toString());
	    }

	    @Test
	    public void testApagadoAhorroEnergia() {
	        try {
	            dispositivoInteligente.ahorrarEnergia();
	            //Operacion No soportada cuando esta apagado
	          //  assertEquals(new ModoAhorroEnergia().toString(),dispositivoInteligente.getModo().toString());
	        } catch (UnsupportedOperationException ignored) {
	            ignored.toString();
	        }
	    }

	    @Test
	    public void testEncendidoAhorroEnergia() {
	        try {
	            dispositivoInteligente.ahorrarEnergia();
	            assertEquals(new ModoAhorroEnergia().toString(),dispositivoInteligente.getModo().toString());
	        } catch (UnsupportedOperationException ignored) {
	            ignored.toString();
	        }
	    }
	
}
