package entities;

import java.util.ArrayList;
import java.util.List;

import composite.Regla;
import enums.TipoMagnitud;
import observers.Observable;

public class Sensor  extends Observable {

	//si mide temparatura , movimiento, humedad,intensidad de luz
	private TipoMagnitud magnitud ;
	private List<Regla> reglas;
	   private int state;
	
	public Sensor(){
		reglas= new ArrayList<Regla>();
	}
	

    public int getState() {
        return state;
    }
//Aqui esta el patron Observer
    public void setState(int state) {
        this.state = state;
        this.notifyObservers();
    }
    
	//se realiza de alguna manera de forma externa y se reciben datos de esa medicion
	public void realizarMedicion(){
		/*procedimiento aun no definido*/
		avisarMedicion();
	}

	//metodo que avisa a sus observadores(reglas) que realizo la medicion
	private void avisarMedicion() {
		
		this.reglas.forEach( r -> r.evaluarMedicion(this));
		
	}

	
	public void agregarRegla(Regla unaRegla){
		this.reglas.add(unaRegla);
	}
	
	public int cantidadReglas(){
		return reglas.size();
	}
	
	//getters y setters
	public TipoMagnitud getTipoMagnitud() {
		return magnitud;
	}

	public void setTipoMagnitud(TipoMagnitud tipoMagnitud) {
		this.magnitud = tipoMagnitud;
	}

	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}


	
	
	
}
