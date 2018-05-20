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
	private List<DispositivoInteligente> dispositivosInteligentes;
	private int puntaje;
	
	public Cliente(List<DispositivoInteligente> dispositivosInteligentes) {
		 this.dispositivosInteligentes = dispositivosInteligentes; 
	}

	private Stream<DispositivoInteligente> filtrarDispositivosInteligentes(){
		return this.dispositivosInteligentes.stream().filter(d -> d.esInteligente() );
	}
	
	public int cantidadDispositivosEncendidos(){
		Stream<DispositivoInteligente> inteligentes = filtrarDispositivosInteligentes(); 
		return inteligentes.filter(disp -> estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivosApagados(){
		Stream<DispositivoInteligente> inteligentes = filtrarDispositivosInteligentes(); 
		return inteligentes.filter(disp -> !estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivos(){
		return dispositivosInteligentes.size();
	}

	public boolean estaEncendido(Dispositivo dispositivo){
		return dispositivo.estaEncendido();
	}

	public float consumoPorDia(DispositivoEstandar dispositivoEstandar,long horas) {
		return dispositivoEstandar.consumoKmHora(horas);
	}

	public Dispositivo convertirStandarInteligente() {
		ModoApagado modoApagado=new ModoApagado();
		DispositivoInteligente dispositivoInteligente=new DispositivoInteligente(modoApagado);
		DispositivoEstandar dispositivoEstandar =new DispositivoEstandar();
		dispositivoEstandar.setAdaptador(dispositivoInteligente);
		puntaje+=10;
		return dispositivoEstandar;
	}
	public void quitarDispositivo(Dispositivo dispositivo) {
		this.dispositivosInteligentes.remove(dispositivo);
	}
	
	public void agregarDispositivo(DispositivoInteligente dispositivo) {
		if (dispositivosInteligentes.add(dispositivo))
		this.setPuntaje(puntaje+15);
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

	public List<DispositivoInteligente> getDispositivosInteligentes() {
		return dispositivosInteligentes;
	}

	public void setDispositivosInteligentes(List<DispositivoInteligente> dispositivosInteligentes) {
		this.dispositivosInteligentes = dispositivosInteligentes;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	}