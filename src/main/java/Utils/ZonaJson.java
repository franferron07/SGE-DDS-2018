package Utils;

import java.util.List;

import geoposicionamiento.Coordenada;

public class ZonaJson {

	public ZonaJson(int id, String coordenadas, double consumo) {
		super();
		this.id = id;
		this.coordenadas = coordenadas;
		this.consumo = consumo;
	}
	private int id;
	private String coordenadas;
	private double consumo;
}
