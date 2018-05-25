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

	//aca debo discriminar los inteligentes que tienen el estandar distinto de null
	public int cantidadDispositivos(){
		return dispositivos.size();
	}

	public boolean estaEncendido(DispositivoInteligente dispositivo){
		return dispositivo.estaEncendido();
	}

	//conviero dispositivo estandar a inteligente y agrego inteligente a la lista. (consultar si saco el estandar o lo dejo)
	public void convertirEStandarInteligente( DispositivoEstandar dispositivoEstandar) {
		
		ModoApagado modoApagado=new ModoApagado();
		DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(modoApagado);
		
		//agrego disposiutivo estandar en inteligente y lo agrego a la lista. 
		dispositivoInteligente.setEstandar(dispositivoEstandar);
		agregarDispositivo(dispositivoInteligente);
		
		puntaje+=10;

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

	
	//getters y setters
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