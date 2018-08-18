package geoposicionamientoTest;

import org.junit.Before;
import org.junit.Test;


import dao.DaoJson;

import static org.junit.Assert.assertEquals;

import java.awt.Polygon;
import java.util.List;

import geoposicionamiento.Mapa;
import geoposicionamiento.ZonaGeografica;

public class GeoposicionamientoTest {
	
	
	private Mapa mapa;
	
	
	@Before
	public void init(){
		
		mapa = new Mapa();
		
	}
	
	@Test 
	public void testNuevaZona(){
		
		
		int[] puntosX = {1,1,10,10};
		int[] puntosY = {1,20,1,20};
		ZonaGeografica zona = new ZonaGeografica( puntosX , puntosY);
		
		assertEquals( zona.getLimitesZona().npoints , 4 );

	}
	
	@Test
	public void testLecturaDeZonasCantidadCorrecta(){

		assertEquals( mapa.getZonasGeograficas().size() , 1  );
	}
	
	@Test
	public void testLecturaDeZonasInicializadasCorrectamente(){
		
		ZonaGeografica zona2 = mapa.getZonasGeograficas().get(0);
		Polygon poligon = zona2.getLimitesZona(); 
		
		assertEquals( 4 , poligon.npoints );
		//assertEquals( mapa.getZonasGeograficas().size() , 1  );
	}
	
	
	@Test
	public void testPrueba(){
		DaoJson<ZonaGeografica> daoZona;
		List<ZonaGeografica> zonas;
		daoZona = new DaoJson<ZonaGeografica>("zonasGeograficas.json");
		zonas = daoZona.obtener();
		
		ZonaGeografica zona2 = mapa.getZonasGeograficas().get(0);
		Polygon poligon = zona2.getLimitesZona(); 
		
		assertEquals( 4 , poligon.npoints );
	}

}
