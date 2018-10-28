package datos_transformadores;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Utils.HibernateProxyTypeAdapter;
import Utils.TrafoJson;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Transformador;
import models.TransformadorModel;
import usuarios.Cliente;

public class APIReturnAll {

	@Test
	public void test() {
		TransformadorModel tm = new TransformadorModel();
		Type listType = new TypeToken<List<Transformador>>() {}.getType();
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
		System.out.println( json);
	}

}
