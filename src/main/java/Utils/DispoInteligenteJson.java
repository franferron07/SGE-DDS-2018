package Utils;

import java.time.LocalDateTime;


import dispositivos.DispositivoDetalle;
import usuarios.Cliente;

public class DispoInteligenteJson {

	public int id;
	public boolean activado;
	public String modo;
	
	
	public DispoInteligenteJson(int id, boolean activado, String modo) {
		super();
		this.id = id;
		this.activado = activado;
		this.modo = modo;
	}
	
	
	
}
