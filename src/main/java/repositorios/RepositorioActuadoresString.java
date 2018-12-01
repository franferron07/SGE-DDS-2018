package repositorios;

import java.util.ArrayList;
import java.util.List;

import dispositivos.DispositivoDetalle;
import models.ModelHelper;
import reglasYActuadores.ActuadorString;


public class RepositorioActuadoresString {
	
	public static List<ActuadorString> actuadores =new ArrayList<ActuadorString>();
	
	public static ModelHelper model;
	
	
	public static void cargarActuadoresEnum(){
		
		model = new ModelHelper();
		actuadores = new ArrayList<ActuadorString>();
		
		actuadores.addAll(model.buscarTodos(ActuadorString.class));
		
	}
	
	public static ActuadorString buscarActuadorEnum(int id_actuador) {
		
		return actuadores.stream().filter(a -> a.getId() == id_actuador).findFirst().get();
	}
	
	public static ActuadorString buscarActuadorEnum(String nombre) {
		
		return actuadores.stream().filter(a -> a.getNombre() == nombre).findFirst().get();
	}

	public static List<ActuadorString> getActuadores() {
		return actuadores;
	}

	public static void setActuadores(List<ActuadorString> actuadores) {
		RepositorioActuadoresString.actuadores = actuadores;
	}
	
	
	
	
}
