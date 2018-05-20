package dispositivoInteligenteTest;

//import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import entities.DispositivoInteligente;
import entities.Modo;
import entities.ModoApagado;
import entities.ModoEncendido;

public class DispositivoInteligenteTest {

	 private DispositivoInteligente dispositivoInteligente;
	 private Modo conexion;

	    @Before
	    public void ini() {
	        this.conexion = new ModoApagado();
	        this.dispositivoInteligente=new DispositivoInteligente(conexion);
	    }

	    @Test
	    public void testEstadoInicial() {
	        assertEquals(true, this.dispositivoInteligente.estaEncendido());
	    }

	    @Test
	    public void testApagadoEncender() {
	    	this.conexion = new ModoEncendido();
	    	this.dispositivoInteligente=new DispositivoInteligente(conexion);
	        this.dispositivoInteligente.encender();
	        assertEquals(this.conexion, this.dispositivoInteligente.getModo());
	    }

	    @Test
	    public void testApagadoApagar() {
	        this.conexion = new ModoApagado();
	    	this.dispositivoInteligente=new DispositivoInteligente(conexion);
	        this.dispositivoInteligente.apagar();
	        assertEquals(this.conexion, this.dispositivoInteligente.getModo());
	    }

	    @Test
	    public void testApagadoNoSoportadoAhorroEnergia() {
	        try {
	            this.dispositivoInteligente.ahorrarEnergia();
	            fail();
	        } catch (UnsupportedOperationException ignored) {
	            ignored.toString();
	        }
	    }

	
}
