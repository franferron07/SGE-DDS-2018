package repositorios;

import java.util.ArrayList;
import java.util.List;

import dao.DaoJson;
import geoposicionamiento.ZonaGeografica;



public class RepositorioZonas {
	
	private DaoJson<ZonaGeografica> daoZonas;
	private List<ZonaGeografica> zonas;
	
	//constructor
	public RepositorioZonas(){
		this.daoZonas= new DaoJson<ZonaGeografica>("zonasGeograficas.json");
		this.zonas = new ArrayList<ZonaGeografica>();
		
		cargarDatos();
	}
	
	
	//carga datos del archivo
	public void cargarDatos(){
		if( this.zonas.isEmpty() ){
			this.zonas = daoZonas.obtener();
		}
	}
	
	
	//getters y setters
	public List<ZonaGeografica> getZonas() {
		return zonas;
	}
	public void setZonas(List<ZonaGeografica> zonas) {
		this.zonas = zonas;
	}
	public DaoJson<ZonaGeografica> getDaoZonas() {
		return daoZonas;
	}
	public void setDaoZonas(DaoJson<ZonaGeografica> daoZonas) {
		this.daoZonas = daoZonas;
	}
	
	
	
	

}
