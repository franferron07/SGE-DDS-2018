package usuarios;

import geoposicionamiento.Ubicable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo")
@DiscriminatorValue(value="usuario")
public abstract class Usuario extends Ubicable{


	@Column(name="nombre")
	protected String nombre;
	@Column(name="apellido")
	protected String apellido;
	@Column(name="domicilio")
	protected String domicilio;
	@Column(name="nombreUsuario")
	protected String nombreUsuario;
	@Column(name="password")
	protected String password;
	
	public Usuario() {
		
	}
	
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