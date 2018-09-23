package geoposicionamientoTest;

import org.junit.Before;
import org.junit.Test;


import dao.DaoJson;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Polygon;
import java.util.List;

import geoposicionamiento.Mapa;
import geoposicionamiento.Transformador;
import geoposicionamiento.ZonaGeografica;
import usuarios.Cliente;

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
		ZonaGeografica zona = new ZonaGeografica( puntosX , puntosY , 4);
		
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
	
	
	/*@Test
	public void testPrueba(){
		DaoJson<ZonaGeografica> daoZona;
		List<ZonaGeografica> zonas;
		daoZona = new DaoJson<ZonaGeografica>("zonasGeograficas.json");
		zonas = daoZona.obtener();
		
		ZonaGeografica zona2 = mapa.getZonasGeograficas().get(0);
		Polygon poligon = zona2.getLimitesZona(); 
		
		assertEquals( 4 , poligon.npoints );
	}*/
	
	@Test
	public void testPruebaMapa(){
		
		Mapa mapa2 = new Mapa();
		
		int[] puntosX = {1,10,1,10};
		int[] puntosY = {1,1,20,20};
		//declaro zona
		ZonaGeografica zona = new ZonaGeografica( puntosX , puntosY , 4);

		//declaro transformador t1 y t2 estan dentro t3 fuera
		Transformador t1;
		Transformador t2;
		Transformador t3;
		t1= new Transformador( new Point(4,15) );
		t2= new Transformador( new Point(8,19) );
		t3= new Transformador( new Point(11,19) );
		
		//agrego zona en mapa ya que por archivo indica error
		mapa2.getZonasGeograficas().add(zona);
		
		//asigno transformador en la zona correcta
		mapa2.asignarZonaTransformador(t1);
		mapa2.asignarZonaTransformador(t2);
		mapa2.asignarZonaTransformador(t3);
		
		//creo cliente y verifico en cual transformador se asigna.
		Cliente cliente = new Cliente();
		cliente.setNombre("test");
		cliente.setCoordenadas(new Point(2,18));
		
		mapa2.asignarZonaCliente(cliente);
		
		assertEquals( mapa2.getZonasGeograficas().size() , 1  );
		assertEquals(2 , zona.getTransformadores().size());
		
		assertEquals( 1 , t1.getClientes().size()  );
		
		
	}
	

}
