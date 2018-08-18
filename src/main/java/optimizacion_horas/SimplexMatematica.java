package optimizacion_horas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import dispositivos.DispositivoUsuario;

public interface SimplexMatematica {
	default double[] crearVectorDeUnos(int n) {
		double[] x=new double[n];
		for (int i = 0; i < n; i++) {
			x[i]=1;
		}
		return x;
	}
	default double[][] crearMatrizDeCeros(int filas , int columnas) {
		double[][] matrizIdentidadDeSimplex=new double[filas][columnas];
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				matrizIdentidadDeSimplex[fila][columna]=(double)0;
			}
		}
		return matrizIdentidadDeSimplex;
	}
	default double[][] obtenerMatrizIdentidadDeSimplex(ArrayList<DispositivoUsuario> dispositivos) {
		int filas=dispositivos.size()*2,columnas=dispositivos.size();
		double[][] matrizIdentidadDeSimplex=this.crearMatrizDeCeros(filas,columnas);
		int fila=0;
		for (int columna = 0; columna < columnas ;) {
			matrizIdentidadDeSimplex[fila][columna]=(double)1;
			fila++;
			matrizIdentidadDeSimplex[fila][columna]=(double)1;
			fila++;
			columna++;
		}
		return matrizIdentidadDeSimplex;
	}
	default double[] obtenerMatrizBDeSimplex(ArrayList<DispositivoUsuario> dispositivos){
		int filas=dispositivos.size()*2;
		double[]  matrizB = new double[filas]; 
		int fila=0;
		for (int columna = 0; columna < dispositivos.size() ;columna++) {
			matrizB[fila]=dispositivos.get(columna).getHsMensualMaximo();
			fila++;
			matrizB[fila]=dispositivos.get(columna).getHsMensualMinimo();
			fila++;
			
		}
		return matrizB;
	}
	default double[] obtenerCoeficientesDeFuncionObjetivo(ArrayList<DispositivoUsuario> dispositivos) {
		double[] consumos = new double[dispositivos.size()];
		for (int i = 0; i < consumos.length; i++) {
			
		}
		return  consumos;
	}
	
}
