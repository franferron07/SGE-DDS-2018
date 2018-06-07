package entities;

import java.util.ArrayList;
import java.util.List;

public class ReglaCompuesta extends Regla {

	List<Regla> reglas;
	
	
	public ReglaCompuesta(String nombre) {
		super(nombre);
		this.reglas=new ArrayList<Regla>();
	}

	@Override
	public void evaluarMedicion(Sensor sensor) {
		// TODO Auto-generated method stub
		
	}

	
	//getters y setters
	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}
	
	

}
