package entities;

public class DispositivoEstandar extends Dispositivo {
	

	private float horasPorDia;
	
	//constructor
	public DispositivoEstandar(){
	}
	
	
	@Override
	public boolean esInteligente() {
		return false;
	}

	public float consumoPeriodo(int dias){		
		return consumoEnElDia() * dias ; 
	}
	
	//me devuelve el consumo estimativo para un dia
	private float consumoEnElDia() {
		return horasPorDia * kwHora;
	}



	public float getHorasPorDia() {
		return horasPorDia;
	}



	public void setHorasPorDia(float horasPorDia) {
		this.horasPorDia = horasPorDia;
	}

	


}
