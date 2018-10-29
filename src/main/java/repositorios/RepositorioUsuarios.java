package repositorios;

import java.util.ArrayList;
import java.util.List;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import models.UsuarioModel;
import usuarios.Cliente;
import usuarios.Usuario;

public class RepositorioUsuarios {
	
	public   List<Usuario> usuarios;
	public  UsuarioModel usuarioModel;
	
	
	public RepositorioUsuarios(){
		
		usuarioModel = new UsuarioModel();
		usuarios = new ArrayList<Usuario>();
		
		Cliente cliente =  new Cliente();
		cliente.setUsername("test");
		cliente.setPassword("test");
		cliente.setNombre("testNombre");
		cliente.setApellido("testApellido");
		cliente.setDomicilio2("calle falsa 1234");
		cliente.setTelefonoContacto("4444-2222");
		cliente.id= 1;
		
		/* agrego dispositivo a cliente */
		DispositivoDetalle detalle = new DispositivoDetalle();
		detalle.id = 1;
		detalle.setNombre("Televisor");
		detalle.setDescripcion("tele 4k");
		DispositivoEstandar estandar = new DispositivoEstandar(detalle);
		estandar.id = 1;
		cliente.agregarDispositivo(estandar);
		
		
		
		usuarios.add(cliente);
	}
	
	public   Usuario buscarUsuario(String username){
		
		return usuarios.stream().filter( u -> username.equals( u.getUsername())).findFirst().get();
		
		//return usuarioModel.buscarUsuarioPorUsername(username);
	}
	
	
	public   Usuario buscarUsuario(int id){
		
		return usuarios.stream().filter( u -> id == u.getId()).findFirst().get();
		//return usuarioModel.buscarUsuarioPorUsername(username);
	}
	
	

}
