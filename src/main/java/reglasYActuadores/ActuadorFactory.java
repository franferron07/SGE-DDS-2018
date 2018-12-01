package reglasYActuadores;


public class ActuadorFactory {
	
	
	public ActuadorBase crearActuador( String tipo ){

		switch( tipo ){
			
		case "APAGAR":
			return new ApagarDICommand(); 
		
		case "ENCENDER":
			return new PrenderDiCommand();
			
		case "CAMBIAR_INTENSIDAD_LUZ":
			return new CambiarIntensidadLuzCommand();
		
		case "CAMBIAR_TEMPERATURA":
			return new CambiarTemperaturaActuadorCommand();
			
		default: return null;
		}
		
	}

}
