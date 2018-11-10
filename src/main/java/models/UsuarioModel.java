package models;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import usuarios.Usuario;


public class UsuarioModel extends ModelHelper {
	
	
	public  UsuarioModel() {
		super();
	}
	
	public Usuario buscarUsuarioPorID(int id) {
		return super.buscar(Usuario.class, new ImmutablePair<>("id", id));
	}
	
	public Usuario buscarUsuarioPorUsername(String username) {
		return super.buscar(Usuario.class, new ImmutablePair<>("username", username));
	}
	
	public List<Usuario> buscarTodosLosTransformadores(){
		return super.buscarTodos(Usuario.class);
	}
	
	
	

}
