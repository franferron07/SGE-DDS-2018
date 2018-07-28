package dispositivos;

import java.time.LocalDateTime;

public abstract class DispositivoUsuario {
	
	public DispositivoLista dispositivoLista;
	

	//seguramente esto sea un date time, desde y hasta. Me da el consumo en un determinado periodo de tiempo. 
	public abstract float consumoPeriodo( LocalDateTime desde , LocalDateTime hasta );
	
	public abstract boolean esInteligente();


	//getters and setters
	public DispositivoLista getDispositivoLista() {
		return dispositivoLista;
	}

	public void setDispositivoLista(DispositivoLista dispositivoLista) {
		this.dispositivoLista = dispositivoLista;
	}
	
	


}