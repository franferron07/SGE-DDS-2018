package ar.frba.dds.grupo3.entities;

public class Categoria {

	private String nombre;
	private int consumoDesde;
	private int consumoHasta;
	private int montoAPagar;
	private int costoFijo;
	private String unidadCostoFijo;
	private int unidadCostoVariable;
	private int costoVariable;

	public Categoria() {

	}

	public String getNombre() {
		return nombre;
	}

	public int getConsumoDesde() {
		return consumoDesde;
	}

	public int getConsumoHasta() {
		return consumoHasta;
	}

	public int getMontoAPagar() {
		return montoAPagar;
	}

	public int getCostoFijo() {
		return costoFijo;
	}

	public String getUnidadCostoFijo() {
		return unidadCostoFijo;
	}

	public int getUnidadCostoVariable() {
		return unidadCostoVariable;
	}

	public int getCostoVariable() {
		return costoVariable;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setConsumoDesde(int consumoDesde) {
		this.consumoDesde = consumoDesde;
	}

	public void setConsumoHasta(int consumoHasta) {
		this.consumoHasta = consumoHasta;
	}

	public void setMontoAPagar(int montoAPagar) {
		this.montoAPagar = montoAPagar;
	}

	public void setCostoFijo(int costoFijo) {
		this.costoFijo = costoFijo;
	}

	public void setUnidadCostoFijo(String unidadCostoFijo) {
		this.unidadCostoFijo = unidadCostoFijo;
	}

	public void setUnidadCostoVariable(int unidadCostoVariable) {
		this.unidadCostoVariable = unidadCostoVariable;
	}

	public void setCostoVariable(int costoVariable) {
		this.costoVariable = costoVariable;
	}

	/**
	 * 
	 * @param unidad
	 */
	public void setUnidad(String unidad){

	}

}