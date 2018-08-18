package repositorios;

import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import dispositivos.DispositivoDetalle;

public class RepositorioDispositivosLista {

	private DaoJson<DispositivoDetalle> dao;
	private List<DispositivoDetalle> dispositivosLista;
	
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
	
	
	
}
