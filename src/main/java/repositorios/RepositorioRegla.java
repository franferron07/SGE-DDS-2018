package repositorios;

import java.util.ArrayList;
import java.util.List;

import models.ModelHelper;
import models.UsuarioModel;
import reglasYActuadores.Regla;


public class RepositorioRegla {

	
	public static List<Regla> reglas;
	public static  ModelHelper model;
	
	//Cargo todos las reglas en memoria. 
	public static  void cargarReglas(){
		
		model = new UsuarioModel();
		reglas = new ArrayList<Regla>();
		
		reglas.addAll(model.buscarTodos(Regla.class));
	}
	
	public  static Regla buscarRegla(int id){
		
		return reglas.stream().filter( u -> id == u.getId()).findFirst().get();
	}
	
	
	public static void agregarRegla( Regla regla ){
		
		reglas.add(regla);
	}
	
	//getter y setters
	public static List<Regla> getReglas() {
		return reglas;
	}

	public static void setReglas(List<Regla> reglas) {
		RepositorioRegla.reglas = reglas;
	}

	public static void quitarRegla(Regla regla) {
		
		reglas.remove(regla);
	}
	
	
	
	
}
