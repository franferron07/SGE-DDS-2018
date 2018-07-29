package dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dispositivos.DispositivoLista;

public class JsonDispositivoListaDAO implements DAO<DispositivoLista> {

	private String rutaArchivo;
	
	public JsonDispositivoListaDAO(String ruta){
		rutaArchivo = ruta;
	}
	
	
	@Override
	public List<DispositivoLista> obtener() {
		
		Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();

		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		try {

			List<DispositivoLista> dispositivos = new ArrayList<DispositivoLista>();
			JsonNode nodosDispositivo = mapper.readTree(new File(this.rutaArchivo)).get("dispositivos");

			dispositivos = (List<DispositivoLista>) mapper.treeToValue(nodosDispositivo, dispositivos.getClass());
			
			//TODO
			Type pagedResultType = new TypeToken<List<DispositivoLista>>() {}.getType();  
			
		//	List<Usuario> userRolePojos = gson.fromJson(nodosCliente, pagedResultType);
	
			return dispositivos;

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

	
	@Override
	public void guardar(List<DispositivoLista> listaUsuarios) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(DispositivoLista usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(DispositivoLista usuario) {
		// TODO Auto-generated method stub
		
	}



}
