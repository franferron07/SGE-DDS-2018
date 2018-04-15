package repositorios;

import java.util.List;

import ar.frba.dds.grupo3.entities.Usuario;
import dao.Dao;
import excepciones.ArchivoException;

public class RepositorioUsuarios {
	
	private Dao dao;
	private List<Usuario> usuarios;
	
	
	
	public void inicializarUsuarios() throws ArchivoException{
		usuarios = dao.leerArchivo( "usuarios.json" , Usuario.class );
	}
	
	
}
