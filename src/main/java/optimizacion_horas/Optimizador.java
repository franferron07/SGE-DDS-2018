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
	//metodoss de carga
	public void cargarDispositivos(DispositivoUsuario... _dispositivoUsuarios_) {
		this.implementacion_simplex.cargarDispositivos(_dispositivoUsuarios_);

	}
	public void cargarDispositivos(ArrayList<DispositivoUsuario> _dispositivoUsuarios_){
		this.implementacion_simplex.cargarDispositivos(_dispositivoUsuarios_);
	}
	public void cargarDispositivos(Cliente cliente){
		this.implementacion_simplex.cargarDispositivos(cliente);
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


}
