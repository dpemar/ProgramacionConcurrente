/**
 * 
 */
package HojaEjercicios2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */

@SuppressWarnings("serial")
public class ejercicio7Maximo extends RecursiveTask<Integer> {
	
	/*
	 * Para fork join necesito un constructor y heredar de RecursiveTask o RecursiveAction (Si devuelve void) 
	 */
	
	private static final int ELEM = 5;
	private static final int SIZE = 10;
	
	private int start ;
	private int end ;
	private int[] array ;
	
	public ejercicio7Maximo(int start, int end, int[] array) {
		this.start = start;
		this.end = end;
		this.array = array;
	}

	public static int[] generateRandomArray (int size) {
		int[] aux = new int[size];
		Random rdn = new Random();
		for (int i = 0; i < aux.length; i++) {
			aux[i] = rdn.nextInt(size);
		}
		return aux;
	}

	/*
	 * Logica -> Caso base y caso recursivo
	 */
	@Override
	protected Integer compute() {
		if ((end - start) < ELEM) {
			int max = Integer.MIN_VALUE; 
			for (int i = 0; i < array.length; i++) {
				max = Math.max(max, array[i]);
			}
			return max;
		} else {
			int mitad = (end+start) / 2;
			
			ejercicio7Maximo izq = new ejercicio7Maximo(start, mitad, array);
			ejercicio7Maximo der = new ejercicio7Maximo(mitad, end, array);
			
			/* OJO AL ORDEN AQUI -> Fork, compute, join , con 2 aux
			 * 
			 * Fork crea nueva tarea para el subprograma
			 * Compute realiza la tarea
			 * Join espera a finalizar, recupera cada subtarea y combina el resultado
			 */
			
			izq.fork();
			int aux = der.compute();
			
			int salida = izq.join();
			
			int max = Math.max(aux, salida);
			return max;
		}
	}
	
	public static void main(String[] args) {
		int[] arrayMain = generateRandomArray(SIZE);
		// Jorkjoin con un pool especial
		ForkJoinPool pool = new ForkJoinPool();
		int m = pool.invoke(new ejercicio7Maximo(0, arrayMain.length, arrayMain));
		
		System.out.println(Arrays.toString(arrayMain));
		System.out.println("Max: " + m);
		
		pool.shutdown();
	}
	
}
