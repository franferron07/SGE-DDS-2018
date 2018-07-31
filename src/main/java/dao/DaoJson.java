package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DaoJson<T> implements DAO<T> {

	
	public String archivo;
	
	public DaoJson(String archivo) {
		this.archivo = archivo;
	}

	@Override
	public List<T> obtener() {
		String contenido="";
		Gson g = new Gson();
		List<T> datos=null;
		
		contenido= this.deserealizarArchivo();
		
		Type listType = new TypeToken<ArrayList<T>>(){}.getType();
		datos = g.fromJson(contenido,listType);

		return datos;
	}

	//este metodo habria que quitarlo
	@Override
	public void guardar(List<T> listaUsuarios) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(T usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(T usuario) {
		// TODO Auto-generated method stub
		
	}
	
	//lee archivo y lo devuelve en una cadena
	public String deserealizarArchivo() {
		
		String contenido = "";
		String linea=null;
		FileReader fr;
		try {
			fr = new FileReader (new File(this.archivo));
			BufferedReader br = new BufferedReader(fr);
			
	        while((linea=br.readLine())!=null) {
	       	 contenido+=linea; 
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contenido;

	}
	
	

}
