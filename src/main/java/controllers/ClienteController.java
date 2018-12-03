package controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dispositivos.Consumo;
import models.ModelHelper;
import reglasYActuadores.Regla;
import repositorios.RepositorioRegla;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

public class ClienteController {
	
	public ClienteController(){
		
	}
	
	public ModelAndView ver(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		int id = request.session().attribute("id");
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("cliente", cliente);
		return new ModelAndView(model, "cliente.hbs");
	}

	public ModelAndView estado(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();
		
		int id = request.session().attribute("id");
		
		
		//Query DB for consumo
		String queryConsumo = "SELECT c.* from usuario u "+
					"join dispositivo d on d.cliente_id=u.id "+
					"join DispositivoInteligente di on di.id =d.id "+
					"join modo m on m.dispositivo_inteligente_id=di.id "+
					"join consumo c on c.modo_id=m.id "+
					"where u.id="+Integer.toString(id)+
					" limit 10";
		
		javax.persistence.Query q = ModelHelper.getEntityManager().createNativeQuery(queryConsumo,Consumo.class);
		List<Consumo> consumos = q.getResultList();
		model.put("consumos", consumos);
		
		//Query DB for dispositivos
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		model.put("dispositivos", cliente.getDispositivos());
		/*
		//Query DB for reglas
		String queryReglas = "SELECT r.* from usuario u "+
							"join dispositivo d on d.cliente_id=u.id "+
							"join DispositivoInteligente di on di.id=d.id "+
							"join regla_DispositivoInteligente rd on rd.dispositivos_id=di.id "+
							"join regla r on r.id=rd.regla_id "+
							"where u.id="+Integer.toString(id);
				
		javax.persistence.Query q2 = ModelHelper.getEntityManager().createNativeQuery(queryReglas,Regla.class);
		List<Regla> reglas = q2.getResultList();*/
		
		List<Regla> reglas2 = RepositorioRegla.filtrarReglsaCliente(id);
		
		model.put("reglas", reglas2);
		return new ModelAndView(model, "dashboard_hogar.hbs");
	}
	
	public ModelAndView consumoPeriodo(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();

		int id = request.session().attribute("id");


		return new ModelAndView(model, "consumo.hbs");
	}
	
	
	public ModelAndView consumoPeriodoResultado(Request request, Response response) {
		Map<String, Object> model=new HashMap<>();

		int id = request.session().attribute("id");
		
		String desde = request.queryParams("desde");
		String hasta = request.queryParams("hasta");
		
		Cliente cliente = (Cliente) RepositorioUsuarios.buscarUsuario(id);
		
		LocalDateTime fecha_desde = parsFecha(desde);
		LocalDateTime fecha_hasta = parsFecha(hasta);
		
		/* Calculo el consumo */
		double total_consumo = cliente.consumoEnUnPeriodo(fecha_desde, fecha_hasta  );
		
		//model.put("consumos", consumos );
		
		NumberFormat formatter = new DecimalFormat("#0.00");     
		System.out.println(formatter.format(total_consumo));
		model.put("total_consumo", formatter.format(total_consumo));
		return new ModelAndView(model, "resultadoConsumo.hbs");
	}
	
	
	
	public LocalDateTime parsFecha( String fecha ){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return LocalDateTime.parse(fecha, formatter);

		
		
		
	}
	
	
	
	
	
	

}
