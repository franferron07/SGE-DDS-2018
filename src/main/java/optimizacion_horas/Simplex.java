package optimizacion_horas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dispositivos.DispositivoUsuario;
import usuarios.Cliente;

public class Simplex implements SimplexInterface,SimplexMatematica{
	public ArrayList<DispositivoUsuario> dispositivos=new ArrayList<DispositivoUsuario>();
	private double consumoMaximoDeEnergia=0;
	public Cliente clienteActual=null;
	public AlgoritmoSimplex algoritmo=null;
	
	public double[]  coeficientesDeFuncionObjetivo;//ejemplo:  [a,b,c] de MAX f=aX0+bX1+cX2=Z
	public double[][]  matrizIdentidadDeSimplex;
	public double[] matrizB;//del tipo AX=B , en el que X es el vector de horas 
	public ArrayList<ResultadoHora> resultados=new ArrayList<ResultadoHora>();
	public double maximaEnergiaResultado;//Z
	
//	public double consumoFijoDeEnergia;
	
	@Override
	public void maximizar() {
		this.algoritmo = new AlgoritmoSimplex(GoalType.MAXIMIZE, true); //true de variables positivas
		this.resolverInecuacion();
	}
	@Override
	public void minimizar() {
		this.algoritmo=new AlgoritmoSimplex(GoalType.MINIMIZE, true);//true de variables positivas
		this.resolverInecuacion();
	}

	public void resolverInecuacion() {	
		this.plantearMatricesDeInecuacion();
		this.calcularHoras();
	}
	
	public void plantearMatricesDeInecuacion() {

		this.crearFuncionEconomica(this.crearVectorDeUnos(this.cantidadDeDispositivos()));
		this.coeficientesDeFuncionObjetivo =this.obtenerCoeficientesDeFuncionObjetivo(this.dispositivos);
		this.matrizIdentidadDeSimplex= this.obtenerMatrizIdentidadDeSimplex(this.dispositivos);
		this.matrizB=this.obtenerMatrizBDeSimplex(this.dispositivos);
		this.agregarRestriccion(Relationship.LEQ, this.consumoMaximoDeEnergia(),this.coeficientesDeFuncionObjetivo);

		for (int i = 0; i <2* this.cantidadDeDispositivos(); i++) {//esto crea la matriz A al AlgoritmoSimplex
				this.agregarRestriccion(Relationship.LEQ, (double) this.matrizB[i], this.matrizIdentidadDeSimplex[i]);
				i++;
				this.agregarRestriccion(Relationship.GEQ,(double) this.matrizB[i], this.matrizIdentidadDeSimplex[i]);
		}
	}
	private void crearFuncionEconomica(double ... crearVectorDeUnos_) {
		this.algoritmo.crearFuncionEconomica(crearVectorDeUnos_);
	}
	private void calcularHoras() {
		 PointValuePair solucion =this.algoritmo.resolver(); 
		 this.maximaEnergiaResultado=solucion.getValue();
		 for (int i = 0; i < this.cantidadDeDispositivos(); i++) {
			 double xi=solucion.getPoint()[i];
//			 System.out.println("X"+i+" "+xi);
			 ResultadoHora par = new ResultadoHora(this.dispositivos.get(i).getIdentificacion(),xi);
			 this.resultados.add(par);
		}
	}
	
	private double consumoMaximoDeEnergia() {
		return this.consumoMaximoDeEnergia;
	}


	private void agregarRestriccion(Relationship leq_, double consumoMaximoDeEnergia2_, double ... coeficientesDeFuncionObjetivo2_) {
		this.algoritmo.agregarRestriccion(leq_, consumoMaximoDeEnergia2_, coeficientesDeFuncionObjetivo2_);	
	}
	@Override
	public void cargarDispositivos(DispositivoUsuario... _dispositivoUsuarios_) {
		for (int i = 0; i < _dispositivoUsuarios_.length ; i++) {
			if (_dispositivoUsuarios_[i].esEsencial()) {
				this.dispositivos.add(_dispositivoUsuarios_[i]);
			}
		}
	}
		
	@Override
	public void cargarDispositivosEsenciales(Cliente cliente) {
		DispositivoUsuario[] dispositivos__ = (DispositivoUsuario[]) cliente.getDispositivos().stream().filter(dispositivo-> dispositivo.esEsencial()).toArray();
		for (int i = 0; i < dispositivos__.length; i++) {
			dispositivos.add(i, dispositivos__[i]);
		}
	}
	public int cantidadDeDispositivos() {
		return this.dispositivos.size();
	}
	@Override
	public ArrayList<ResultadoHora> getHorasDeCadaDispositivo() {
		// TODO Auto-generated method stub
		return this.resultados;
	}
	
	//ACCESORS
	public ArrayList<DispositivoUsuario> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(ArrayList<DispositivoUsuario> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public double getConsumoMaximoDeEnergia() {
		return consumoMaximoDeEnergia;
	}

	public void setConsumoMaximoDeEnergia(double consumoMaximoDeEnergia) {
		this.consumoMaximoDeEnergia = consumoMaximoDeEnergia;
	}

	public Cliente getClienteActual() {
		return clienteActual;
	}

	public void setClienteActual(Cliente clienteActual) {
		this.clienteActual = clienteActual;
	}


	public double[] getCoeficientesDeFuncionObjetivo() {
		return coeficientesDeFuncionObjetivo;
	}

	public void setCoeficientesDeFuncionObjetivo(double[] coeficientesPrincipales) {
		this.coeficientesDeFuncionObjetivo = coeficientesPrincipales;
	}

	public double[][] getMatrizIdentidadSimplex() {
		return matrizIdentidadDeSimplex;
	}

	public void setMatrizIdentidadSimplex(double[][] matrizDeCoeficientes) {
		this.matrizIdentidadDeSimplex = matrizDeCoeficientes;
	}

	public double[] getMatrizB() {
		return matrizB;
	}

	public void setMatrizB(double[] matrizB) {
		this.matrizB = matrizB;
	}


	public double getMaximaEnergia() {
		return maximaEnergiaResultado;
	}

	public void setMaximaEnergia(double maximaEnergia) {
		this.maximaEnergiaResultado = maximaEnergia;
	}

	
	
	public static void main(String[] args) {
		/*
		 * probando ejemplo de la catedra 
		 * Funci�n econ�mica:
				f(x0,x1, x2) = x2+ x1+ x0
				En donde:
					- x0 -> Es un televisor LCD de 40� cuyo consumo kWh es 0.18
					- x1 -> Es un lavarropas Autom�tico de 5 kg con calentamiento de agua cuyo consumo kwH es 0.875
					- x2 -> Es un ventilador de techo cuyo consumo kWh es 0.06
			- Enfoque (GoalType):
				Maximizaci�n
			- Restricciones:
				1) 0.06x2 + 0.875x1 + 0.18x0 <= 440640
				2) x0 >= 90
				3) x0 <= 370
				4) x1 >= 6
				5) x1 <= 30
				6) x2 >= 120
				7) x2 <= 360
				8) Todas las variables son no negativas (NonNegativeConstraint(true))
			NOTA: 
				- >= : Relationship.GEQ
				- <= : Relationship.LEQ
				- =  : Relationship.EQ
		 
		SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);
		simplexFacade.crearFuncionEconomica(1,1,1);
		simplexFacade.agregarRestriccion(Relationship.LEQ, 440640, 	0.06, 	0.875, 	0.18);
		 * 
		 * */ 
		
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
		};
		Simplex simplex = new Simplex();
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
