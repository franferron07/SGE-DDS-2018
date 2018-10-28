
package usuarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Table;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import dispositivos.ModoApagado;
import geoposicionamiento.Transformador;
import optimizacion_horas.Optimizador;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Transient;


@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Usuario {
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="id")
	private Usuario usuario;
	
	@Column(name="tipoDocumento")
	private String tipoDocumento;
	@Column(name="numeroDocumento")
	private String numeroDocumento;
	@Column(name="telefonoContacto")
	private String telefonoContacto;
	@Column(name="fechaAltaServicio")
	private String fechaAltaServicio_s;

	@Transient
	private LocalDateTime fechaAltaServicio;
	
	@ManyToOne
	@JoinColumn(name="categoria_id" , referencedColumnName="id")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
	private Transformador transformador;
	
	@OneToMany(mappedBy="cliente", cascade = CascadeType.PERSIST , fetch = FetchType.LAZY )
	private List<DispositivoUsuario> dispositivos;
	
	@Column(name="puntaje")
	private int puntaje;
	
	
	@Column(name="accionadoAutomatico")
	private boolean accionadoAutomatico; //variable que utiliza el simplex para ejecutar automaticamente acciones

	//constructor 
	
	public Cliente(){
		super();
		dispositivos= new ArrayList<DispositivoUsuario>();
		fechaAltaServicio=LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		fechaAltaServicio_s= fechaAltaServicio.format(formatter);
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
	public void convertirEstandarInteligente( DispositivoEstandar dispositivoEstandar) {
		
		DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(dispositivoEstandar.detalle);
		dispositivoInteligente.setCliente(this);
		
		//agrego disposiutivo estandar en inteligente y lo agrego a la lista.
		dispositivoInteligente.convertirDispositivoEstandar( dispositivoEstandar );
		agregarDispositivo(dispositivoInteligente);
		quitarDispositivo(dispositivoEstandar);
		
		puntaje+=10;

	}
	
	//quita el adaptador de un dispositivo estandar , quita el inteligente de la lista y pone el estandar. 
	public void quitarAdaptadorEnDispositivoEstandar( DispositivoInteligente inteligente ){
		
		DispositivoEstandar estandar = inteligente.getEstandar();
		
		if( estandar != null ){
			inteligente.quitarAdaptador();
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
		dispositivo.setCliente(this);
	}

	
	public double consumoEnUnPeriodo(LocalDateTime desde , LocalDateTime hasta){
		
		return dispositivos.stream().mapToDouble(d -> d.consumoPeriodo(desde, hasta)).sum(); 
		
	}
	
	//setea la fecha del alta correctamente
	public void parsearFecha( String fecha_s ){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.fechaAltaServicio = LocalDateTime.parse(fecha_s, formatter);
	}
	
	
	//metodo para correr simplex
	public String recomendacionHogarEficiente(){
		
		Optimizador simplex = new Optimizador();
		simplex.cargarDispositivosEsenciales(this);
		
		return simplex.resultados().toString();
		
	}
	
	
	
	//getters y setters
	
	public String getFechaAltaServicio_s() {
		parsearFecha(this.fechaAltaServicio_s);
		return fechaAltaServicio_s;
	}

	public void setFechaAltaServicio_s(String fechaAltaServicio_s) {
		this.fechaAltaServicio_s = fechaAltaServicio_s;
	}
	
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

	public LocalDateTime getFechaAltaServicio() {
		return fechaAltaServicio;
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


	public boolean isAccionadoAutomatico() {
		return accionadoAutomatico;
	}

	public void setAccionadoAutomatico(boolean accionadoAutomatico) {
		this.accionadoAutomatico = accionadoAutomatico;
	}
	
}