package models;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import geoposicionamiento.Transformador;
public class TransformadorModel extends ModelHelper {
	
	public  TransformadorModel() {
		super();
	}
	
	public Transformador buscarTransformador(int id) {
		return super.buscar(Transformador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Transformador> buscarTodasLasFarmacias(){
		return super.buscarTodos(Transformador.class);
	}
}
