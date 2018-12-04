package reglasYActuadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.paho.client.mqttv3.MqttException;

import dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue("reglaCompuesta")
public class ReglaCompuesta extends Regla {

	
	//son las condiciones de las reglas compuestas
	@OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
	@JoinColumn( name="reglapadre_id" , referencedColumnName="id" )
	private List<Regla> reglas;
	
	public ReglaCompuesta(){
		
	}
	
	public ReglaCompuesta(String nombre) {
		super(nombre);
		this.reglas=new ArrayList<Regla>();
	}

	//filtra los modos que entren en el intervalo pedido
	@Override
	public boolean cumpleCondiciones(Double valor) {
		
		return this.reglas.stream().allMatch( c -> c.cumpleCondiciones(valor) );
	}
		
	@Override
	//ejecuto cada actuador de cada regla que se cumple + los actuadores propios
	public void ejecutarActuadores(DispositivoInteligente d) {
		
		//ejecuto actuadores de las reglas
		this.reglas.stream().forEach(r->r.ejecutarAccionDispositivosActuadores());
		
		//ejecuto actuadores propios
		getActuadores().stream().forEach(a->{
			try {
				a.ejecutarAccion(d);
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	

	
	//getters y setters
	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}

	public void addRegla(Regla regla) {
		this.reglas.add(regla);
	}
	

		

}
