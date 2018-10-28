package controllers;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Utils.HibernateProxyTypeAdapter;
import Utils.TrafoJson;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Transformador;
import models.TransformadorModel;

public class MapController {

	
	
	public String getTrafos(Request request, Response response) {
		response.type("application/json");
		
		
		TransformadorModel tm = new TransformadorModel();
		List<Transformador> transformadores = tm.buscarTodosLosTransformadores();
		List<TrafoJson> trafojson = new ArrayList<TrafoJson>();
		List<Integer> clientes = new ArrayList<Integer>();
		
		
		for (Transformador transformador : transformadores) {
			for (Cliente cliente : transformador.getClientes()) {
				clientes.add(cliente.getId());
			}
			
			trafojson.add(new TrafoJson( transformador.getId(), new Coordenada(transformador.getUbicacion().getLongitud(),transformador.getUbicacion().getLatitud()), clientes, transformador.getZonaAsignada().getId(), transformador.consumoTotal(LocalDateTime.now().minusMonths(1),LocalDateTime.now()) ));
		}
		
		GsonBuilder b = new GsonBuilder();
		b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = b.create();
		
		String json = gson.toJson(trafojson);
		System.out.println(json);
		return json;
		
	}
	

	
	public ModelAndView drawMap(Request request, Response response) {
		return new ModelAndView(null, "mapa.hbs");
	}
}
