package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReglaSimple extends Regla {

	
	private List<CondicionRegla> condiciones;
	
	
	public ReglaSimple(String nombre) {
		super(nombre);
		this.condiciones = new ArrayList<CondicionRegla>();
	}

	//ejecuto actuadores de la regla.
	@Override
	public void ejecutarActuadores() {		
		
		this.actuadores.stream().forEach(a->a.ejecutarAccion());		
	}
	
	//filtra los modos que entren en el intervalo pedido
	@Override
	public boolean cumpleCondiciones(Float valor) {
		
		return this.condiciones.stream().allMatch( c -> c.cumpleMedicion(valor) );
	}

	
	
	//getters y setters
	public List<CondicionRegla> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionRegla> condiciones) {
		this.condiciones = condiciones;
	}

	

}
