package sensorTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import entities.Regla;
import entities.Sensor;

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
	
}
