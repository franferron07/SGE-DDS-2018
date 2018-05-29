package archivosTest;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.JsonUsuariosDAO;
import entities.Administrador;
import entities.Cliente;
import entities.Dispositivo;
import entities.DispositivoInteligente;
import entities.ModoApagado;
import entities.ModoEncendido;
import entities.Usuario;
import excepciones.ArchivoException;
import repositorios.RepositorioUsuarios;

public class EscrituraUsuariosJsonTest {

	RepositorioUsuarios repoUsuarios;

	@Before
	public void init() {
		JsonUsuariosDAO jsonUsuariosDAO=new JsonUsuariosDAO("usuarios.json");
		repoUsuarios = new RepositorioUsuarios(jsonUsuariosDAO);
		}

	@Test
	public void testEscrituraUsuarios() throws ArchivoException {
        List<Usuario> usuarios=new ArrayList<Usuario>();
        List<Dispositivo> dispositivosInteligentes=new ArrayList<Dispositivo>();
               
		Cliente cliente1 = new Cliente(dispositivosInteligentes);
		cliente1.setNombre("Cliente1");

		DispositivoInteligente dispositivo1 = new DispositivoInteligente(new ModoApagado());
		dispositivo1.setNombre("Dispositivo1");
		ModoEncendido modoEncendido=new ModoEncendido(10);
		modoEncendido.encenderse(dispositivo1);
		DispositivoInteligente dispositivo2 = new DispositivoInteligente(new ModoApagado());
		dispositivo2.setNombre("Dispositivo2");
		modoEncendido=new ModoEncendido(20);
		modoEncendido.encenderse(dispositivo2);
		
		cliente1.agregarDispositivo(dispositivo1);
		cliente1.agregarDispositivo(dispositivo2);
		
		Administrador administrador=new Administrador();
		administrador.setNombre("administrador1");
		
		usuarios.add(cliente1);
		usuarios.add(administrador);
		try {
			Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();			
			String jsonParameter = gson.toJson(usuarios);			
			System.out.println(jsonParameter);				
			Type resultType = new TypeToken<List<Usuario>>() {}.getType();  			
			List<Usuario> userRolePojos = gson.fromJson(jsonParameter, resultType);
			System.out.println("Usuarios: "+userRolePojos);
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	//	int cantidadUsuariosPrevios = repoUsuarios.obtenerClientes().size();
		
	//	repoUsuarios.guardar(cliente1);
		
	//	Assert.assertEquals(cantidadUsuariosPrevios+1, repoUsuarios.obtenerClientes().size());
	}
	
	@Test
	public void testEscrituraClientes() throws ArchivoException {
        List<Cliente> clientes=new ArrayList<Cliente>();
        List<Dispositivo> dispositivos=new ArrayList<Dispositivo>();
       
		Cliente cliente1 = new Cliente(dispositivos);
		cliente1.setNombre("Cliente1");

		DispositivoInteligente dispositivo1 = new DispositivoInteligente(new ModoApagado());
		dispositivo1.setNombre("Dispositivo1");
			
		DispositivoInteligente dispositivo2 = new DispositivoInteligente(new ModoApagado());
		dispositivo2.setNombre("Dispositivo2");
				
		cliente1.agregarDispositivo(dispositivo1);
		cliente1.agregarDispositivo(dispositivo2);
		
		clientes.add(cliente1);
		try {
			Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();			
			String jsonParameter = gson.toJson(clientes);			
			System.out.println(jsonParameter);				
			Type resultType = new TypeToken<ArrayList<Cliente>>() {}.getType();  			
			List<Cliente> userRolePojos = gson.fromJson(jsonParameter, resultType);
			System.out.println("clientes: "+userRolePojos);
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	//	int cantidadUsuariosPrevios = repoUsuarios.obtenerClientes().size();
		
	//	repoUsuarios.guardar(cliente1);
		
	//	Assert.assertEquals(cantidadUsuariosPrevios+1, repoUsuarios.obtenerClientes().size());
	}
}
