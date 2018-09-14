package usuarios;

import java.awt.Point;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import dispositivos.Modo;
import dispositivos.ModoApagado;
import optimizacion_horas.Optimizador;

public class Cliente extends Usuario {
	
	private String tipoDocumento;
	private String numeroDocumento;
	private Date fechaAltaServicio;
	private String telefonoContacto;
	private Categoria categoria;
	private List<DispositivoUsuario> dispositivos;
	private int puntaje;
	private Point coordenadas;
	
	private boolean accionadoAutomatico; //variable que utiliza el simplex para ejecutar automaticamente acciones

	//constructor 
	public Cliente(){
		dispositivos= new ArrayList<DispositivoUsuario>();
		
	}
	
	public Cliente(List<DispositivoUsuario> dispositivos) {
		 this.dispositivos = dispositivos; 
	}

	//filtro los dispositivos inteligentes
	private List<DispositivoInteligente> filtrarDispositivosInteligentes(){
		List<DispositivoInteligente> inteligentes ;
		inteligentes = (List<DispositivoInteligente>) this.dispositivos.stream().filter(d -> d.esInteligente()) ; 
		return inteligentes ;
	}
	
	public int cantidadDispositivosEncendidos(){
		List<DispositivoInteligente> inteligentes = filtrarDispositivosInteligentes(); 
		return inteligentes.stream().filter(disp -> estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivosApagados(){
		List<DispositivoInteligente> inteligentes = filtrarDispositivosInteligentes(); 
		return inteligentes.stream().filter(disp -> !estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivos(){
		return dispositivos.size();
	}

	public boolean estaEncendido(DispositivoInteligente dispositivo){
		return dispositivo.estaEncendido();
	}

	//conviero dispositivo estandar a inteligente y agrego inteligente a la lista. y saco el estandar de la lista
	public void convertirEStandarInteligente( DispositivoEstandar dispositivoEstandar) {
		
		ModoApagado modoApagado=new ModoApagado();
		DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(modoApagado , dispositivoEstandar.detalle);
		
		//agrego disposiutivo estandar en inteligente y lo agrego a la lista. 
		dispositivoInteligente.setEstandar(dispositivoEstandar);
		agregarDispositivo(dispositivoInteligente);
		
		quitarDispositivo(dispositivoEstandar);
		
		puntaje+=10;

	}
	
	//quita el adaptador de un dispositivo estandar , quita el inteligente de la lista y pone el estandar. 
	public void quitarAdaptadorEnDispositivoEstandar( DispositivoInteligente inteligente ){
		
		DispositivoEstandar estandar = inteligente.getEstandar();
		
		if( estandar != null ){
			inteligente.setEstandar(null);
			quitarDispositivo(inteligente);
			agregarDispositivo(estandar);
			
			puntaje= puntaje-10;
		}	
	}
	
	public void quitarDispositivo(DispositivoUsuario dispositivo) {
		this.dispositivos.remove(dispositivo);
	}
	
	public void agregarDispositivo(DispositivoUsuario dispositivo) {
		
		if( dispositivo.esInteligente() == true ){
			this.setPuntaje(puntaje+15);
		}
		
		dispositivos.add(dispositivo);
	}

	
	public double consumoEnUnPeriodo(LocalDateTime desde , LocalDateTime hasta){
		
		return dispositivos.stream().mapToDouble(d -> d.consumoPeriodo(desde, hasta)).sum(); 
		
	}
	
	
	//metodo para correr simplex
	public String recomendacionHogarEficiente(){
		
		Optimizador simplex = new Optimizador();
		simplex.cargarDispositivosEsenciales(this);
		
		return simplex.resultados().toString();
		
	}
	
	
	
	//getters y setters
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getFechaAltaServicio() {
		return fechaAltaServicio;
	}

	public void setFechaAltaServicio(Date fechaAltaServicio) {
		this.fechaAltaServicio = fechaAltaServicio;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<DispositivoUsuario> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<DispositivoUsuario> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Point getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}

	public boolean isAccionadoAutomatico() {
		return accionadoAutomatico;
	}

	public void setAccionadoAutomatico(boolean accionadoAutomatico) {
		this.accionadoAutomatico = accionadoAutomatico;
	}


	
}