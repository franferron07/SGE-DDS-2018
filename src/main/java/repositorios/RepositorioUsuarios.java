package repositorios;

import java.util.ArrayList;
import java.util.List;

import models.UsuarioModel;
import usuarios.Usuario;

public class RepositorioUsuarios {
	
	public  List<Usuario> usuarios;
	public  UsuarioModel usuarioModel;
	
	
	public RepositorioUsuarios(){
		
		usuarioModel = new UsuarioModel();
		usuarios = new ArrayList<Usuario>();
	}
	
	public  Usuario buscarUsuario(String username){
		
		return usuarioModel.buscarUsuarioPorUsername(username);
	}
	
	

}
