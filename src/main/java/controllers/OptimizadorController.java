package controllers;

import dispositivos.DispositivoUsuario;
import models.ModelHelper;
import optimizacion_horas.Optimizador;
import optimizacion_horas.ResultadoHora;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

import java.util.ArrayList;

public class OptimizadorController {

    ModelHelper modelHelper =new ModelHelper();
    public ModelAndView mostrarHorasDeUsuariosSimplexController(Request request, Response response) {
        String probando = "probando";
        int id_user=1;//esto debe ser leido desde un textbox
        Cliente user_seleccionado = this.buscarClientePorID(id_user);

        //List<DispositivoUsuario> dispositivos_seleccionados = user_seleccionado.getDispositivos();
        Optimizador optimizador = new Optimizador();
        optimizador.cargarDispositivosEsenciales(user_seleccionado);
        optimizador.maximizar();
        ArrayList<ResultadoHora> resultados = optimizador.resultados();
        Boolean se_paso_de_consumo=optimizador.consumioSuMaximaEnergia();
        return new ModelAndView(null,"optimizador_usuario.hbs");
    }
    //public ModelAndView mostrarUsuariosController(Request req, Response res){
     //   return new ModelAndView(null,"mostrar_usuarios.hbs");
   // }
    private Cliente buscarClientePorID(int id_usuario){
         Cliente user = this.modelHelper.buscar(Cliente.class,id_usuario);
        return user;
    }

}
