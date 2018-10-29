package usuarios;

import geoposicionamiento.Ubicable;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.google.maps.errors.ApiException;

import APIs.GeoCodingService;

@Entity
@Table(name="usuario")
@DiscriminatorValue("usuario")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_usuario")
public abstract class Usuario extends Ubicable{

	@Column(name="nombre")
	protected String nombre;
	@Column(name="apellido")
	protected String apellido;
	@Column(name="domicilio")
	protected String domicilio;
	@Column(name="username" , unique=true)
	private String username;
	@Column(name="password")
	private String password;
	
	/*@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="id")
	protected Login login;*/
	
	public Usuario() {
		super();
	}
	
	public boolean login(String nombreUsuario, String password) {
		return (getUsername() == nombreUsuario && getPassword() == password);
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

	public void setDomicilio(String domicilio) throws ApiException, InterruptedException, IOException {
		this.domicilio = domicilio;
		this.addCoordenadas(GeoCodingService.getCoordinates(domicilio));
	}
	
	public void setDomicilio2(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}