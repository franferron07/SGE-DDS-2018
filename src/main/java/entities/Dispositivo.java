package entities;

public abstract class Dispositivo {
	private String nombre;
	private float kwHora;
	
	public abstract float consumoKmHora(long horas);
	
	public abstract boolean esInteligente();
	
	public abstract boolean estaEncendido();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getKwHora() {
		return kwHora;
	}

	public void setKwHora(float kwHora) {
		this.kwHora = kwHora;
	}
	
}