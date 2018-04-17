package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Administrador;
import entities.Cliente;
import entities.Dispositivo;
import entities.Usuario;

public class JsonUsuariosDAO extends DAO<Usuario> {

	private String rutaArchivo;

	public JsonUsuariosDAO(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public void guardar(List<Usuario> listaUsuarios) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();

		try {

			mapper.writeValue(new File(this.rutaArchivo), listaUsuarios);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@Override
	public List<Usuario> obtener() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		try {

			List<Usuario> usuarios = new ArrayList<Usuario>();

			usuarios = (List<Usuario>) mapper.readValue(new File(this.rutaArchivo), usuarios.getClass());
			return usuarios;

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
