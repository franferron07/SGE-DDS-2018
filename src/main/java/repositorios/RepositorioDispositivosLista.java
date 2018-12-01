package repositorios;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.DaoJson;
import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoUsuario;
import models.ModelHelper;
import usuarios.Cliente;
import usuarios.Usuario;

public class RepositorioDispositivosLista {

	public DaoJson<DispositivoDetalle> dao;
	
	public static List<DispositivoDetalle> dispositivosLista =new ArrayList<DispositivoDetalle>();//;
	
	public static ModelHelper model;
	
	//constructor
	public RepositorioDispositivosLista(){
		this.dao= new DaoJson<DispositivoDetalle>("dispositivosLista.json");
		this.dispositivosLista = new ArrayList<DispositivoDetalle>();


		cargarDatos("dispositivosLista.json");
		
	}
	
	public static void agregarDipositivoDetalle(DispositivoDetalle unDispositivoDetalle) {
		model.agregar(unDispositivoDetalle);
	}
	
	
	
	public static void cargarDispositiosLista(){
		
		model = new ModelHelper();
		dispositivosLista = new ArrayList<DispositivoDetalle>();
		
		dispositivosLista.addAll(model.buscarTodos(DispositivoDetalle.class));
		
	}
	
	
	public static DispositivoDetalle buscarDispositivoDetalle(int id_disp) {
		
		return dispositivosLista.stream().filter(d -> d.id == id_disp).findFirst().get();
	}
	
	
	
	//carga datos del archivo
	public void cargarDatos(String pathJsonDispositivos){
		if( this.dispositivosLista.isEmpty() ){
			//this.dispositivosLista = dao.obtener();

			this.setDispositivosLista(this.jsonToArrayList(pathJsonDispositivos));
		}
	}

	public static List<DispositivoDetalle> getDispositivosLista() {
		
		return dispositivosLista;
	}

	public void setDispositivosLista(List<DispositivoDetalle> dispositivosLista) {
		this.dispositivosLista = dispositivosLista;
	}
	
	public static DispositivoDetalle buscarDispositivo( int id_disp) {
		
		return dispositivosLista.stream().filter(d -> d.getId() == id_disp).findFirst().get();
	}









	public static ArrayList<DispositivoDetalle> jsonToArrayList(String pathJsonDispositivos){//OK
		DaoJson dao=new DaoJson<DispositivoDetalle>(pathJsonDispositivos);
		ArrayList unosDispositivos = new ArrayList<DispositivoDetalle>();
		String gson = dao.deserealizarArchivo();
		JsonParser parser = new JsonParser();
		// Obtain Array
		JsonArray gsonArr = parser.parse(gson).getAsJsonArray();
		// for each element of array
		for (JsonElement obj : gsonArr) {
			// Object of array
			JsonObject gsonObj = obj.getAsJsonObject();
			// Primitives elements of object
			String nombre = gsonObj.get("nombre").getAsString();
			String descripcion = gsonObj.get("descripcion").getAsString();
			int hsMensualMinimo = gsonObj.get("hsMensualMinimo").getAsInt();
			int hsMensualMaximo = gsonObj.get("hsMensualMaximo").getAsInt();
			float consumoKwHora = gsonObj.get("consumoKwHora").getAsFloat();
			Boolean esBajoConsumo = gsonObj.get("esBajoConsumo").getAsBoolean();
			Boolean esEsencial = gsonObj.get("esEsencial").getAsBoolean();
			Boolean esInteligente = gsonObj.get("esInteligente").getAsBoolean();
            /*// List of primitive elements
            JsonArray demarcation = gsonObj.get("demarcation").getAsJsonArray();
            List<String> listDemarcation = new ArrayList<String>();
            for (JsonElement demarc : demarcation) {
                listDemarcation.add(demarc.getAsString());
            }*/
			DispositivoDetalle unDispositivo = new DispositivoDetalle(){
				@Override
				public String toString() {
					return "nombre: "+this.getNombre()+",descripcion: "+this.getDescripcion()+",consumo: "+this.getConsumoKwHora();
				}
			};
			unDispositivo.setNombre(nombre);
			unDispositivo.setDescripcion(descripcion);
			unDispositivo.setHsMensualMinimo(hsMensualMinimo);
			unDispositivo.setHsMensualMaximo(hsMensualMaximo);
			unDispositivo.setConsumoKwHora(consumoKwHora);
			unDispositivo.setEsEsencial(esEsencial);
			unDispositivo.setEsInteligente(esInteligente);
			unDispositivo.setEsBajoConsumo(esBajoConsumo);

			unosDispositivos.add(unDispositivo);
			System.out.println("jsonToArrayList()->   "+unDispositivo);//se puede borrar
		}
		return unosDispositivos;
	}
	
}
