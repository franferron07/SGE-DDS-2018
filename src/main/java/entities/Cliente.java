package entities;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cliente extends Usuario {
	private static final long serialVersionUID = 1L;
	private String tipoDocumento;
	private int numeroDocumento;
	private LocalTime fechaAltaServicio;
	private int telefonoContacto;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;
	private int puntaje;
	
	
	public Cliente(List<Dispositivo> dispositivos) {
		 this.dispositivos = dispositivos; 
	}

	private Stream<Dispositivo> filtrarDispositivosInteligentes(){
		return this.dispositivos.stream().filter(d -> d.esInteligente() );
	}
	
	public int cantidadDispositivosEncendidos(){
		Stream<Dispositivo> inteligentes = filtrarDispositivosInteligentes(); 
		return inteligentes.filter(disp -> estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivosApagados(){
		Stream<Dispositivo> inteligentes = filtrarDispositivosInteligentes(); 
		return inteligentes.filter(disp -> !estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivos(){
		return dispositivos.size();
	}

	public boolean estaEncendido(Dispositivo dispositivo){
		return dispositivo.estaEncendido();
	}

	public float consumoPorDia(DispositivoEstandar dispositivoEstandar,long horas) {
		return dispositivoEstandar.consumoKmHora(horas);
	}

	public Dispositivo convertirStandarInteligente( DispositivoEstandar dispositivoEstandar) {
		ModoApagado modoApagado=new ModoApagado();
		DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(modoApagado);
		dispositivoEstandar.setAdaptador(dispositivoInteligente);
		puntaje+=10;
		return dispositivoEstandar;
	}
	
	public void quitarDispositivo(Dispositivo dispositivo) {
		this.dispositivos.remove(dispositivo);
	}
	
	public void agregarDispositivo(Dispositivo dispositivo) {
		
		if( dispositivo.esInteligente() == true ){
			this.setPuntaje(puntaje+15);
		}
		
		dispositivos.add(dispositivo);
		
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalTime getFechaAltaServicio() {
		return fechaAltaServicio;
	}

	public void setFechaAltaServicio(LocalTime fechaAltaServicio) {
		this.fechaAltaServicio = fechaAltaServicio;
	}

	public int getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(int telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	}