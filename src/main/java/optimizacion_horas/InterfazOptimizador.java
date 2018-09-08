package optimizacion_horas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import usuarios.Cliente;

public interface InterfazOptimizador {
	
	public void maximizar();
	
	public void minimizar();

	public void calcularHoras();
	
	public double consumoMaximoDeEnergia();

	public void agregarRestriccion(Relationship leq_, double consumoMaximoDeEnergia2_, double ... coeficientesDeFuncionObjetivo2_);	
	
	public void cargarDispositivos(DispositivoUsuario... _dispositivoUsuarios_);
		
	public void cargarDispositivosEsenciales(Cliente cliente);
	
	public void analizarResultados(LocalDateTime desde , LocalDateTime hasta);

}
