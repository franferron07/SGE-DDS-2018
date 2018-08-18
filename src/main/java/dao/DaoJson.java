package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DaoJson<T> implements DAO<T> {

	public String archivo;
	public List<T> datos;

	
	public DaoJson(String archivo) {
		this.archivo = archivo;
		this.datos= new ArrayList<T>();
	}

	@Override
	public List<T> obtener() {
		String contenido="";
		Gson g = new Gson();
		
		contenido= this.deserealizarArchivo();
		
		Type listType = new TypeToken<ArrayList<T>>(){}.getType();		
		datos = g.fromJson(contenido,listType);

		return datos;
	}

	//este metodo habria que quitarlo
	@Override
	public void guardar(List<T> listaNodos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(T nodo) {
		if( datos.isEmpty() ) this.obtener();
		this.agregarDato(nodo);
		this.copiarLista();
	}


	//copia los datos de la lista en el archivo
	private void copiarLista() {
		Gson g = new Gson();
		String texto = g.toJson(datos);
		FileWriter fw = null;
	    PrintWriter pw;
	    
		try
        {
            fw = new FileWriter(this.archivo);
            pw = new PrintWriter(fw);
            pw.print(texto);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        } finally {
           try {
           if (fw != null)
              fw.close();
           } catch (Exception e2) {
              System.out.println(e2.getMessage());;
           }
        }
	}

	@Override
	public void borrar(T nodo) {
		if( datos.isEmpty() ) this.obtener();
		this.borrarDato(nodo);
		this.copiarLista();
		this.obtener();
		
	}
	
	private void agregarDato(T nodo) {
		datos.add(nodo);
	}
	
	private void borrarDato(T nodo) {
		datos.remove(nodo);
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
