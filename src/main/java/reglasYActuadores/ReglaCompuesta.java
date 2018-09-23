package reglasYActuadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("reglaCompuesta")
public class ReglaCompuesta extends Regla {

	
	//son las condiciones de las reglas compuestas
	@OneToMany(mappedBy="id" , cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
	private List<Regla> reglas;
	
	
	public ReglaCompuesta(String nombre) {
		super(nombre);
		this.reglas=new ArrayList<Regla>();
	}

	//filtra los modos que entren en el intervalo pedido
	@Override
	public boolean cumpleCondiciones(double valor) {
		
		return this.reglas.stream().allMatch( c -> c.cumpleCondiciones(valor) );
	}
		
	@Override
	//ejecuto cada actuador de cada regla que se cumple + los actuadores propios
	public void ejecutarActuadores() {
		//ejecuto actuadores de las reglas
		this.reglas.stream().forEach(r->r.ejecutarActuadores());
		//ejecuto actuadores propios
		this.actuadores.stream().forEach(a->a.ejecutarAccion());
		
	}
	
	//getters y setters
	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}

		

}
