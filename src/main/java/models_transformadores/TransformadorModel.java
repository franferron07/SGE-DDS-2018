package models_transformadores;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import geoposicionamiento.Transformador;
public class TransformadorModel extends ModelHelper {
	
	public  TransformadorModel() {
		super();
	}
	
	public Transformador buscarTransformadorPorID(int id) {
		return super.buscar(Transformador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Transformador> buscarTodosLosTransformadores(){
		return super.buscarTodos(Transformador.class);
	}
}
