package geoposicionamiento;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import geoposicionamiento.*;

@Entity
@Table(name="ubicable")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Ubicable {
	
	@Id
	@GeneratedValue
	public int id;
	
	@OneToMany(mappedBy="ubicable",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	public List<Coordenada> coordenadas;
	
	
	public Ubicable() {
		this.coordenadas=new ArrayList<Coordenada>();
		
	}
	public int getId() {
		return this.id;
	}


	public Coordenada getUbicacion() {
		return this.coordenadas.iterator().next();
	}
	
	public Path2D getPoligono(){
		FactoryPath2D f=new FactoryPath2D();
		Path2D p = f.factoryMethod(this.coordenadas);
		return p;	
		
	}

	public Path2D getPath2D(){
		FactoryPath2D f=new FactoryPath2D();
		Path2D p = f.factoryMethod(this.coordenadas);
		return p;	
		
	}
	
	public void addCoordenadas(double x, double y) {
		this.coordenadas.add( new Coordenada(x,y));
	}
	
	public void setUbicacion(Coordenada coordenadas) {
		if(!this.coordenadas.isEmpty())
		{
			this.coordenadas=new ArrayList<Coordenada>();
		}
		this.coordenadas.add(coordenadas);

	}
	
	public void addCoordenadas(Coordenada coordenadas) {
		this.coordenadas.add(coordenadas);
	}
	
	public void removeCoordenadas(Coordenada coordenadas) {
		this.coordenadas.remove(coordenadas);
	}
	
	public double distanciaCoordenadas(Coordenada xy, Coordenada yz) {    
        final double radioTierra = 6371;//en kilómetros
		double dLat = Math.toRadians(yz.getLatitud() - xy.getLatitud());  
        double dLng = Math.toRadians(yz.getLongitud()- xy.getLongitud());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(xy.getLatitud())) * Math.cos(Math.toRadians(yz.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2; 
        
        return distancia;  
    }  
	
	public Ubicable getMasCercano(Coordenada xy, List<? extends Ubicable> ubicables ) {
		Iterator<? extends Ubicable> iterator = ubicables.iterator();
		Ubicable masCercano = null;
		Ubicable ubicable = null;
		double distaciaMenor = Double.MAX_VALUE;
		
		
		while(iterator.hasNext()) {
			ubicable = iterator.next();
			double distanciaEvaluada = this.distanciaCoordenadas(xy, ubicable.getUbicacion());
			if(distanciaEvaluada < distaciaMenor) {				
				distaciaMenor = distanciaEvaluada;
				masCercano = ubicable;
			}
		}
		return masCercano;
		
	}
}

	
	
}
