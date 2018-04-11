package ar.frba.dds.grupo3.entities;

public class Dispositivo {

	private String nombre;
	private int kwHora;
	private boolean encendido;

	public Dispositivo() {

	}

	public boolean getEncendido(){
		return encendido;
	}

	public String getNombre() {
		return nombre;
	}

	public int getKwHora() {
		return kwHora;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setKwHora(int kwHora) {
		this.kwHora = kwHora;
	}

}