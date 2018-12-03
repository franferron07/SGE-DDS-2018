package dbTest;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Ubicable;
import models.ModelHelper;
import usuarios.Administrador;
import usuarios.Categoria;
import usuarios.Cliente;

public class CreoClienteTest {
	
	
private ModelHelper model;
	
	
	@Before
	public void init() {
		this.model = new ModelHelper();
				
		//CLIENTE
		Categoria categoria = new Categoria("Baja");
		
		Cliente cliente = new Cliente();
		cliente.setUbicable(new Ubicable());
		cliente.setNombre("Carlos");
		cliente.setApellido("garro");
		cliente.setTipoDocumento("DNI");
		cliente.setUsername("test2");
		cliente.setPassword("test2");
		//cliente.setDomicilio("calle falsa 1234");
		cliente.setTelefonoContacto("44442222");
		cliente.setPuntaje(0);
		cliente.setAccionadoAutomatico(false);
		cliente.setCategoria( categoria );
		cliente.getUbicable().addCoordenadas(new Coordenada(cliente.getUbicable(), 111,111 ));
		
		
		model.agregar(categoria);
		model.agregar(cliente);
		
		
		
	}
	
	@Test
    public void testInicial() {
       
    }

}
