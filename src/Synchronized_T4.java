/**
 * 
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Synchronized_T4 {

	private static int x;
	private static Object xLock = new Object();
	
	// Necesito otro objeto para syncronizarlo
	// 1. No deberia sincronizar con la misma variables
	// 2. Con x no me deja porque int no es un objeto, Integer si 
	
	public static void inc() {
		synchronized (xLock) {
			x = x +1;
		}
	}
	
	// Usar el mismo objeto, porque trabaja con la misma variable
	// Si tengo 2 variables;  mejor poner 2 objetos para sincronizar
	
	public static void dec() {
		synchronized (xLock) {
			x = x -1;
		}
	}
	
	public static void main(String[] args) {
		List<Thread> ths = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			ths.add(new Thread (() -> inc()));
		}
		
		for (Thread thread : ths) {
			thread.start();
		}
		
		for (Thread thread : ths) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(x);
		
	}
	
}
