package ar.frba.dds.grupo3.entities;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente extends Usuario {

	private String tipoDocumento;
	private int numeroDocumento;
	private LocalTime fechaAltaServicio;
	private int telefonoContacto;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;

	public Cliente() {
	}

	public int cantidadDispositivosEncendidos(){
		return dispositivos.stream().filter(disp -> estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivosApagados(){
		return dispositivos.stream().filter(disp -> !estaEncendido(disp)).collect(Collectors.toList()).size();
	}

	public int cantidadDispositivos(){
		return dispositivos.size();
	}

	public boolean estaEncendido(Dispositivo dispositivo){
		return dispositivo.getEncendido();
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

	public Categoria getCategoria() {
		return categoria;
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

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}
	
	public void quitarDispositivo(Dispositivo dispositivo) {
		this.dispositivos.remove(dispositivo);
	}
	
	public void agregarDispositivo(Dispositivo dispositivo) {
		this.dispositivos.add(dispositivo);
	}
}