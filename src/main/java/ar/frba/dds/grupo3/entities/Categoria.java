package ar.frba.dds.grupo3.entities;

public class Categoria {

	private String nombre;
	private float consumoDesde;
	private float consumoHasta;
	private float montoAPagar;
	private float costoFijo;
	private String unidadCostoFijo;
	private String unidadCostoVariable;
	private float costoVariable;

	public Categoria() {

	}

	public String getNombre() {
		return nombre;
	}

	public float getConsumoDesde() {
		return consumoDesde;
	}

	public float getConsumoHasta() {
		return consumoHasta;
	}

	public float getMontoAPagar() {
		return montoAPagar;
	}

	public float getCostoFijo() {
		return costoFijo;
	}

	public String getUnidadCostoFijo() {
		return unidadCostoFijo;
	}

	public String getUnidadCostoVariable() {
		return unidadCostoVariable;
	}

	public float getCostoVariable() {
		return costoVariable;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setConsumoDesde(float consumoDesde) {
		this.consumoDesde = consumoDesde;
	}

	public void setConsumoHasta(float consumoHasta) {
		this.consumoHasta = consumoHasta;
	}

	public void setMontoAPagar(float montoAPagar) {
		this.montoAPagar = montoAPagar;
	}

	public void setCostoFijo(float costoFijo) {
		this.costoFijo = costoFijo;
	}

	public void setUnidadCostoFijo(String unidadCostoFijo) {
		this.unidadCostoFijo = unidadCostoFijo;
	}

	public void setUnidadCostoVariable(String unidadCostoVariable) {
		this.unidadCostoVariable = unidadCostoVariable;
	}

	public void setCostoVariable(float costoVariable) {
		this.costoVariable = costoVariable;
	}

	

}