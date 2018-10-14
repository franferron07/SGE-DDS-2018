package APIs;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import geoposicionamiento.Coordenada;

public class GeoCodingService {
	
	public static Coordenada getCoordinates(String address) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
				.apiKey("AIzaSyDOlN_6GL7ciW-HDqtSoVax8i-FXSDhgk4")
				.build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,
				address).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(results[0].geometry.location.lat));
		System.out.println(gson.toJson(results[0].geometry.location.lng));
		
		Double x = Double.parseDouble(gson.toJson(results[0].geometry.location.lat));
		Double y = Double.parseDouble(gson.toJson(results[0].geometry.location.lng));

		return new Coordenada(x,y);
	}
	
	
}
