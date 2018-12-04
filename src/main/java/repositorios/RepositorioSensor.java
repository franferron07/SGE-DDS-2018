package repositorios;

import java.util.ArrayList;
import java.util.List;

import entities.Sensor;
import models.ModelHelper;
import reglasYActuadores.ActuadorString;


public class RepositorioSensor {

public static List<Sensor> sensores =new ArrayList<Sensor>();
	
	public static ModelHelper model;
	
	
	public static void cargarSensores(){
		
		model = new ModelHelper();
		sensores = new ArrayList<Sensor>();
		sensores.addAll(model.buscarTodos(Sensor.class));
		
	}
	

	
	
}
