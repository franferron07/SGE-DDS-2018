package entities;

import java.util.ArrayList;
import java.util.List;

public class ReglaSimple extends Regla {

	
	private List<CondicionRegla> condiciones;
	
	
	public ReglaSimple(String nombre) {
		super(nombre);
		this.setCondiciones(new ArrayList<CondicionRegla>());
	}

	@Override
	public void evaluarMedicion(Sensor sensor) {
		// TODO Auto-generated method stub
		
	}

	
	
	//getters y setters
	public List<CondicionRegla> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionRegla> condiciones) {
		this.condiciones = condiciones;
	}

}
