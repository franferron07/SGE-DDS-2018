package dispositivos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="dispositivosAdaptadorLog")
public class AdaptadorLog {

	@Id
	@GeneratedValue
	private int id;
	
	//@Id
	@ManyToOne
	@JoinColumn( name="estandar_id" , referencedColumnName="id" )
	public DispositivoEstandar estandar;
	
	//@Id
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adaptadorInteligente_id")
	public DispositivoInteligente adaptador ;
	
	@Column(name="fechaInicio")
	public LocalDateTime fechaInicio;
	
	@Column(name="fechaFin")
	public LocalDateTime fechaFin;
	
	
}
