package usuarios;

public class Categoria {

	private String nombre;
	private float consumoMinimo;
	private float consumoMaximo;
	private String concepto;//Valores que puede tener Cargo fijo,Cargo variable u otro si abria en el futuro
	private String unidad;
	private float costoNormal;

	public Categoria() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(float consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public float getConsumoMaximo() {
		return consumoMaximo;
	}

	public void setConsumoMaximo(float consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public float getCostoNormal() {
		return costoNormal;
	}

	public void setCostoNormal(float costoNormal) {
		this.costoNormal = costoNormal;
	}

	
}