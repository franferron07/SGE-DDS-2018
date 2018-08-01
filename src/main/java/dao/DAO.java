package dao;

import java.util.List;

public interface DAO<T> {

	public  List<T> obtener();
	public  void guardar(List<T> listaNodos);
	public  void guardar(T nodo);			
	public  void borrar(T nodo);
}
