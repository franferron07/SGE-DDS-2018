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
    private    Cliente cliente_seleccionado=new Cliente();
    private ArrayList<DispositivoUsuario> dispositivos_seleccionados=new ArrayList<DispositivoUsuario>();
    private ArrayList<ResultadoHora> resultados=new ArrayList<ResultadoHora>();
    private ModelHelper modelHelper =new ModelHelper();


    private Cliente buscarClientePorID(int id_usuario){
        Cliente user = this.modelHelper.buscar(Cliente.class,id_usuario);
        return user;
    }
    public ModelAndView mostrarResultadosOptimizador(Request request, Response res ){
        Map<String, Object> model=new HashMap<>();
//        Integer user_id = request.session().attribute("id");
        setearUser();
       // modelHelper.agregar(cliente_seleccionado);
      //  cliente_seleccionado = this.buscarClientePorID(cliente_seleccionado.getId());


        Optimizador optimizador = new Optimizador();
        optimizador.cargarDispositivos(this.getUnosDispositivos().get(0),this.getUnosDispositivos().get(1),this.getUnosDispositivos().get(2));
        optimizador.maximizar();
        resultados = optimizador.resultados();
        Boolean se_paso_de_consumo=optimizador.consumioSuMaximaEnergia();


        model.put("user_name",cliente_seleccionado.getNombre());
        model.put("user_id",cliente_seleccionado.getId());
        model.put("resultados",resultados);
        model.put("se_paso_de_consumo",se_paso_de_consumo);
        return new ModelAndView(model, "optimizador_resultados_v2.hbs");
    }
    private void setearUser(){
        cliente_seleccionado.setId(2);
        cliente_seleccionado.setNombre("UnNombre");
        cliente_seleccionado.setApellido("UnApellido");
        cliente_seleccionado.setDispositivos(this.getUnosDispositivos());
    }
    private ArrayList<DispositivoUsuario> getUnosDispositivos(){
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
//        lcd.detalle.setEsEsencial(true);
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
  //      lavaropas.detalle.setEsEsencial(true);
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
    //    ventilador.detalle.setEsEsencial(true);
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
/*       Optimizador simplex = new Optimizador();
       simplex.setConsumoMaximoDeEnergia(450000);
       simplex.cargarDispositivos(lcd,lavaropas,ventilador);
       simplex.maximizar();
       resultados=simplex.getHorasDeCadaDispositivo();*/
       this.dispositivos_seleccionados.add(lcd);
       this.dispositivos_seleccionados.add(lavaropas);
       this.dispositivos_seleccionados.add(ventilador);
       return this.dispositivos_seleccionados;
   }


//    @Test
//    public void horasMaximas() {
//        Assert.assertEquals(resultados.get(0).getHorasQuePuedeConsumir(),370.0,0.001);
//        Assert.assertEquals(resultados.get(1).getHorasQuePuedeConsumir(),30.0,0.001);
//        Assert.assertEquals(resultados.get(2).getHorasQuePuedeConsumir(),360.0,0.001);
//        Assert.assertEquals(resultados.get(0).getDispositivo(),"lcd");
//    }
}