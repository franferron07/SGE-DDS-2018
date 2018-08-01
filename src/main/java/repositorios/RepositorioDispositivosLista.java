package repositorios;

import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import dispositivos.DispositivoLista;

public class RepositorioDispositivosLista {

	private DaoJson<DispositivoLista> dao;
	private List<DispositivoLista> dispositivosLista;
	
	//constructor
	public RepositorioDispositivosLista(){
		this.dao= new DaoJson<DispositivoLista>("dispositivosLista.json");
		this.dispositivosLista = new ArrayList<DispositivoLista>();
		
		cargarDatos();
	}
	
	//carga datos del archivo
	public void cargarDatos(){
		if( this.dispositivosLista.isEmpty() ){
			this.dispositivosLista = dao.obtener();
		}
	}
	
	
	
}
