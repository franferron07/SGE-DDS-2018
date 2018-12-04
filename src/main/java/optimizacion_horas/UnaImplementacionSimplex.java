package optimizacion_horas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.eclipse.paho.client.mqttv3.MqttException;

import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import usuarios.Cliente;

public class UnaImplementacionSimplex implements Implementador{
	public  ArrayList<DispositivoUsuario> dispositivos=new ArrayList<DispositivoUsuario>();
	public  Cliente clienteActual=null;
	public double[]  coeficientesDeFuncionObjetivo;//ejemplo:  [a,b,c] de MAX f=aX0+bX1+cX2=Z
	public double[][]  matrizIdentidadDeSimplex;
	public double[] matrizB;//del tipo AX=B , en el que X es el vector de horas
	public ArrayList<ResultadoHora> resultados=new ArrayList<ResultadoHora>();
	private AlgoritmoSimplex algoritmo=null;
	public double maximaEnergiaResultado=0;//Z
	private double consumoMaximoDeEnergia=0;//setteable , energia maxima de un cliente segun su categoria , criterio de eficiencia


	@Override
	public void cargarDispositivosEsenciales(Cliente cliente) {
		DispositivoUsuario[] dispositivos__ = (DispositivoUsuario[]) cliente.getDispositivos().stream().filter(dispositivo-> dispositivo.esEsencial()).toArray();
		for (int i = 0; i < dispositivos__.length; i++) {
			this.dispositivos.add(i, dispositivos__[i]);
		}
	}
	public void cargarDispositivos(ArrayList<DispositivoUsuario> _dispositivoUsuarios_){
		this.dispositivos.addAll(_dispositivoUsuarios_);
	}
	public void cargarDispositivos(Cliente cliente){
		this.dispositivos.addAll(cliente.getDispositivos());
	}

	@Override
	public void maximizar() {
		this.algoritmo = new AlgoritmoSimplex(GoalType.MAXIMIZE, true); //true de variables positivas
		this.resolverInecuacion();
	}
	public void resolverInecuacion() {
		this.plantearMatricesDeInecuacion();
		this.calcularHoras();
	}
	public void calcularHoras() {
		PointValuePair solucion =this.algoritmo.resolver();
		this.maximaEnergiaResultado=solucion.getValue();
		for (int i = 0; i < this.cantidadDeDispositivos(); i++) {
			double horasTope=solucion.getPoint()[i];

			//ResultadoHora par = new ResultadoHora(this.dispositivos.get(i).getIdentificacion(),horasTope);

			//entrega final, para que el ResultadoHora tenga dispositivo para saber el consumo actual

			//ResultadoHora par = new ResultadoHora(this.dispositivos.get(i).getIdentificacion(),horasTope);
			double horasDeUso = this.dispositivos.get(i).horasDeUso(LocalDateTime.now().withDayOfMonth(1), LocalDateTime.now());
			ResultadoHora par = new ResultadoHora(this.dispositivos.get(i).getIdentificacion(),horasTope-horasDeUso);
			par.setSePasoDeConsumo(horasTope<horasDeUso);

			this.resultados.add(par) ;
		}
	}
	private void plantearMatricesDeInecuacion() {
		this.crearFuncionEconomica(this.crearVectorDeUnos(this.cantidadDeDispositivos()));
		this.coeficientesDeFuncionObjetivo =this.obtenerCoeficientesDeFuncionObjetivo(this.dispositivos);
		this.matrizIdentidadDeSimplex= this.obtenerMatrizIdentidadDeSimplex(this.dispositivos);
		this.matrizB=this.obtenerMatrizBDeSimplex(this.dispositivos);
		this.agregarRestriccion(Relationship.LEQ, this.consumoMaximoDeEnergia,this.coeficientesDeFuncionObjetivo);
		for (int i = 0; i <2* this.cantidadDeDispositivos(); i++) {//esto crea la matriz A al AlgoritmoSimplex
			this.agregarRestriccion(Relationship.LEQ, (double) this.matrizB[i], this.matrizIdentidadDeSimplex[i]);
			i++;
			this.agregarRestriccion(Relationship.GEQ,(double) this.matrizB[i], this.matrizIdentidadDeSimplex[i]);
		}
	}
	public PointValuePair resolver() {
		return this.algoritmo.resolver();
		//return this.maximaEnergiaResultado=this.algoritmo.resolver();
	}
	public void crearFuncionEconomica(double ... crearVectorDeUnos_) {
		this.algoritmo.crearFuncionEconomica(crearVectorDeUnos_);
	}
	public void agregarRestriccion(Relationship leq_, double consumoMaximoDeEnergia2_, double ... coeficientesDeFuncionObjetivo2_) {
		this.algoritmo.agregarRestriccion(leq_, consumoMaximoDeEnergia2_, coeficientesDeFuncionObjetivo2_);
	}
	public void setConsumoMaximoDeEnergia(double consumoMaximoDeEnergia) {
		this.consumoMaximoDeEnergia = consumoMaximoDeEnergia;
	}
	public int cantidadDeDispositivos() {
		return this.dispositivos.size();
	}
	public ArrayList<ResultadoHora> getHorasDeCadaDispositivo() {
		// TODO Auto-generated method stub
		return this.resultados;
	}

	@Override
	public void cargarDispositivos(DispositivoUsuario... _dispositivoUsuarios_) {
		for (int i = 0; i < _dispositivoUsuarios_.length ; i++) {
			if (_dispositivoUsuarios_[i].esEsencial()) {
				this.dispositivos.add(_dispositivoUsuarios_[i]);
			}
		}
	}
	@Override	//metod que ejecutara el hilo para tomar decisiones sobre los dispositivos del cliente
	public void analisarResultados( LocalDateTime desde , LocalDateTime hasta ){
		int i=0; //iterador
		DispositivoUsuario dispositivo;
		Iterator<ResultadoHora> it = resultados.iterator();
		// recorro lista de transformadores buscando la distancia minima.
		while(it.hasNext()){
			ResultadoHora resultado =it.next();
			dispositivo = dispositivos.get(i);
			//si ya se uso mas de lo que deberia deberia accionar la accion del cliente
			if( dispositivo.horasDeUso(desde, hasta) >  resultado.horasQuePuedeConsumir  ){

				//si el dispositivo es inteligente ejecuto la accion
				if( dispositivo.esInteligente() ){

					DispositivoInteligente di = (DispositivoInteligente) dispositivo;
					try {
						di.ejecutarAccionAutomatica();
					} catch (MqttException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			i++;
		}

	}

	@Override
	public ArrayList<ResultadoHora> resultados() {
		return this.resultados;
	}

	//entrega 4 ----------------
	public double maximaEnergiaResultado(){
		double energia_consumida=0;
		for(int i=0;i<this.cantidadDeDispositivos();i++){
			energia_consumida+=this.dispositivos.get(i).getConsumoKwHora()*this.resultados.get(i).getHorasQuePuedeConsumir();
		}
		return energia_consumida;
	}
	public boolean consumioSuMaximaEnergia(){
		return this.consumoMaximoDeEnergia<this.maximaEnergiaResultado;//supero el consumo maximo
	}
}
