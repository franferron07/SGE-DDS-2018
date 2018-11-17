package dispositivos;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("apagado")
public class ModoApagado extends Modo {

	
	
	
	public ModoApagado(){
		
	}
	
	public ModoApagado(DispositivoInteligente di) {
		super(di);
	}

	@Override
	public float consumoEnPeriodo( LocalDateTime fechaInicial , LocalDateTime fechaFinal ){
    
    	return 0;
    } 
	
	@Override
	public boolean cumpleIntervalo(LocalDateTime desde, LocalDateTime hasta) {

		return false;
	}
	 
	@Override
	public boolean encendido() {
		return false;
	}

	@Override
    public void apagarse(DispositivoInteligente disp) {	
        
	}
	
	public String toString() {
		return "Modo Apagado";
	}

	
	@Override
	public void registrarConsumo(LocalDateTime inicio, LocalDateTime fin, float consumo) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	

}
