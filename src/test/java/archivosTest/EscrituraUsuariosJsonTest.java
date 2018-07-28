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
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import dispositivos.ModoApagado;
import dispositivos.ModoEncendido;
import excepciones.ArchivoException;
import repositorios.RepositorioUsuarios;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.Usuario;

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
        List<DispositivoUsuario> dispositivosInteligentes=new ArrayList<DispositivoUsuario>();
               
		Cliente cliente1 = new Cliente(dispositivosInteligentes);
		cliente1.setNombre("Cliente1");

		DispositivoInteligente dispositivo1 = new DispositivoInteligente(new ModoApagado());
		ModoEncendido modoEncendido=new ModoEncendido();
		modoEncendido.encenderse(dispositivo1);
		DispositivoInteligente dispositivo2 = new DispositivoInteligente(new ModoApagado());
		modoEncendido=new ModoEncendido();
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
        List<DispositivoUsuario> dispositivos=new ArrayList<DispositivoUsuario>();
       
		Cliente cliente1 = new Cliente(dispositivos);
		cliente1.setNombre("Cliente1");

		DispositivoInteligente dispositivo1 = new DispositivoInteligente(new ModoApagado());
			
		DispositivoInteligente dispositivo2 = new DispositivoInteligente(new ModoApagado());
				
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
