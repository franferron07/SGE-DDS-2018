package dispositivoInteligenteTest;

//import junit.framework.Assert;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import entities.DispositivoInteligente;
import entities.Modo;
import entities.ModoAhorroEnergia;
import entities.ModoApagado;
import entities.ModoEncendido;

public class DispositivoInteligenteTest {

	 private DispositivoInteligente dispositivoInteligente;
	 private Modo modo;

	    @Before
	    public void init() {
	        modo = new ModoEncendido(10); // new ModoApagado() // new ModoAhorroEnergia()
	        dispositivoInteligente=new DispositivoInteligente(modo);
	    }

	    @Test
	    public void testEstadoInicial() {
	        assertEquals(true, !dispositivoInteligente.estaEncendido());
	    }

	    @Test
	    public void testApagadoEncender() {
	        dispositivoInteligente.encender();
	        assertEquals(new ModoEncendido(20).toString(),dispositivoInteligente.getModo().toString());
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
	            assertEquals(new ModoAhorroEnergia(30).toString(),dispositivoInteligente.getModo().toString());
	        } catch (UnsupportedOperationException ignored) {
	            ignored.toString();
	        }
	    }
	
}
