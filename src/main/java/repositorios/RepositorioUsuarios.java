package repositorios;

import java.util.List;

import ar.frba.dds.grupo3.entities.Usuario;
import dao.Dao;

public class RepositorioUsuarios {
	
	private Dao dao;
	private List<Usuario> usuarios;
	
	
	
	public void inicializarUsuarios(){
		usuarios = dao.leerArchivo( "usuarios.json" , Usuario.class );
	}
	
	
}
