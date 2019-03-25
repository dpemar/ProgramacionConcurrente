/**
 * 
 */
package HojaEjercicios2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicio4ArrayIndice {

	private static final int SIZE = 10;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		int [] array = generateArray(SIZE); // Si no uso un array de prueba
		List<Future<Integer>> list = new ArrayList<>();
		Future<Integer> numero = null;
		int[] arrayPrueba = {6,1,5,7,4,8,6,8,8,10} ;
		List<Integer> listaSalida = new ArrayList<>();
		
		for (int i = 0; i < arrayPrueba.length; i++) {
			final int finalI = i;
			numero = pool.submit(() -> contarIndice(arrayPrueba , finalI) );
			
			list.add(numero);
		}
		
		for (Future<Integer> f : list) {
			int numeroSalida = f.get();
			listaSalida.add(numeroSalida);
		}
		
		for (Integer var : listaSalida) {
			System.out.print(var != 0 ? var + " " : "");
		}
		
		pool.shutdown();
	}
	
	public static int contarIndice (int[] numArray, int posicion) {
		int numero = 0;
		if (posicion == numArray[posicion]) {
			numero = numArray[posicion];
		}
		return numero;
	}
	
	public static int[] generateArray (int size) {
		int[] array = new int[size];
		Random rnd = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = rnd.nextInt(size);
		}
		return array;
	}
	
}
