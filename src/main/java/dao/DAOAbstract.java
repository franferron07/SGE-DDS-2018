package dao;

import java.util.List;

public abstract class DAOAbstract {
	
	public abstract <T> void insertar( T clazz);

	abstract <T> void eliminar(T clazz);

	abstract <T> List<T> obtenerTodos(T clazz);


}
