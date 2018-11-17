package dbTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Ubicable;
import junit.framework.Assert;
import models.ModelHelper;
import usuarios.Administrador;
import usuarios.Categoria;
import usuarios.Cliente;

public class InicializacionDatosTest {
	
	private ModelHelper model;
	
	
	@Before
	public void init() {
		this.model = new ModelHelper();
		
		
		//ADMINISTRADOR
		Administrador admin = new Administrador();
		admin.setNombre("Roberto");
		admin.setApellido("Garcia");
		admin.setUsername("admin");
		admin.setPassword("admin");
		
		model.agregar(admin);

		
		//CLIENTE
		Categoria categoria = new Categoria("Baja");
		
		Cliente cliente = new Cliente();
		cliente.setUbicable(new Ubicable());
		cliente.setNombre("Mario");
		cliente.setApellido("Perez");
		cliente.setTipoDocumento("DNI");
		cliente.setUsername("test");
		cliente.setPassword("test");
		//cliente.setDomicilio("calle falsa 1234");
		cliente.setTelefonoContacto("44442222");
		cliente.setPuntaje(0);
		cliente.setAccionadoAutomatico(false);
		cliente.setCategoria( categoria );
		cliente.getUbicable().addCoordenadas(new Coordenada(cliente.getUbicable(), 3333,3333 ));
		
		DispositivoEstandar di_estandar = new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1));
		
		cliente.agregarDispositivo(di_estandar);
		
		cliente.agregarDispositivo(new DispositivoEstandar(model.buscar(DispositivoDetalle.class, 1)) );
		
		//agrego di inteligente con modos y consumo
		DispositivoInteligente di = new DispositivoInteligente(model.buscar(DispositivoDetalle.class, 5));
		di.encender();
		di.ahorrarEnergia();
		
		LocalDateTime inicio = LocalDateTime.now();
		LocalDateTime fin = inicio.plusMinutes(10);
		di.avisoConsumo(inicio, fin, 10);

		di.encender();
		di.avisoConsumo(inicio, fin, 10);
		
		model.agregar(di);
		model.agregar(categoria);
		model.agregar(cliente);
		
		
		
	}
	
	@Test
    public void testInicial() {
       
    }
	

}
