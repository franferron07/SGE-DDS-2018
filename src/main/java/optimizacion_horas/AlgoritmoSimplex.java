
package optimizacion_horas;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class AlgoritmoSimplex  {
	private SimplexSolver simplex;
	private LinearObjectiveFunction funcionEconomica;
	private Collection<LinearConstraint> restricciones;
	private GoalType objetivo;
	private boolean variablesPositivas;
	
	public AlgoritmoSimplex(GoalType objetivo, boolean variablesPositivas) {
		this.variablesPositivas = variablesPositivas;
		this.objetivo = objetivo;
		this.restricciones = new ArrayList<LinearConstraint>();
		this.simplex = new SimplexSolver();
	}
	
	public void crearFuncionEconomica(double ... coeficientes) {
		this.funcionEconomica = new LinearObjectiveFunction(coeficientes, 0);
	}
	
	public void agregarRestriccion(Relationship unComparador, double valorAcomprar, double ... coeficientes) {
		this.restricciones.add(new LinearConstraint(coeficientes,unComparador, valorAcomprar));
	}
	
	public PointValuePair resolver() throws TooManyIterationsException{
		return this.simplex.optimize(
										new MaxIter(100),
										this.funcionEconomica,
										new LinearConstraintSet(this.restricciones),
										this.objetivo,
										new NonNegativeConstraint(this.variablesPositivas)
				);
	}
	//probando funcionamiento que esta en el test DE LA CATEDRA, ... FUNCIONA correctamentee
	public static void main(String[] args) {
		AlgoritmoSimplex algoritmo = new AlgoritmoSimplex(GoalType.MAXIMIZE, true);
		double[] unos = {1,1,1};
		double[] coeficientes = {0.18,0.875,0.06};
		algoritmo.crearFuncionEconomica(unos);
		algoritmo.agregarRestriccion(Relationship.LEQ, 440640, coeficientes);
		algoritmo.agregarRestriccion(Relationship.GEQ, 90, 		1, 		0, 		0);
		algoritmo.agregarRestriccion(Relationship.LEQ, 370, 	1, 		0, 		0);
		algoritmo.agregarRestriccion(Relationship.GEQ, 6, 		0, 		1, 		0);
		algoritmo.agregarRestriccion(Relationship.LEQ, 30, 		0, 		1, 		0);
		algoritmo.agregarRestriccion(Relationship.GEQ, 120, 	0, 		0, 		1);
		algoritmo.agregarRestriccion(Relationship.LEQ, 360, 	0, 		0, 		1);
		PointValuePair solucion = algoritmo.resolver();
		System.out.println("Z = " +solucion.getValue());
		for (int i = 0; i < coeficientes.length; i++) {
			System.out.println("X"+i+" "+solucion.getPoint()[i]);
		}
	}
}
