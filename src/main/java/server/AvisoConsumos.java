package server;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Timer;
import Utils.ConsumoHogar;
import dispositivos.DispositivoInteligente;
import dispositivos.Modo;
import models.ModelHelper;

public class AvisoConsumos extends TimerTask { 
	

	public AvisoConsumos(String msg) {
		super();
	}
	
	public void start() {
		
		Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(this , 0, 18000000);//cada 5 hora...3600000 es 1 hora
        System.out.println("Timer Task de consumo comenzado");
	}
	
		
	public void run() {
    	
		System.out.println("******CORRO TIMER *******");
        ModelHelper model = new ModelHelper();
        
        //fecha de consumo en la ultima media hora
        LocalDateTime fecha_inicial = LocalDateTime.now().plusMinutes(-30);
        LocalDateTime fecha_final = LocalDateTime.now();
		
        //rangos de valor de consumo
        int rango_max= 20;
        int rango_min = 0;
        
     
		List<DispositivoInteligente> dispositivos = model.buscarTodos(DispositivoInteligente.class);
		
		//filtro los que esten actviados y no apagados
		Stream<DispositivoInteligente> dispFilt = dispositivos.stream().filter( d -> d.estaEncendido() && d.isActivado() );
		List<DispositivoInteligente> dispositivosTotal = dispFilt.collect(Collectors.toList());
		
		
		for (DispositivoInteligente element : dispositivosTotal) {

			int randomNum = ThreadLocalRandom.current().nextInt(rango_min, rango_max + 1);
			
			//creo consumo
			element.avisoConsumo(fecha_inicial, fecha_final, randomNum);

			System.out.println("*********random:***************"+ randomNum);
			System.out.println("****dispo id:******" + element.id);
			
			model.modificar(element);
		}

    }

}
