package dispositivos;

import java.time.LocalDateTime;

public abstract class DispositivoUsuario {
	
	public DispositivoDetalle detalle;
	

	//seguramente esto sea un date time, desde y hasta. Me da el consumo en un determinado periodo de tiempo. 
	public abstract float consumoPeriodo( LocalDateTime desde , LocalDateTime hasta );
	
	public abstract boolean esInteligente(); // esEsencial() lo reemplaza para el simplex


	public boolean esEsencial(){
		return detalle.isEsEsencial();
	}
	
	
	//getters and setters
	public DispositivoDetalle getDispositivoDetalle() {
		return detalle;
	}

	public void setDispositivoLista(DispositivoDetalle dispositivoLista) {
		this.detalle = dispositivoLista;
	}
	
	
//	//------ simplex
	// en lugar de llamar cada momento a su atributo detalle ,esto lo simplifica haciendolo mas cohesivo ,
	//dependeria menos de su atributo por que ese mismo podria borrarse y reemplazarse con una base de datos 
	//o una especie de tabla mejorada 
	
	public  double getConsumoKwHora() {
		return this.detalle.getConsumoKwHora();
	}
	public  String getIdentificacion() {
		return this.detalle.getDescripcion();
	}
//	
	public  double getHsMensualMinimo() {
		return this.detalle.getHsMensualMinimo();
	}
	public  double getHsMensualMaximo(){
		return this.detalle.getHsMensualMaximo();
	}



}