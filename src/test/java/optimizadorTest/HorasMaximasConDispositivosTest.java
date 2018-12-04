package optimizadorTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import dispositivos.ModoApagado;
import optimizacion_horas.Optimizador;
import optimizacion_horas.ResultadoHora;
import repositorios.RepositorioDispositivosLista;
import usuarios.Cliente;

public class HorasMaximasConDispositivosTest {
	ArrayList<ResultadoHora> resultados;

	ArrayList<DispositivoUsuario> dispositivos=null;
	Cliente unCliente=null;

	@Before
	public void init(){
		this.dispositivos=new ArrayList<DispositivoUsuario>();
		this.resultados=new ArrayList<ResultadoHora>();
		this.unCliente=new Cliente(){
			@Override
			public String toString() {
				return "unCliente";//this.nombre;
			}
		};
		RepositorioDispositivosLista.cargaDeDispositivosDetalleAUser(RepositorioDispositivosLista.jsonToArrayList("dispositivosLista_v2.json"),this.unCliente);
		Optimizador simplex = new Optimizador();
		simplex.setConsumoMaximoDeEnergia(450000);
		simplex.cargarDispositivos(this.unCliente);
		simplex.maximizar();
		resultados=simplex.getHorasDeCadaDispositivo();
	}


	@Test
	public void horasMaximas() {
		Assert.assertEquals(resultados.get(0).getHorasQuePuedeConsumir(),370.0,0.001);
		Assert.assertEquals(resultados.get(1).getHorasQuePuedeConsumir(),30.0,0.001);
		Assert.assertEquals(resultados.get(2).getHorasQuePuedeConsumir(),360.0,0.001);
		Assert.assertEquals(resultados.get(0).getDispositivo_id(),"LCD , De 8 pulgadas");
	}



}
