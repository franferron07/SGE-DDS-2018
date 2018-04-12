package dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Dao {

	

	
	//metodo que permite leer un archivo json y devuelve lo leido siempre en una lista.
	public static <T> List<T> leerArchivo(String ruta_archivo, Class<T> clase) {
		
		//creo mapper jackson para leer archivo json
		ObjectMapper JSON_MAPPER = new ObjectMapper();
		
		List<T> resultados = null;
		try {
			
			resultados = JSON_MAPPER.readValue( new File(ruta_archivo) , JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, clase));
			
		} catch (IOException e) {
			
			e.printStackTrace();	
		}
		
		return resultados;
	}

	


}
