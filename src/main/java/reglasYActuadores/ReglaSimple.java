package reglasYActuadores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.eclipse.paho.client.mqttv3.MqttException;

import dispositivos.DispositivoInteligente;
import entities.Sensor;
import repositorios.RepositorioRegla;
import repositorios.RepositorioSensor;

@Entity
@DiscriminatorValue("reglaSimple")
public class ReglaSimple extends Regla {

	@OneToMany(mappedBy="regla_simple" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<CondicionRegla> condiciones;
	
	
	public ReglaSimple(){
		
	}
	
	public ReglaSimple(String nombre) {
		super(nombre);
		this.condiciones = new ArrayList<CondicionRegla>();

	}

	
	@Override
	public boolean cumpleCondiciones(Double valor) {

		return this.condiciones.stream().allMatch( c -> c.cumpleMedicion(valor) );
	}
	
	
	
	//ejecuto actuadores de la regla.
	@Override
	public void ejecutarActuadores( DispositivoInteligente d ) {		
		
		getActuadores().stream().forEach(a->{
			try {
				a.ejecutarAccion(d);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
				System.out.println(e.toString());
			}
		});		
	}
	
	
	public void agregarCondiciones( List<CondicionRegla> condiciones ){
		
		this.condiciones.addAll(condiciones);
		
		//seteo la regla a cada elemento agregado
		Iterator<CondicionRegla> iterator = condiciones.iterator();
		while (iterator.hasNext()) {
			CondicionRegla cond = iterator.next();
			cond.setRegla_simple(this);			
		}
		
	}
	
	
	
	
	//getters y setters
	public List<CondicionRegla> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionRegla> condiciones) {
		this.condiciones = condiciones;
	}


	
	

	

	

}
