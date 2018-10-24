package datos_transformadores;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import geoposicionamiento.Coordenada;
import geoposicionamiento.Mapa;
import geoposicionamiento.Transformador;
import geoposicionamiento.ZonaGeografica;
import models.ModelHelper;
import models.TransformadorModel;
import usuarios.Cliente;

public class TestTransformadores {

	public ModelHelper model;
	public TransformadorModel transformadorModel;
	public Transformador t1;
	public Transformador t2;
	public Transformador t3;
	
	
	@Before
	public void init() {

		this.model = new ModelHelper();
		this.transformadorModel=new TransformadorModel();
		t1= new Transformador( new Coordenada(4,15) );
		t2= new Transformador( new Coordenada(8,19) );
		t3= new Transformador( new Coordenada(11,19) );
		
		List<Coordenada> xy = new ArrayList<Coordenada>();
		xy.add(new Coordenada(1,1));
		xy.add(new Coordenada(1,20));
		xy.add(new Coordenada(10,1));
		xy.add(new Coordenada(10,20));
		
		ZonaGeografica zona = new ZonaGeografica(xy);

		//agrego zona en mapa ya que por archivo indica error
		Mapa.getZonasGeograficas().add(zona);
		
		//asigno transformador en la zona correcta
		Mapa.asignarZonaTransformador(t1);
		Mapa.asignarZonaTransformador(t2);
		Mapa.asignarZonaTransformador(t3);
	}

	@Test
	public void levantarTransformadores() {
		this.model.agregar(t1);
		this.model.agregar(t2);
		this.model.agregar(t2);
	}
//	@Test
//	public void testRecuperarTransformador() {
//		Transformador transformador = this.transformadorModel.buscarTransformadorPorID(1);
//		try {
//			Cliente cliente = new Cliente();
//			cliente.setApellido("unApellido");
//			cliente.setNumeroDocumento("111111");
//			transformador.agregarCliente(cliente);
//			this.model.modificar(transformador);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void testAgregarTransformadorDesdeJson() {
//		Transformador unTransformador = new Transformador();
//		
////		this.model.modificar(un);
//	}
////	@Test
////	public void testRecuperarUsuariosDelTransformador() {
////		Transformador t = this.transformadorModel.buscarTransformadorPorID(1);
////		for(Cliente cli : t.getClientes())
////			System.out.println(cli.getNombre());
////		}
////	}
		
	}
