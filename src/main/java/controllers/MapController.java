package controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import geoposicionamiento.Transformador;
import models.TransformadorModel;

public class MapController {

	
	
	public String getTrafos(Request request, Response response) {
		response.type("application/json");
		
		
		TransformadorModel tm = new TransformadorModel();
		Type listType = new TypeToken<List<Transformador>>() {}.getType();
		List<Transformador> transformadores = tm.buscarTodosLosTransformadores();
		
		Gson gson = new Gson();
		String json = gson.toJson(transformadores,listType);
		return json;
		
	}
	

	
	public ModelAndView drawMap(Request request, Response response) {
		return new ModelAndView(null, "mapa.hbs");
	}
}
