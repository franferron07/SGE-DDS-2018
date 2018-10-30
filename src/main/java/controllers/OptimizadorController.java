package controllers;

import dispositivos.DispositivoUsuario;
import models.ModelHelper;
import optimizacion_horas.Optimizador;
import optimizacion_horas.ResultadoHora;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OptimizadorController {
    Cliente cliente_seleccionado=new Cliente();
    ArrayList<DispositivoUsuario> dispositivos_seleccionados=new ArrayList<DispositivoUsuario>();
    ArrayList<ResultadoHora> resultados=new ArrayList<ResultadoHora>();
    ModelHelper modelHelper =new ModelHelper();


    private Cliente buscarClientePorID(int id_usuario){
        Cliente user = this.modelHelper.buscar(Cliente.class,id_usuario);
        return user;
    }
    public ModelAndView mostrarResultadosOptimizador(Request request, Response res ){
        Map<String, Object> model=new HashMap<>();

        //Integer user_id=Integer.parseInt(request.queryParams("id"));
        this.setearValoresAUsuario();
        modelHelper.agregar(cliente_seleccionado);
        cliente_seleccionado = this.buscarClientePorID(cliente_seleccionado.getId());


        Optimizador optimizador = new Optimizador();
        optimizador.cargarDispositivosEsenciales(cliente_seleccionado);
        optimizador.maximizar();
        resultados = optimizador.resultados();
        Boolean se_paso_de_consumo=optimizador.consumioSuMaximaEnergia();


        model.put("user_name",cliente_seleccionado.getNombre());
        model.put("user_id",cliente_seleccionado.getId());
        model.put("resultado_horas",resultados);
        model.put("se_paso_de_consumo",se_paso_de_consumo);
        return new ModelAndView(model, "optimizador_resultados.hbs");
    }

    private void setearValoresAUsuario(){


        cliente_seleccionado.setNombre("Daniel");
        DispositivoUsuario lcd,lavaropas,ventilador,heladera;
        lcd=new DispositivoUsuario() {

            @Override
            public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}
            @Override
            public double getConsumoKwHora() {	return 0.18;
            }
            @Override
            public String getIdentificacion() {	return "lcd";}
            @Override
            public double getHsMensualMinimo() {	return 90;}
            @Override
            public double getHsMensualMaximo() {	return 370;}
            @Override
            public boolean esEsencial() {return true;}
            @Override
            public boolean esInteligente() {
                // TODO Auto-generated method stub
                return false;
            }
            @Override
            public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
                // TODO Auto-generated method stub
                return 0;
            }
        };
        lavaropas=new DispositivoUsuario() {
            @Override
            public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}

            @Override
            public double getConsumoKwHora() {	return 0.875;}
            @Override
            public String getIdentificacion() {	return "lavarropas";}
            @Override
            public double getHsMensualMinimo() {	return 6;}
            @Override
            public double getHsMensualMaximo() {	return 30;}

            @Override
            public boolean esEsencial() {return true;}

            @Override
            public boolean esInteligente() {
                return false;
            }

            @Override
            public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
                // TODO Auto-generated method stub
                return 0;
            }
        };
        ventilador=new DispositivoUsuario() {
            @Override
            public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}
            @Override
            public double getConsumoKwHora() {	return 0.06;}
            @Override
            public String getIdentificacion() {	return "ventilador";}
            @Override
            public double getHsMensualMinimo() {	return 120;}
            @Override
            public double getHsMensualMaximo() {	return 360;}
            @Override
            public boolean esEsencial() {return true;}
            @Override
            public boolean esInteligente() {
                return false;
            }
            @Override
            public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
                // TODO Auto-generated method stub
                return 0;
            }
        };
        heladera=new DispositivoUsuario() {
            @Override
            public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {	return 0;}
            @Override
            public double getConsumoKwHora() {	return 0;}
            @Override
            public String getIdentificacion() {	return "heladera";}
            @Override
            public double getHsMensualMinimo() {	return 0;}
            @Override
            public double getHsMensualMaximo() {	return 0;}
            @Override
            public boolean esEsencial() {return true;}
            @Override
            public boolean esInteligente() {
                return false;
            }
            @Override
            public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
                // TODO Auto-generated method stub
                return 0;
            }
        };

        cliente_seleccionado.agregarDispositivo(lcd);
        cliente_seleccionado.agregarDispositivo(lavaropas);
        cliente_seleccionado.agregarDispositivo(ventilador);
        cliente_seleccionado.agregarDispositivo(heladera);

        cliente_seleccionado.id=1;
    }
}
