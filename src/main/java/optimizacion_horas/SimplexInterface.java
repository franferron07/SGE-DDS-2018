package optimizacion_horas;

import java.util.ArrayList;

import dispositivos.DispositivoEstandar;
import dispositivos.DispositivoInteligente;
import dispositivos.DispositivoUsuario;
import usuarios.Cliente;
import usuarios.Usuario;

public interface SimplexInterface {
	void cargarDispositivosEsenciales(Cliente cliente);
//	void cargarDispositivos_porArray(DispositivoUsuario[] dispositivos);
	void cargarDispositivos(DispositivoUsuario ... dispositivoUsuarios_ );
	
//	void cargarRestricciones(SimplexModeloRestriccion restricciones);
//	
	void maximizar();
	void minimizar();
	

	
	public ArrayList<ResultadoHora> getHorasDeCadaDispositivo();


}
