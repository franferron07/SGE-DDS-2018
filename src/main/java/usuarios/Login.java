package usuarios;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="login")
public class Login {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="id")
	private Usuario usuario;

	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;

	public Login() {

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