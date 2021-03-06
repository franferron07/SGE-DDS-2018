package sensorTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entities.Medicion;
import entities.Sensor;
import junit.framework.Assert;
import reglasYActuadores.CambiarIntensidadLuzCommand;
import reglasYActuadores.CambiarTemperaturaActuadorCommand;
import reglasYActuadores.CondicionRegla;
import reglasYActuadores.ReglaSimple;

public class SensorTest {
	
	private Sensor sensor;

	@Before
	public void init() {	
	 sensor=new Sensor();		
	}
	
	//instanciacion de sensor
	@Test
	public void testSensorConReglas(){
		ReglaSimple reglaSimple=new ReglaSimple("ReglaSimple1");
		sensor.agregarObservador(reglaSimple);
		reglaSimple=new ReglaSimple("ReglaSimple2");
		sensor.agregarObservador(reglaSimple);
		
		Assert.assertEquals(2, sensor.cantidadObservadores());
	}
	
	//instanciacion de regla con actuadores
	@Test
	public void testReglasConActuadores(){	
		CambiarIntensidadLuzCommand actuadorLuz = new CambiarIntensidadLuzCommand();
		CambiarTemperaturaActuadorCommand actuadorTemperatura = new CambiarTemperaturaActuadorCommand();
		ReglaSimple reglaSimple=new ReglaSimple("ReglaSimple1");
		reglaSimple.agregarActuador(actuadorLuz);
		reglaSimple.agregarActuador(actuadorTemperatura);
		
		Assert.assertEquals(2, reglaSimple.getActuadores().size() );
	}
	
	//sensor que notifica muchas reglas
	@Test
	public void testNotificarSensorReglas(){
		ReglaSimple reglaSimple=new ReglaSimple("ReglaSimple1");
		
		List<CondicionRegla> condiciones = new ArrayList<CondicionRegla>();
		CondicionRegla condicionRegla=new CondicionRegla(">",10f);
		condiciones.add(condicionRegla);
		reglaSimple.setCondiciones(condiciones);
		
		sensor.agregarObservador(reglaSimple);
		
		CambiarIntensidadLuzCommand actuadorLuz = new CambiarIntensidadLuzCommand();
		CambiarTemperaturaActuadorCommand actuadorTemperatura = new CambiarTemperaturaActuadorCommand();
		
		reglaSimple.agregarActuador(actuadorLuz);
		reglaSimple.agregarActuador(actuadorTemperatura);
		
		sensor.obtenerMedicion(new Medicion((double)10));
	}
	
}
