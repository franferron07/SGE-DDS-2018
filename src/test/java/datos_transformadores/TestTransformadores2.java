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

public class TestTransformadores2 {

	public ModelHelper model;
	public TransformadorModel transformadorModel;
	public Transformador t1;
	public Transformador t2;
	public Transformador t3;
	public ZonaGeografica zona;
	
	
	@Before
	public void init() {

		this.model = new ModelHelper();
		this.transformadorModel=new TransformadorModel();
		t1= new Transformador( new Coordenada(-34.67,-58.5) );
		t2= new Transformador( new Coordenada(-34.66,-58.43) );
		t3= new Transformador( new Coordenada(-34.68,-58.47) );
		
		List<Coordenada> xy = new ArrayList<Coordenada>();
		xy.add(new Coordenada(-34.697878, -58.468897));
		xy.add(new Coordenada(-34.686919, -58.486813));
		xy.add(new Coordenada(-34.651905, -58.530758));
		xy.add(new Coordenada(-34.65924, -58.418313));
		
		zona = Mapa.factoryZona(xy);

		//agrego zona en mapa ya que por archivo indica error
		//Mapa.getZonasGeograficas().add(zona);
		
		//asigno transformador en la zona correcta
		Mapa.asignarZonaTransformador(t1);
		Mapa.asignarZonaTransformador(t2);
		Mapa.asignarZonaTransformador(t3);
	}

	@Test
	public void levantarTransformadores() {
		this.model.agregar(zona);
		this.model.agregar(t1);
		this.model.agregar(t2);
		this.model.agregar(t3);
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
