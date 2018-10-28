package Utils;

import java.util.List;

import geoposicionamiento.Coordenada;

public class ZonaJson {

	public ZonaJson(int id, List<Coordenada> coordenadas, double consumo) {
		super();
		this.id = id;
		this.coordenadas = coordenadas;
		this.consumo = consumo;
	}
	private int id;
	private List<Coordenada> coordenadas;
	private double consumo;
}
