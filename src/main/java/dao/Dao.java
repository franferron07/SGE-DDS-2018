package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import excepciones.ArchivoException;

public class Dao {


	//metodo que permite leer un archivo json y devuelve lo leido siempre en una lista.
	public static <T> List<T> leerArchivo(String ruta_archivo, Class<T> clase) throws ArchivoException {
		
		//creo mapper jackson para leer archivo 
		ObjectMapper JSON_MAPPER = new ObjectMapper();
		
		List<T> resultados = null;
		try {
			
			resultados = JSON_MAPPER.readValue( new File(ruta_archivo) , JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, clase));
			
			return resultados;
			
		/* EXCEPCIONES */
		} catch (com.fasterxml.jackson.core.JsonParseException e) {
			throw new ArchivoException("Error de Formato de Archivo");
		} catch (FileNotFoundException e) {
			throw new ArchivoException("NO Se Logro Cargar El Archivo");
		} catch (com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException e) {
			throw new ArchivoException(
					"No esta bien formateado el archivo, le falta" + e.getLocation().getSourceRef().toString());
		} catch (com.fasterxml.jackson.databind.JsonMappingException e) {
			throw new ArchivoException("No se pudo Mapear los usuarios");
		} catch (IOException e) {
			throw new ArchivoException("Error de Lectura/Escritura de Archivo");
		}
		
	}

	


}
