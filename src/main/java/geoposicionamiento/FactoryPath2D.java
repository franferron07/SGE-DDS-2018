package geoposicionamiento;

import java.awt.geom.Path2D;
import java.util.List;

public class FactoryPath2D {
	//Utilizamos Path ya que se pueden crear areas con puntos double
	private Path2D path;
	
	public FactoryPath2D() {
		
	}
	
	public Path2D factoryMethod(double[] x, double[] y, int n) {
		path = new Path2D.Double();
		path.moveTo(x[0],y[0]);
		for(int i = 1; i < n; ++i) {
			   path.lineTo(x[i], y[i]);
			}
		path.closePath();
		
		return path;
	}
	
	public Path2D factoryMethod(List<Double> X, List<Double> Y, int n) {
		double[] x = X.stream().mapToDouble(Double::doubleValue).toArray();
		double[] y = Y.stream().mapToDouble(Double::doubleValue).toArray();
		this.path = this.factoryMethod(x, y, n);
		return this.path;
	}
	
	public Path2D factoryMethod(List<Coordenada> xy) {
		double[] x = xy.stream().mapToDouble(Coordenada::getLatitud).toArray();
		double[] y = xy.stream().mapToDouble(Coordenada::getLongitud).toArray();
		this.path = this.factoryMethod(x, y, xy.size());
		return this.path;
	}
	
	
}
