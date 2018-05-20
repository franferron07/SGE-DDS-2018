package dao;

import java.util.List;

public abstract interface DAO<T> {

	public abstract List<T> obtener();
	public abstract void guardar(List<T> listaUsuarios);
	public abstract void guardar(T usuario);			
	public abstract void borrar(T usuario);
}
