package entities;

import java.util.ArrayList;
import java.util.List;

public class ReglaCompuesta extends Regla {

	//son las condiciones de las reglas compuestas
	List<Regla> reglas;
	
	
	public ReglaCompuesta(String nombre) {
		super(nombre);
		this.reglas=new ArrayList<Regla>();
	}

	
	@Override
	//ejecuto cada actuador de cada regla que se cumple + los actuadores propios
	public void ejecutarActuadores() {
		
		this.reglas.ejecutarActuadores();
	}
	
	//filtra los modos que entren en el intervalo pedido
	@Override
	public boolean cumpleCondiciones(Float valor) {
		
		return this.reglas.stream().allMatch( c -> c.cumpleCondiciones(valor) );
	}
	
	//getters y setters
	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}

		

}
