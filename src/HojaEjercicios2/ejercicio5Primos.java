/**
 * 
 */
package HojaEjercicios2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicio5Primos {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		int[] arrayPrimos = {6,1,5,7,4,8,6,8,8,10,7,11} ;
		int contador = 0;
		List<Future<Boolean>> lista = new ArrayList<>();
		boolean aux = false;
		for (int i = 0; i < arrayPrimos.length; i++) {
			final int finalI = i;
			Future<Boolean> salida = pool.submit(() -> esPrimo(arrayPrimos[finalI]));
		
			lista.add(salida);
		}
		
		for (Future<Boolean> numero : lista) {
			aux = numero.get();
			if (aux == true) {
				contador++;
			}
		}
		
		System.out.println(contador);
		pool.shutdown();
	}
	
	public static boolean esPrimo(int n) {
	    for(int i=2;i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
}
