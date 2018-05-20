package repositorios;

import java.util.List;
import java.util.stream.Collectors;

import dao.JsonUsuariosDAO;
import entities.Administrador;
import entities.Cliente;
import entities.Usuario;
/**
 * Patron Strategy
 *
 */
public class RepositorioUsuarios {
	private JsonUsuariosDAO dao;
	
	public RepositorioUsuarios(JsonUsuariosDAO dao) {
		//this.dao = new JsonUsuariosDAO("usuarios.json");
		this.dao = dao;
	}

	public void setDao(JsonUsuariosDAO dao) {
		this.dao = dao;
	}

	public List<Cliente> obtenerClientes() {
		List<Usuario> usuarios = this.dao.obtener();
		return usuarios.stream().filter(Cliente.class::isInstance).map(Cliente.class::cast)
				.collect(Collectors.toList());
	}
	
	public List<Administrador> obtenerAdministradores() {
		List<Usuario> usuarios = this.dao.obtener();
		return usuarios.stream().filter(Administrador.class::isInstance).map(Administrador.class::cast)
				.collect(Collectors.toList());
	}
	
	public void guardar(List<Usuario> usuarios ) {
		this.dao.guardar(usuarios);
	}
	
	public List<Usuario> recargarUsuarios() {
		return this.dao.obtener();
	}
	
	public void guardar(Usuario usuario){
		this.dao.guardar(usuario);
		
	}	
	public void borrar(Usuario usuario) {
		this.dao.borrar(usuario);
	}
}
