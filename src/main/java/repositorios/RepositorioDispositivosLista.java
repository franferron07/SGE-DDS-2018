package repositorios;

import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoUsuario;
import usuarios.Cliente;

public class RepositorioDispositivosLista {

	public DaoJson<DispositivoDetalle> dao;
	public List<DispositivoDetalle> dispositivosLista;
	
	//constructor
	public RepositorioDispositivosLista(){
		this.dao= new DaoJson<DispositivoDetalle>("dispositivosLista.json");
		this.dispositivosLista = new ArrayList<DispositivoDetalle>();
		
		cargarDatos();
		
	}
	
	//carga datos del archivo
	public void cargarDatos(){
		if( this.dispositivosLista.isEmpty() ){
			this.dispositivosLista = dao.obtener();
		}
	}

	public List<DispositivoDetalle> getDispositivosLista() {
		
		return dispositivosLista;
	}

	public void setDispositivosLista(List<DispositivoDetalle> dispositivosLista) {
		this.dispositivosLista = dispositivosLista;
	}
	
	public DispositivoDetalle buscarDispositivo( int id_disp) {
		
		return dispositivosLista.stream().filter(d -> d.getId() == id_disp).findFirst().get();
	}
	
	
	
}
