package repositorios;

import java.util.List;
import java.util.stream.Collectors;

import dao.JsonUsuariosDAO;
import entities.Administrador;
import entities.Cliente;
import entities.Usuario;

public class RepositorioUsuarios {
	private JsonUsuariosDAO dao;
	private List<Usuario> usuarios;

	public RepositorioUsuarios() {

		this.dao = new JsonUsuariosDAO("usuarios.json");
		this.usuarios = this.dao.obtener();
	}

	public List<Cliente> obtenerClientes() {
		return this.usuarios.stream().filter(Cliente.class::isInstance).map(Cliente.class::cast)
				.collect(Collectors.toList());

	}
	
	public List<Administrador> obtenerAdministradores() {
		return this.usuarios.stream().filter(Administrador.class::isInstance).map(Administrador.class::cast)
				.collect(Collectors.toList());

	}
	
	public void guardar() {
		this.dao.guardar(this.usuarios);
	}
	
	public void recargarUsuarios() {
		this.usuarios = this.dao.obtener();
	}
	
	public void agregarUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}
	
	public void quitarUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
	}
}
