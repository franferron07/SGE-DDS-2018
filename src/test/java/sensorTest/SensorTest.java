package sensorTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.CambiarIntensidadLuzCommand;
import entities.CambiarTemperaturaActuadorCommand;
import entities.Regla;
import entities.Sensor;
import junit.framework.Assert;

public class SensorTest {
	
	private Sensor sensor;
	private Regla regla1;
	private Regla regla2;
	

	@Before
	public void init() {
		
		sensor= new Sensor();
		regla1= new Regla("Cambiar Intensidad");
		regla2= new Regla("Cambiar temparatura");

	}
	
	//instanciacion de sensor
	@Test
	public void testSensorConReglas(){
		
		sensor.agregarRegla(regla1);
		sensor.agregarRegla(regla2);
		Assert.assertEquals(2, sensor.cantidadReglas());
	}
	
	//instanciacion de regla con actuadores
	@Test
	public void testReglasConActuadores(){
		
		CambiarIntensidadLuzCommand actuadorLuz = new CambiarIntensidadLuzCommand(null);
		CambiarTemperaturaActuadorCommand actuadorTemperatura = new CambiarTemperaturaActuadorCommand(null);
		
		regla1.agregarActuador(actuadorLuz);
		regla1.agregarActuador(actuadorTemperatura);
		
		Assert.assertEquals(2, regla1.getActuadores().size() );
	}
	
	//sensor que notifica muchas reglas
	@Test
	public void testNotificarSensorReglas(){
		
		sensor.agregarRegla(regla1);
		sensor.agregarRegla(regla2);
		
		CambiarIntensidadLuzCommand actuadorLuz = new CambiarIntensidadLuzCommand(null);
		CambiarTemperaturaActuadorCommand actuadorTemperatura = new CambiarTemperaturaActuadorCommand(null);
		
		regla1.agregarActuador(actuadorLuz);
		regla1.agregarActuador(actuadorTemperatura);
		
		sensor.obtenerMedicion();
		
	}
	
}
