package reglasYActuadores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="condicion")
public class CondicionRegla {

	@Id
	@GeneratedValue
	private int id;
	@Column(name="comparador")
	private String comparador;
	@Column(name="valorComparable")
	private float valorComparable;
	
	
	public CondicionRegla( String comparador , Float valorComparable ){
		this.comparador = comparador;
		this.valorComparable = valorComparable;
	}
	
	//chequeo si se cumple o no la medicion segun el comparador pasado. siempre el valorComparable es el segundo miembro
	public boolean cumpleMedicion(double valor) {
		
		if( this.comparador == ">" ){
			
			if( valor > this.valorComparable ) return true;
			return false;
		}
		if( this.comparador == "<" ){
			
			if( valor < this.valorComparable )return true;
			return false;
			
		}
		if( this.comparador == "==" ){
			
			if( valor == this.valorComparable ) return true;
			return false;
		}
		if( this.comparador == "!=" ){
			
			if( valor != this.valorComparable )return true;
			return false;
		}
		if( this.comparador == "<=" ){
			
			if( valor <= this.valorComparable )return true;
			return false;
		}
		if( this.comparador == ">=" ){
			
			if( valor >= this.valorComparable)return true;
			return false;
		}
		
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
