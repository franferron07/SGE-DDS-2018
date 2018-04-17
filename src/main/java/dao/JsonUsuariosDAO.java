package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonUsuariosDAO<clazz> extends DAOAbstract {

	private String rutaArchivo;
	
	public JsonUsuariosDAO(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
	
	@Override
	public <T> void insertar(T clazz) {
		
	}

	@Override
	public <T> void eliminar(T clazz) {
	
	}

	@Override
	public <T> List<T> obtenerTodos(T clazz) {
		
		List<clazz> listaUsuarios = new ArrayList<clazz>();
		try {

			ObjectMapper mapper = new ObjectMapper();

			JsonNode nodosUsuario = mapper.readTree(new File(rutaArchivo)).get("Clientes");
			if (nodosUsuario.isArray()) {
				for (final JsonNode nodoUsuario : nodosUsuario) {
					clazz us = (clazz) mapper.treeToValue(nodoUsuario, clazz.getClass());
					listaUsuarios.add(us);

				}
			}

			/* EXCEPCIONES */
		} catch (com.fasterxml.jackson.core.JsonParseException e) {
			// throw new ArchivoException("Error de Formato de Archivo");
		} catch (FileNotFoundException e) {
			// throw new ArchivoException("NO Se Logro Cargar El Archivo");
		} catch (com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException e) {
			// throw new ArchivoException("No esta bien formateado el archivo, le falta" +
			// e.getLocation().getSourceRef().toString());
		} catch (com.fasterxml.jackson.databind.JsonMappingException e) {
			// throw new ArchivoException("No se pudo Mapear los usuarios");
		} catch (IOException e) {
			// throw new ArchivoException("Error de Lectura/Escritura de Archivo");
		}
		return (List<T>) listaUsuarios;

	}

	
	
	
	

}
