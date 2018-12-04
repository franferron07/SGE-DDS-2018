package controllers;

import dao.DaoJson;
import dispositivos.DispositivoUsuario;
import models.ModelHelper;
import optimizacion_horas.Optimizador;
import optimizacion_horas.ResultadoHora;
import repositorios.RepositorioDispositivosLista;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;
import usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OptimizadorController {
    private    Cliente cliente_seleccionado;
    private ArrayList<ResultadoHora> resultados;
    private ModelHelper modelHelper =new ModelHelper();
    private Optimizador optimizador ;

    private Cliente buscarClientePorID(int id_usuario){
        Cliente user = this.modelHelper.buscar(Cliente.class,id_usuario);
        return user;
    }

    public ModelAndView mostrarResultadosOptimizador(Request request, Response res ){
    	
    	optimizador = new Optimizador();
    	resultados = new ArrayList<ResultadoHora>();
        
    	
        Map<String, Object> model=new HashMap<>();
        int idUserLoggeado = request.session().attribute("id");
        this.cliente_seleccionado=(Cliente)RepositorioUsuarios.buscarUsuario(idUserLoggeado);


        /*Cliente unCliente = new Cliente(){
            @Override
            public int getId() {
                return 9999999;
            }
        };*/
        //RepositorioDispositivosLista.cargaDeDispositivosDetalleAUser(RepositorioDispositivosLista.jsonToArrayList("dispositivosLista_v2.json"),unCliente);
        //optimizador.cargarDispositivos(unCliente);
        optimizador.cargarDispositivos(this.cliente_seleccionado);
        optimizador.setConsumoMaximoDeEnergia(450000);//para saber si se paso de consumo
        optimizador.maximizar();
        resultados = optimizador.resultados();
        Boolean se_paso_de_consumo=optimizador.consumioSuMaximaEnergia();

        /*model.put("user_name",cliente_seleccionado.getNombre());
        model.put("user_id",cliente_seleccionado.getId());*/
        model.put("resultados",resultados);
        // model.put("dispositivo_id",)
        model.put("se_paso_de_cosumo",se_paso_de_consumo);

       // System.out.println("---------SI APARECE [] ENTONCES EL USER CON id: "+ this.cliente_seleccionado.getId()+" NO TIENE DISPOSITIVOS");
        // System.out.println(resultados);

        return new ModelAndView(model, "optimizador_resultados_v2.hbs");
    }

}