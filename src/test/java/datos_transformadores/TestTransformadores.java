package datos_transformadores;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import geoposicionamiento.Mapa;
import geoposicionamiento.Transformador;
import geoposicionamiento.ZonaGeografica;
import junit.framework.Assert;
import models.ModelHelper;
import models.TransformadorModel;
import usuarios.Cliente;

public class TestTransformadores {

	public ModelHelper model=null;
	public TransformadorModel transformadorModel=null;
	private Mapa mapa=null;
	Transformador t1=null;
	Transformador t2=null;
	Transformador t3=null;
	
	@Before
	private void init() {

		this.model = new ModelHelper();
		this.transformadorModel=new TransformadorModel();
		t1= new Transformador( new Point(4,15) );
		t2= new Transformador( new Point(8,19) );
		t3= new Transformador( new Point(11,19) );
		

		int[] puntosX = {1,10,1,10};
		int[] puntosY = {1,1,20,20};
		//declaro zona
		ZonaGeografica zona = new ZonaGeografica( puntosX , puntosY , 4);

		//agrego zona en mapa ya que por archivo indica error
		mapa.getZonasGeograficas().add(zona);
		
		//asigno transformador en la zona correcta
		mapa.asignarZonaTransformador(t1);
		mapa.asignarZonaTransformador(t2);
		mapa.asignarZonaTransformador(t3);
	}

	@Test
	public void levantarTransformadores() {
		this.model.agregar(t1);
		this.model.agregar(t2);
		this.model.agregar(t2);
	}
	@Test
	public void testRecuperarTransformadorPorID() {
		Transformador transformador = this.transformadorModel.buscarTransformador(1);
		try {
			Cliente cliente = new Cliente();
			cliente.setApellido("unApellido");
			cliente.setNumeroDocumento("111111");
			transformador.agregarCliente(cliente);
			this.model.modificar(transformador);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testAgregarTransformadorDesdeJson() {
		Transformador unTransformador = new Transformador();
		
//		this.model.modificar(un);
	}

}
