package optimizacion_horas;

public class ResultadoHora {
	public String dispositivo_id;
	public double horasQuePuedeConsumir;
	public ResultadoHora(String dispositivo,double horas) {
		this.dispositivo_id=dispositivo;
		this.horasQuePuedeConsumir=horas;
	}
	public String getDispositivo() {
		return dispositivo_id;
	}
	public void setDispositivo(String dispositivo) {
		this.dispositivo_id = dispositivo;
	}
	public double getHorasQuePuedeConsumir() {
		return horasQuePuedeConsumir;
	}
	public void setHorasQuePuedeConsumir(double horasQuePuedeConsumir) {
		this.horasQuePuedeConsumir = horasQuePuedeConsumir;
	}
	
	
}
