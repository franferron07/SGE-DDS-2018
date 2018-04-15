package ar.frba.dds.grupo3.entities;

public abstract class Usuario {

	protected String nombre;
	protected String apellido;
	protected String domicilio;
	protected String nombreUsuario;
	protected String password;
	

	public boolean login(String nombreUsuario, String password) {
		return ( this.nombreUsuario == nombreUsuario && this.password == password );
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}