package dao;

import java.util.List;

public abstract class JsonDAO<T> implements DAO<T> {

	@Override
	public abstract void insertar(T a);

	@Override
	public abstract void eliminar(T a);

	@Override
	public abstract List<T> obtenerTodos();


	
}
