package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Utils.HibernateProxyTypeAdapter;
import Utils.TrafoJson;
import Utils.ZonaJson;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Transformador;
import geoposicionamiento.ZonaGeografica;
import models.ModelHelper;
import models.TransformadorModel;

public class MapController {

	public String getZonas(Request request,Response response) {
		response.type("application/json");
		
		ModelHelper model = new ModelHelper();
		List <ZonaGeografica> zonas = model.buscarTodos(ZonaGeografica.class);
		List <ZonaJson> zonajson = new ArrayList<ZonaJson>();
		List<Coordenada> coordenadas = new ArrayList<Coordenada>();
		
		for (ZonaGeografica zona : zonas) {
			for (Coordenada coordenada : zona.getCoordenadas()) {
				coordenadas.add(new Coordenada( coordenada.getLongitud(),coordenada.getLatitud() ));
			}
			zonajson.add(new ZonaJson(zona.getId(), coordenadas, zona.consumoTotal(LocalDateTime.now().minusMonths(1),LocalDateTime.now()) ));
			coordenadas = new ArrayList<Coordenada>();
		}
		
		GsonBuilder b = new GsonBuilder();
		b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = b.create();
		
		String json = gson.toJson(zonajson);
		System.out.println(json);
		return json;
	}
	
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
