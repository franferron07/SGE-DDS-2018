package dispositivos;

//dispositivo con datos genericos del dispositivo que tendrá el usuario.
public class DispositivoLista {
	
	private String nombre;
	private String descripcion;
	private int hsMensualMinimo;
	private int hsMensualMaximo;
	private float consumoKwHora;
	private boolean esBajoConsumo;
	private boolean esInteligente;
	private boolean esEsencial;
	
	//constructor
	public DispositivoLista(){
		
	}
	
	
	//getters and setters
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getHsMensualMinimo() {
		return hsMensualMinimo;
	}
	public void setHsMensualMinimo(int hsMensualMinimo) {
		this.hsMensualMinimo = hsMensualMinimo;
	}
	public int getHsMensualMaximo() {
		return hsMensualMaximo;
	}
	public void setHsMensualMaximo(int hsMensualMaximo) {
		this.hsMensualMaximo = hsMensualMaximo;
	}
	public float getConsumoKwHora() {
		return consumoKwHora;
	}
	public void setConsumoKwHora(float consumoKwHora) {
		this.consumoKwHora = consumoKwHora;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public boolean isEsBajoConsumo() {
		return esBajoConsumo;
	}
	public boolean isEsInteligente() {
		return esInteligente;
	}


	public boolean isEsEsencial() {
		return esEsencial;
	}


	public void setEsEsencial(boolean esEsencial) {
		this.esEsencial = esEsencial;
	}
	
	
	

}
