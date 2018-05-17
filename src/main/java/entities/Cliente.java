package entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cliente extends Usuario {

	private String tipoDocumento;
	private int numeroDocumento;
	private LocalTime fechaAltaServicio;
	private int telefonoContacto;
	private Categoria categoria;
	private List<Dispositivo> dispositivos; //inicializo la lista en nul
	private int puntaje;
	
	//constructor vacio para jackson
	public Cliente() {
		 dispositivos = new ArrayList<Dispositivo>() ; 
	}

	
	@SuppressWarnings("unused")
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
		return dispositivoEstandar.consumoDia(horas);
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public LocalTime getFechaAltaServicio() {
		return fechaAltaServicio;
	}

	public int getTelefonoContacto() {
		return telefonoContacto;
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setFechaAltaServicio(LocalTime fechaAltaServicio) {
		this.fechaAltaServicio = fechaAltaServicio;
	}

	public void setTelefonoContacto(int telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}
	
	public void quitarDispositivo(Dispositivo dispositivo) {
		this.dispositivos.remove(dispositivo);
	}
	
	public void agregarDispositivo(Dispositivo dispositivo) {
		if (dispositivos.add(dispositivo))
			this.setPuntaje(puntaje+15);
	}

	public Dispositivo convertirStandarInteligente() {
		DispositivoInteligente dispositivoInteligente=new DispositivoInteligente();
		DispositivoEstandar dispositivoEstandar =new DispositivoEstandar();
		dispositivoEstandar.setAdaptador(dispositivoInteligente);
		puntaje+=10;
		return dispositivoEstandar;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
}