package repositorios;

import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import geoposicionamiento.Transformador;

public class RepositorioTransformadores {
	
	private DaoJson<Transformador> daoTransformador;
	private List<Transformador> transformadores;
	
	//constructor
	public RepositorioTransformadores(){
		this.daoTransformador= new DaoJson<Transformador>("transformadores.json");
		this.transformadores = new ArrayList<Transformador>();
		
		cargarDatos();
	}
	
	
	//carga datos del archivo
	public void cargarDatos(){
		if( this.transformadores.isEmpty() ){
			this.transformadores = daoTransformador.obtener();
		}
	}

	//	getters y setters
	public DaoJson<Transformador> getDaoTransformador() {
		return daoTransformador;
	}


	public void setDaoTransformador(DaoJson<Transformador> daoTransformador) {
		this.daoTransformador = daoTransformador;
	}


	public List<Transformador> getTransformadores() {
		return transformadores;
	}


	public void setTransformadores(List<Transformador> transformadores) {
		this.transformadores = transformadores;
	}
	
	
	

}
