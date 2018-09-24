package reglasYActuadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue("reglaSimple")
public class ReglaSimple extends Regla {

	@OneToMany(mappedBy="id" , cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
	private List<CondicionRegla> condiciones;
	
	
	public ReglaSimple(String nombre) {
		super(nombre);
		this.condiciones = new ArrayList<CondicionRegla>();
	}

	
	@Override
	public boolean cumpleCondiciones(double valor) {
		
		return this.condiciones.stream().allMatch( c -> c.cumpleMedicion(valor) );
	}
	
	
	
	//ejecuto actuadores de la regla.
	@Override
	public void ejecutarActuadores( DispositivoInteligente d ) {		
		
		this.actuadores.stream().forEach(a->a.ejecutarAccion(d));		
	}
	
	//getters y setters
	public List<CondicionRegla> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionRegla> condiciones) {
		this.condiciones = condiciones;
	}

	

	

}
