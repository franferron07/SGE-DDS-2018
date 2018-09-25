package usuarios;

import geoposicionamiento.Ubicable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
@DiscriminatorValue(value="usuario")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo")
public abstract class Usuario extends Ubicable{


	@Column(name="nombre")
	protected String nombre;
	@Column(name="apellido")
	protected String apellido;
	@Column(name="domicilio")
	protected String domicilio;
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="id")
	protected Login login;
	
	public Usuario() {
		
	}
	
	public boolean login(String nombreUsuario, String password) {
		return (login.getUsername() == nombreUsuario && login.getPassword() == password);
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


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
}