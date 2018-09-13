package timer;


import java.time.LocalDateTime;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import optimizacion_horas.Optimizador;
import repositorios.RepositorioClientes;
import usuarios.Cliente;

public class Tarea extends TimerTask {

	public Timer timer = new Timer("Simplex");
	public Optimizador simplex = new Optimizador();
	public RepositorioClientes repositorioCliente;
	public long horas;
	
	
	public Tarea() {
		
	}
	
	public Tarea(long hora) {
		repositorioCliente = new RepositorioClientes();
		horas = hora;
	}
	
	@Override
	public void run() {
		ejemploSaludar();
		ejecutarSimplex();
	}
	
	public void ejemploSaludar() {
		System.out.println("Lol");
	}

	public void parar() {
		this.timer.cancel();
	}
	
	//ejecuto metodo run cada x horas, esto esta en milisegundos.
	public void ejecutarCadaHora() {
		this.timer.schedule(this, 0, this.horas*3600000);
	}
	
	public void ejecutarSimplex() {
		
		List<Cliente> clientes = repositorioCliente.filtrarClientesAccionAutomatica();
		
		Iterator<Cliente> it = clientes.iterator();
		// recorro clientes para ejecutar simplex por cada cliente.
		while(it.hasNext()){
			
			Cliente cliente =it.next();
			//cargo los dipositivos del cliente y cliente en simplex y ejecuto
			this.simplex.cargarDispositivosEsenciales(cliente);
			this.simplex.maximizar();
			this.simplex.analisarResultados(primerFechaMes(), LocalDateTime.now());//recibe desde y hasta
		}

	}
	

	//devuelve la primer fecha del mes corriente.
	private LocalDateTime primerFechaMes() {
		LocalDateTime primeraFecha = LocalDateTime.now().withDayOfMonth(1) ;
		return primeraFecha;
	}

/*	private boolean sePasoDeTiempoLimite() {
		return this.tiempoLimite.isBefore(LocalDateTime.now());
	}
*/
	
	public static void main(String[] args) throws Throwable {
		Tarea tarea = new Tarea();
		tarea.ejecutarCadaHora();
//		Thread.sleep(4000);
		
	 
	}

	

}
