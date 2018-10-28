/* Este objeto se usa para mapear el transformador real 
 * y obtener los datos que interesan al momento de mostrarlo en el mapa.
 * A su vez, a gson no le gusta las referencias bidireccionales, arrojando error de Stack Overflow, 
 * ya que hibernate tiene que trabajar con esas referencias, no podemos quitarlas.
  
 */
package Utils;

import java.util.List;

import geoposicionamiento.Coordenada;

public class TrafoJson {

	private int id;
	private Coordenada coordenadas;
	private List<Integer> clientes;
	private int zonaGeografica;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Coordenada getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(Coordenada coordenadas) {
		this.coordenadas = coordenadas;
	}
	public List<Integer> getClientes() {
		return clientes;
	}
	public void setClientes(List<Integer> clientes) {
		this.clientes = clientes;
	}
	public int getZonaGeografica() {
		return zonaGeografica;
	}
	public void setZonaGeografica(int zonaGeografica) {
		this.zonaGeografica = zonaGeografica;
	}
	public TrafoJson(int id, Coordenada coordenadas, List<Integer> clientes, int zonaGeografica) {
		super();
		this.id = id;
		this.coordenadas = coordenadas;
		this.clientes = clientes;
		this.zonaGeografica = zonaGeografica;
	}
	
	
}
