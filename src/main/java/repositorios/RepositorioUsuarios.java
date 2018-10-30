package repositorios;

import java.util.ArrayList;
import java.util.List;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoUsuario;
import models.UsuarioModel;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.Usuario;

public class RepositorioUsuarios {
	
	public   List<Usuario> usuarios;
	public  UsuarioModel usuarioModel;
	
	
	public RepositorioUsuarios(){
		
		usuarioModel = new UsuarioModel();
		usuarios = new ArrayList<Usuario>();
		
		/* creo cliente */
		Cliente cliente =  new Cliente();
		cliente.setUsername("test");
		cliente.setPassword("test");
		cliente.setNombre("testNombre");
		cliente.setApellido("testApellido");
		cliente.setDomicilio2("calle falsa 1234");
		cliente.setTelefonoContacto("4444-2222");
		cliente.id= 1;
		
		/* creo admin */
		Administrador admin = new Administrador();
		admin.id= 2;
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setNombre("adminNombre");
		admin.setApellido("adminApellido");
		admin.setDomicilio2("calle falsa 1234");
		
		
		
		/* agrego dispositivo a cliente */
		DispositivoDetalle detalle = new DispositivoDetalle();
		detalle.id = 1;
		detalle.setNombre("Televisor");
		detalle.setDescripcion("tele 4k");
		DispositivoEstandar estandar = new DispositivoEstandar(detalle);
		estandar.id = 1;
		cliente.agregarDispositivo(estandar);
		
		
		usuarios.add(cliente);
		usuarios.add(admin);
	}
	
	public   Usuario buscarUsuario(String username){
		
		return usuarios.stream().filter( u -> username.equals( u.getUsername())).findFirst().get();
		
		//return usuarioModel.buscarUsuarioPorUsername(username);
	}
	
	
	public   Usuario buscarUsuario(int id){
		
		return usuarios.stream().filter( u -> id == u.getId()).findFirst().get();
	}

	public DispositivoUsuario buscarDispositivo(Cliente cliente, int id_disp) {
		
		return cliente.getDispositivos().stream().filter(d -> d.id == id_disp).findFirst().get();
	}

	
	public void agregar_dispositivo_usuario(int id, DispositivoUsuario dispositivo) {
		
		Cliente cliente = (Cliente) buscarUsuario(id);
		cliente.agregarDispositivo(dispositivo);
	}
	
	

}
