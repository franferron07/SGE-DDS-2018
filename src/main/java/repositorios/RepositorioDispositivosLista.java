package repositorios;

import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoUsuario;
import models.ModelHelper;
import usuarios.Cliente;
import usuarios.Usuario;

public class RepositorioDispositivosLista {

	public DaoJson<DispositivoDetalle> dao;
	
	public static List<DispositivoDetalle> dispositivosLista;
	
	public static ModelHelper model;
	
	//constructor
	public RepositorioDispositivosLista(){
		this.dao= new DaoJson<DispositivoDetalle>("dispositivosLista.json");
		this.dispositivosLista = new ArrayList<DispositivoDetalle>();
		
		cargarDatos();
		
	}
	
	
	
	public static void cargarDispositiosLista(){
		
		model = new ModelHelper();
		dispositivosLista = new ArrayList<DispositivoDetalle>();
		
		dispositivosLista.addAll(model.buscarTodos(DispositivoDetalle.class));
		
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
