package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dispositivos.Modo;
import models.ModelHelper;
import models.UsuarioModel;
import reglasYActuadores.Regla;
import usuarios.Cliente;


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
	
	public static List<Regla> filtrarReglsaCliente( int id_cli ){
		
		try{
			
			Stream<Regla> reglasFilt = reglas.stream().filter( r -> r.getDispositivos().get(0).getCliente().id == id_cli );
			return reglasFilt.collect(Collectors.toList());	
			
		}catch(Exception  e){
			//si algo falla listo todas
			return getReglas();
		}
		
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
