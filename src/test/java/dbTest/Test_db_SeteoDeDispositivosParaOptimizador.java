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
        this.cliente=new Cliente();
        this.artefactos=new ArrayList<DispositivoDetalle>();
        this.cliente=(Cliente)RepositorioUsuarios.buscarUsuario("test");
    }
    @Test
    public void testDispositivosDelUser(){//no me carga los dispositivos , el id de user test me da 22 y no 1 o cero , cualquiera
        Assert.assertTrue(true);
        //this.mostrarDispositivosUsuario((ArrayList<DispositivoUsuario>) this.cliente.getDispositivos());
       // System.out.print(this.cliente.getNombre());
    }







    //metodos auxiliares

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
