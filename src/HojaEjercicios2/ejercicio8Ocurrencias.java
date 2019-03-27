/**
 * 
 */
package HojaEjercicios2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class ejercicio8Ocurrencias {

	private static final int SIZE = 10;
	public static final String[] arrayStrings = {"Hola" , "The" , "all" , "el",  "resto" , "no" , "salen" , "son" , "en" , "español" } ;
	
	public static int contarPalabras (String word, BufferedReader file) throws IOException {
		int contador = 0;
		String linea = null;
		while((linea = file.readLine()) != null) {
			String[] words = linea.split(" ");
			for (String w : words) {
				if (w.equals(word)) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		String path = "big_file.txt" ;
		Map<String,Future<Integer>> mapa = new HashMap<>();
		BufferedReader file = new BufferedReader(new FileReader(path));
		
		for (int i = 0; i < SIZE; i++) {
			final int finalI = i;
			Future<Integer> aux = pool.submit(() -> contarPalabras(arrayStrings[finalI], file));
			mapa.put(arrayStrings[finalI], aux);
		}
		
		for (String map2 : mapa.keySet()) {
			String key = map2;
			Integer value = mapa.get(map2).get();
			System.out.println("Key: " + key + " ->  Word: " + value);
		}
		
		pool.shutdown();
	}
	
}
