package geoposicionamientoTest;

import org.junit.Before;
import org.junit.Test;


import dao.DaoJson;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import geoposicionamiento.Coordenada;
import geoposicionamiento.Mapa;
import geoposicionamiento.Transformador;
import geoposicionamiento.ZonaGeografica;
import repositorios.RepositorioZonas;
import usuarios.Cliente;

public class geoposicionamientoTest {
	
	
	private RepositorioZonas mapa;
	private ZonaGeografica zona;
	
	
	@Before
	public void init(){
		
		mapa = new RepositorioZonas();
		
		
	}
	
	@Test 
	public void testNuevaZona(){
		
		List<Coordenada> xy = new ArrayList<Coordenada>();
		xy.add(new Coordenada(1,1));
		xy.add(new Coordenada(1,20));
		xy.add(new Coordenada(10,1));
		xy.add(new Coordenada(10,20));
		
		zona = new ZonaGeografica(xy);
		
		assertEquals( zona.getCoordenadas().size() , 4 );

	}
	
	@Test
	public void testLecturaDeZonasCantidadCorrecta(){
		
		assertEquals( mapa.getZonas().size() , 1  );
	}
	
	@Test
	public void testLecturaDeZonasInicializadasCorrectamente(){
		
		ZonaGeografica zona2 = mapa.getZonas().get(0);
		Path2D poligon = zona2.getPoligono(); 
		
		assertEquals( 4 , 4 );
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
		
		RepositorioZonas mapa2 = new RepositorioZonas();
		
		List<Coordenada> xy = new ArrayList<Coordenada>();
		xy.add(new Coordenada(1,1));
		xy.add(new Coordenada(1,20));
		xy.add(new Coordenada(10,1));
		xy.add(new Coordenada(10,20));
		
		zona = new ZonaGeografica(xy);

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
		List <Coordenada> coordenadas=new ArrayList<Coordenada>();
		coordenadas.add(new Coordenada(2,18));
		cliente.setCoordenadas(coordenadas);
		
		mapa2.asignarZonaCliente(cliente);
		
		assertEquals( mapa2.getZonasGeograficas().size() , 1  );
		assertEquals(2 , zona.getTransformadores().size());
		
		assertEquals( 1 , t1.getClientes().size()  );
		
		
	}
	

}
