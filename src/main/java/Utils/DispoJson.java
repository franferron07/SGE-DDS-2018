package Utils;

import java.time.LocalDateTime;


import dispositivos.DispositivoDetalle;
import usuarios.Cliente;

public class DispoJson {

	public int id;
	public String nombre;
	public String descripcion;
	public boolean activado;
	public String fecha_alta_s;
	
	
	public DispoJson(int id, String nombre, String descripcion, boolean activado,
			String fecha_alta_s) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activado = activado;
		this.fecha_alta_s = fecha_alta_s;
	}
	
}
