package repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dao.DaoJson;
import geoposicionamiento.ZonaGeografica;


@Entity
@Table(name="repositorio_zonas")
public class RepositorioZonas {
	@Id
	@GeneratedValue
	private int id;
	
	
	private DaoJson<ZonaGeografica> daoZonas;
	
	@OneToMany(mappedBy="repositorio_zonas",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
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
	
	public int getId() {
		return this.id;
	}
	
	
	

}
