package entities;

public abstract class Dispositivo {
	
	private String nombre;
	protected float kwHora;
	
	//seguramente esto sea un date time, desde y hasta. Me da el consumo en un determinado periodo de tiempo. 
	public abstract float consumoPeriodo(int dias);
	
	public abstract boolean esInteligente();

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