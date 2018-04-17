package dao;

import java.util.List;

public interface DAO<T> {
	
	public void insertar( T clazz);

	void eliminar(T a);

	List<T> obtenerTodos();



}
