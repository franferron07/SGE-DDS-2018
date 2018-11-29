package dbTest;

import dispositivos.DispositivoDetalle;
import dispositivos.DispositivoUsuario;

import models.ModelHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repositorios.RepositorioDispositivosLista;
import repositorios.RepositorioUsuarios;
import usuarios.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test_db_SeteoDeDispositivosParaOptimizador {
   // private ModelHelper model;
    private Cliente cliente ;
    private ArrayList<DispositivoDetalle> artefactos;
    @Before
    public void init() {
    //    this.model = new ModelHelper();
        this.cliente= new Cliente(){
            @Override
            public String toString() {
                return this.nombre+", "+this.apellido;
            }
        };
        RepositorioUsuarios.cargarUsuarios();
        this.cliente  =(Cliente) RepositorioUsuarios.buscarUsuario(1);
        this.artefactos=new ArrayList<DispositivoDetalle>();
        this.artefactos.addAll( RepositorioDispositivosLista.jsonToArrayList("dispositivosLista_v2.json"));
        RepositorioDispositivosLista.dispositivosLista.addAll(RepositorioDispositivosLista.jsonToArrayList("dispositivosLista_v2.json"));

        this.cargaDeDispositivosDetalleAUser((ArrayList<DispositivoDetalle>) RepositorioDispositivosLista.getDispositivosLista(),cliente);
    }
    @Test
    public void testEnCantidadDeDispositivosEnElRepositorio(){
        Assert.assertTrue(artefactos.size()==RepositorioDispositivosLista.getDispositivosLista().size());
        this.mostrarDispositivosDetalle((ArrayList<DispositivoDetalle>) RepositorioDispositivosLista.getDispositivosLista());
    }
    @Test
    public void testDispositivosDelUser(){
        //Assert.assertTrue(this.cliente.getDispositivos().size()==this.artefactos.size());
        Assert.assertTrue(this.cliente.getDispositivos().size()==7);//dos ya estaban cargados
        System.out.println(this.cliente.getNombre()+" y cant de disps : "+this.cliente.getDispositivos().size());
        for (int i = 0; i < this.artefactos.size(); i++) {
         //   Assert.assertTrue(this.cliente.getDispositivos().get(i).detalle==this.artefactos.get(i));
            System.out.println(this.cliente.getDispositivos().get(i));
        }

    }
    @Test
    public void cargaDeDispositivosToDB(){
        for (int i = 0; i < this.artefactos.size(); i++) {
     //       model.agregar(artefactos.get(i));
        }
        //RepositorioDispositivosLista.cargarDispositiosLista();

        Assert.assertTrue(this.artefactos.size()==RepositorioDispositivosLista.getDispositivosLista().size());
        mostrarDispositivosDetalle((ArrayList<DispositivoDetalle>) RepositorioDispositivosLista.getDispositivosLista());
    }
    public void cargaDeDispositivosDetalleAUser(ArrayList<DispositivoDetalle> lista_, Cliente cliente_){//agregando a un cliente
        for (int i = 0; i < lista_.size(); i++) {
            DispositivoUsuario unDispositivoDelCliente = new DispositivoUsuario() {
                @Override
                public float consumoPeriodo(LocalDateTime desde, LocalDateTime hasta) {
                    return 0;
                }

                @Override
                public boolean esInteligente() {
                    return true;
                }

                @Override
                public double horasDeUso(LocalDateTime desde, LocalDateTime hasta) {
                    return 0;
                }

                @Override
                public String toString() {
                    return "nombre: "+this.getDetalle().getNombre()+" ,descripcion: "+this.getDetalle().getDescripcion()+" ,consumo: "+this.getConsumoKwHora()+" , kwMinimo: "+this.getHsMensualMinimo()+" y kWMaximo:  "+this.getHsMensualMaximo();
                }
            };
            unDispositivoDelCliente.setDispositivoLista(lista_.get(i));
            cliente_.agregarDispositivo(unDispositivoDelCliente);
//            model.agregar(artefactos.get(i));
        }
//        model.agregar(user);
    }
    public void agregarDispositivosDetalleA_BD(ArrayList<DispositivoDetalle> lista){
        for (int i = 0; i < lista.size(); i++) {
                 //  model.agregar(lista.get(i));
        }

    }
    void mostrarDispositivosDetalle(ArrayList<DispositivoDetalle> dispositivos_){
        for(int i = 0;i<dispositivos_.size();i++){
            System.out.println("ID : "+dispositivos_.get(i).getId()+" ,"+"Descripcion : "+dispositivos_.get(i).getDescripcion());
        }
    }
    void mostrarDispositivosUsuario(ArrayList<DispositivoUsuario> dispositivos_){
        for(int i = 0;i<dispositivos_.size();i++){
            //    System.out.println("ID : "+dispositivos_.get(i).getDetalle().getId()+" ,"+"Descripcion : "+dispositivos_.get(i).getDetalle().getDescripcion());
            System.out.println(dispositivos_);
        }
    }
}
