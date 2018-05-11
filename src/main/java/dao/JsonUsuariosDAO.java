package dao;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
		

		JsonNode rootNode = mapper.createObjectNode();

		JsonNode array = mapper.valueToTree(listaUsuarios);
		((ObjectNode) rootNode).set("usuarios", array);
		try {

			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.rutaArchivo), rootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@Override
	public List<Usuario> obtener() {
		Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();

		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		try {

			List<Usuario> usuarios = new ArrayList<Usuario>();
			JsonNode nodosCliente = mapper.readTree(new File(this.rutaArchivo)).get("usuarios");

	//		usuarios = (List<Usuario>) mapper.treeToValue(nodosCliente, usuarios.getClass());
			
			//TODO
			Type pagedResultType = new TypeToken<List<Usuario>>() {}.getType();  
			
		//	List<Usuario> userRolePojos = gson.fromJson(nodosCliente, pagedResultType);
	
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
