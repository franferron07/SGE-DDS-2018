package timer;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import optimizacion_horas.Simplex;
import usuarios.Cliente;

public class Tarea extends TimerTask {

	public Timer timer = new Timer("Simplex");
	public Simplex simplex = new Simplex();
	public long horas;
	
	
	public Tarea() {
		
	}
	
	public Tarea(Cliente _cliente) {
		this.cliente=_cliente;
	}
	
	@Override
	public void run() {
		this.ejemploSaludar();
		//ejecutarSimplex aca
	}
	public void ejemploSaludar() {
		System.out.println("Lol");
	}

	public void parar() {
		this.timer.cancel();
	}
	
	
	public void ejecutarCadaHora() {
		this.timer.schedule(this, 0, this.horas*3600000);
	}
	
	public void ejecutarSimplex() {
		
		this.simplex.cargarDispositivosEsenciales(_cliente);
		this.simplex.maximizar();
		this.simplex.analisarResultados(primerFechaMes(), LocalDateTime.now());//recibe desde y hasta
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
