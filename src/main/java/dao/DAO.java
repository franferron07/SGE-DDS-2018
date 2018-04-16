package dao;

import java.util.List;

public interface DAO<T> {

	void insertar(T a);

	void eliminar(T a);

	List<T> obtenerTodos();

}
