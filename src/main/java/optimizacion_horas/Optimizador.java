package optimizacion_horas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dispositivos.DispositivoUsuario;
import usuarios.Cliente;

public class Optimizador {
	private Implementador implementacion_simplex=new UnaImplementacionSimplex();
	
	
	public void cargarDispositivosEsenciales(Cliente cliente) {
		this.implementacion_simplex.cargarDispositivosEsenciales(cliente);
	}
	public void maximizar() {
		this.implementacion_simplex.maximizar();
	}
	public void analisarResultados(LocalDateTime desde, LocalDateTime hasta) {
		this.implementacion_simplex.analisarResultados(desde, hasta);
	}
	public void cargarDispositivos(DispositivoUsuario... _dispositivoUsuarios_) {
		this.implementacion_simplex.cargarDispositivos(_dispositivoUsuarios_);
	}
	public void setConsumoMaximoDeEnergia(double consumoMaximoDeEnergia) {
		this.implementacion_simplex.setConsumoMaximoDeEnergia(consumoMaximoDeEnergia);
	}
	public int cantidadDeDispositivos() {
		return this.implementacion_simplex.cantidadDeDispositivos();
	}
	public ArrayList<ResultadoHora> getHorasDeCadaDispositivo() {
		return this.implementacion_simplex.getHorasDeCadaDispositivo();
	}
	public ArrayList<ResultadoHora> resultados(){
		return this.implementacion_simplex.resultados();
	}

	public double getMaximaEnergiaResultado(){
		return this.implementacion_simplex.maximaEnergiaResultado();
	}
	public boolean consumioSuMaximaEnergia(){
		return this.implementacion_simplex.consumioSuMaximaEnergia();
	}
		
	public static void main(String[] args) {
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
		Optimizador simplex = new Optimizador();
		simplex.setConsumoMaximoDeEnergia(450000);
		simplex.cargarDispositivos(lcd,lavaropas,ventilador);
		simplex.maximizar();
		ArrayList<ResultadoHora> resultados=simplex.getHorasDeCadaDispositivo();
		
			
		for (int i = 0; i < simplex.cantidadDeDispositivos(); i++) {
			System.out.print(resultados.get(i).getDispositivo()+" = ");
			System.out.println(resultados.get(i).getHorasQuePuedeConsumir());	
		}
	}
}
