package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.frba.dds.grupo3.entities.Cliente;

public class JsonClientesDAO extends JsonDAO<Cliente> {

	private String rutaArchivo;

	public JsonClientesDAO(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public void insertar(Cliente a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Cliente c) {
		// TODO Auto-generated method stub

	}

	public List<Cliente> obtenerTodos() {

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		try {

			ObjectMapper mapper = new ObjectMapper();

			JsonNode nodosCliente = mapper.readTree(new File(rutaArchivo)).get("Clientes");
			if (nodosCliente.isArray()) {
				for (final JsonNode nodoCliente : nodosCliente) {
					Cliente cli = mapper.treeToValue(nodoCliente, Cliente.class);
					listaClientes.add(cli);

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
		return listaClientes;

	}
}
