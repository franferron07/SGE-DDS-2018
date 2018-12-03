package controllers;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Utils.DispoInteligenteJson;
import Utils.DispoJson;
import Utils.HibernateProxyTypeAdapter;
import Utils.TrafoJson;
import Utils.ZonaJson;
import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import geoposicionamiento.Coordenada;
import geoposicionamiento.Transformador;
import geoposicionamiento.ZonaGeografica;
import models.ModelHelper;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class CLIController {

	public String getDispositivos(Request request,Response response) {
		response.type("text/plain");
		
		int id = Integer.parseInt(request.params("id"));
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		List<DispositivoUsuario> dispositivos = cliente.getDispositivos();
		List<DispositivoInteligente> dispos_inteligentes = cliente.filtrarDispositivosInteligentes();
		
		List<DispoJson> dispoJson = new ArrayList<DispoJson>();
		List<DispoInteligenteJson> dispoInteligenteJson = new ArrayList<DispoInteligenteJson>();
		
		for (DispositivoUsuario dispositivo : dispositivos) {
			
			DispositivoDetalle detalle;
			String nombre = "";
			String descrip = "";
			
			if (dispositivo.getDetalle() != null) {
				nombre = dispositivo.getDetalle().getNombre();
				descrip = dispositivo.getDetalle().getDescripcion();
			};
			
			dispoJson.add( new DispoJson( dispositivo.getId(), nombre, descrip, dispositivo.isActivado(), dispositivo.getFecha_alta_s()) );
		}
		
		for (DispositivoInteligente dispositivo : dispos_inteligentes) {
			
			
			dispoInteligenteJson.add( new DispoInteligenteJson( dispositivo.getId(),dispositivo.isActivado(), dispositivo.modoActual().getClass().getName() ) );
		}
		
		
		GsonBuilder b = new GsonBuilder();
		b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = b.create();
		
		String json = gson.toJson(dispoJson);
		String json_inteligentes = gson.toJson(dispoInteligenteJson);
		System.out.println(json);
		return "Todos los dispositivos: \n" + json + "\n Inteligentes: \n" + json_inteligentes;
	}
	
	public String apagarDispositivo(Request request,Response response) {
		response.type("text/plain");
		
		String status = "No existe ese dispositivo";
		
		int id = Integer.parseInt(request.params("id"));
		int id_dispositivo = Integer.parseInt(request.params("dispositivo"));
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		List<DispositivoInteligente> dispos_inteligentes = cliente.filtrarDispositivosInteligentes();
		
		
		for (DispositivoInteligente dispositivo_inteligente : dispos_inteligentes) {
			if(dispositivo_inteligente.getId() == id_dispositivo) {
				
				dispositivo_inteligente.apagar();
				status = "Dispositivo "+id_dispositivo+" apagado";
				break;
				
			}
		}
		
		return status;
	}
	
	public String encenderDispositivo(Request request,Response response) {
		response.type("text/plain");
		
		String status = "No existe ese dispositivo";
		
		int id = Integer.parseInt(request.params("id"));
		int id_dispositivo = Integer.parseInt(request.params("dispositivo"));
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		List<DispositivoInteligente> dispos_inteligentes = cliente.filtrarDispositivosInteligentes();
		
		
		for (DispositivoInteligente dispositivo_inteligente : dispos_inteligentes) {
			if(dispositivo_inteligente.getId() == id_dispositivo) {
				
				dispositivo_inteligente.encender();
				status = "Dispositivo "+id_dispositivo+" encendido";
				break;
				
			}
		}
		
		return status;
	}
	
	public String ahorroDispositivo(Request request,Response response) {
		response.type("text/plain");
		
		String status = "No existe ese dispositivo";
		
		int id = Integer.parseInt(request.params("id"));
		int id_dispositivo = Integer.parseInt(request.params("dispositivo"));
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		List<DispositivoInteligente> dispos_inteligentes = cliente.filtrarDispositivosInteligentes();
		
		
		for (DispositivoInteligente dispositivo_inteligente : dispos_inteligentes) {
			if(dispositivo_inteligente.getId() == id_dispositivo) {
				
				dispositivo_inteligente.ahorrarEnergia();
				status = "Dispositivo "+id_dispositivo+ " puesto en modo ahorro";
				break;
				
			}
		}
		
		return status;
	}
	
	public String consumoUltimoMes(Request request,Response response) {
		
		int id = Integer.parseInt(request.params("id"));
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		
		double consumo_ultimo_mes;
		consumo_ultimo_mes = cliente.consumoEnUnPeriodo(LocalDateTime.now().minusDays(30), LocalDateTime.now());
		return "Su consumo del último mes fue de " + consumo_ultimo_mes + " KW";
	}
	
}
