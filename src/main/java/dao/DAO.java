package dao;

import java.util.List;

import entities.Usuario;

public abstract class DAO<T> {

	public abstract List<T> obtener();

	public abstract void guardar(List<Usuario> listaUsuarios);

}
