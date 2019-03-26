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
public class ejercicio6Longitud {

	/*
	 * Dado un array de cadenas de caracteres, implementa un programa concurrente
	 * que almacene la longitud de cada cadena en otro array, filtrando las cadenas vacías.
	 * Por ejemplo:
	 * 	
	 * 		• [ "", "", "cse", "rox", "", "homework", "", "7", "" ]
	 * 		• [ 3, 3, 8, 1]
	 * 
	 */
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Future<Integer>> lista = new ArrayList<>();
		String[] arrayPrueba = { "", "", "cse", "rox", "", "homework", "", "7", ""};
		Future<Integer> salida = null;
		int contador = 0;
		List<Integer> listaContador = new ArrayList<>();
		
		for (int i = 0; i < arrayPrueba.length; i++) {
			final int finalI = i;
			salida = pool.submit(() -> contarCadenas(arrayPrueba[finalI]));
			
			lista.add(salida);
		}
		
		for (Future<Integer> c : lista) {
			contador = c.get();
			listaContador.add(contador);
		}
		
		for (Integer var : listaContador) {
			System.out.print(var != 0 ? var + " " : "");
		}
		
		pool.shutdown();
	}
	
	public static int contarCadenas (String s) {
		char[] letras = s.toCharArray();
		return letras.length;
	}
	
}
