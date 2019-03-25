/**
 * 
 */
package HojaEjercicios2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class ejercicio3Vocales {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		String path = "big_file.txt" ;
		BufferedReader entrada = new BufferedReader(new FileReader(path));
		String linea = null;
		List<Future<Integer>> lista = new ArrayList<>();
		int contador = 0;
		long startTime = System.currentTimeMillis();
		
		while((linea = entrada.readLine()) != null) {
			String[] words = linea.split(" ");
			for (String w : words) {
				Future<Integer> salida = pool.submit(() -> contarVocales(w));
				lista.add(salida);
			}
		}
		
		for (Future<Integer> palabra : lista) {
			contador += palabra.get();
		}
		System.out.println(contador);
		pool.shutdown();
		entrada.close();
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("Tiempo total " + endTime / 1e3 + " segundos");
	}
	
	/*
	 * Vocales de cada palabra
	 */
	public static int contarVocales (String word) {
		StringBuilder arrayChar = new StringBuilder();
		arrayChar.append(word);
		int contador = 0;
		for (int i = 0; i < arrayChar.length(); i++) {
			char vocal = arrayChar.charAt(i);
			if ((vocal == 'a') || (vocal == 'e') || (vocal == 'i') || (vocal == 'o') || (vocal == 'u')|| 
				(vocal == 'A') || (vocal == 'E') || (vocal == 'I') || (vocal == 'O') || (vocal == 'U')){
				contador++;
			}
		}
		return contador;
	}
	
}
