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
import usuarios.Cliente;

public class HorasMaximasConDispositivosTest {
	ArrayList<ResultadoHora> resultados=null;
	Optimizador simplex=null;
	@Before
	public void init() {
		DispositivoUsuario lcd,lavaropas,ventilador,heladera;
		lcd=new DispositivoUsuario() {
			
			@Override
			public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}
			@Override
			public double getConsumoKwHora() {	return 0.18;
			}
			@Override
			public String getIdentificacion() {	return "lcd";}
			@Override
			public double getHsMensualMinimo() {	return 90;}
			@Override
			public double getHsMensualMaximo() {	return 370;}
			@Override 
			public boolean esEsencial() {return true;}
			@Override
			public boolean esInteligente() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		lavaropas=new DispositivoUsuario() {
			@Override
			public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}
			
			@Override
			public double getConsumoKwHora() {	return 0.875;}
			@Override
			public String getIdentificacion() {	return "lavarropas";}
			@Override
			public double getHsMensualMinimo() {	return 6;}
			@Override
			public double getHsMensualMaximo() {	return 30;}

			@Override 
			public boolean esEsencial() {return true;}

			@Override
			public boolean esInteligente() {
				return false;
			}

			@Override
			public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		ventilador=new DispositivoUsuario() {
			@Override
			public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}
			@Override
			public double getConsumoKwHora() {	return 0.06;}
			@Override
			public String getIdentificacion() {	return "ventilador";}
			@Override
			public double getHsMensualMinimo() {	return 120;}
			@Override
			public double getHsMensualMaximo() {	return 360;}
			@Override 
			public boolean esEsencial() {return true;}
			@Override
			public boolean esInteligente() {
				return false;
			}
			@Override
			public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		heladera=new DispositivoUsuario() {	
			@Override
			public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}
			@Override
			public double getConsumoKwHora() {	return 0;}
			@Override
			public String getIdentificacion() {	return "heladera";}
			@Override
			public double getHsMensualMinimo() {	return 0;}
			@Override
			public double getHsMensualMaximo() {	return 0;}
			@Override 
			public boolean esEsencial() {return true;}
			@Override
			public boolean esInteligente() {
				return false;
			}
			@Override
			public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		simplex = new Optimizador();
		simplex.setConsumoMaximoDeEnergia(450000);
		simplex.cargarDispositivos(lcd,lavaropas,ventilador);
		simplex.maximizar();
		 resultados=simplex.getHorasDeCadaDispositivo();
	}
	
	@Test
	public void horasMaximas() {
		Assert.assertEquals(resultados.get(0).getHorasQuePuedeConsumir(),370.0,0.001);
		Assert.assertEquals(resultados.get(1).getHorasQuePuedeConsumir(),30.0,0.001);
		Assert.assertEquals(resultados.get(2).getHorasQuePuedeConsumir(),360.0,0.001);
		Assert.assertEquals(resultados.get(0).getDispositivo(),"lcd");
		System.out.print("kWh que consume el cliente en esas horas optimizadas : "+simplex.getMaximaEnergiaResultado());
		Assert.assertTrue(!simplex.consumioSuMaximaEnergia());//no supèro su maxima energia
	}

}
