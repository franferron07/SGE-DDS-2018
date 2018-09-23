package datos_transformadores;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import geoposicionamiento.Mapa;
import geoposicionamiento.Transformador;
import junit.framework.Assert;

public class Transformadores {
	public Mapa mapa=null;
	Transformador t1=null;
	Transformador t2=null;
	Transformador t3=null;
	
	@Before
	private void init() {
		t1= new Transformador( new Point(4,15) );
		t2= new Transformador( new Point(8,19) );
		t3= new Transformador( new Point(11,19) );
	}

	@Test
	public void test() {
		Assert.assertEquals(true, true);
	}
	@Test
	public void recuperarTransformadores(){
		Assert.assertEquals(true, true);
	}

}
