package repositorios;

import java.util.ArrayList;
import java.util.List;


import dispositivos.DispositivoUsuario;
import models.UsuarioModel;
import usuarios.Cliente;
import usuarios.Usuario;

public class RepositorioUsuarios {
	
	public static List<Usuario> usuarios;
	public static  UsuarioModel usuarioModel;
	
	
	public RepositorioUsuarios(){
		
	}
	
	//Cargo todos los usuarios en memoria. 
	public static  void cargarUsuarios(){
		
		usuarioModel = new UsuarioModel();
		usuarios = new ArrayList<Usuario>();
		
		usuarios.addAll(usuarioModel.buscarTodos(Usuario.class));
	}
	
	public static  Usuario buscarUsuario(String username){
		
		return usuarios.stream().filter( u -> username.equals( u.getUsername())).findFirst().get();
		
	}
	
	
	public  static Usuario buscarUsuario(int id){
		
		return usuarios.stream().filter( u -> id == u.getId()).findFirst().get();
	}

	public static DispositivoUsuario buscarDispositivo(Cliente cliente, int id_disp) {
		
		return cliente.getDispositivos().stream().filter(d -> d.id == id_disp).findFirst().get();
	}

	
	public static void agregar_dispositivo_usuario(int id, DispositivoUsuario dispositivo) {
		
		Cliente cliente = (Cliente) buscarUsuario(id);
		cliente.agregarDispositivo(dispositivo);
		usuarioModel.agregar(dispositivo);
		
	}
	
	

}
