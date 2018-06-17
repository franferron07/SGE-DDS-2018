package entities;

public class CondicionRegla {

	
	private String comparador;
	private float valorComparable;
	
	
	public CondicionRegla( String comparador , Float valorComparable ){
		this.comparador = comparador;
		this.valorComparable = valorComparable;
	}
	
	public boolean cumpleMedicion(Float valor) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	//getters y setters
	public String getComparador() {
		return comparador;
	}

	public void setComparador(String comparador) {
		this.comparador = comparador;
	}

	public float getValorComparable() {
		return valorComparable;
	}

	public void setValorComparable(float valorComparable) {
		this.valorComparable = valorComparable;
	}
	
	

	
}
