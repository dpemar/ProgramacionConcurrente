import java.util.Random;

public class Ejercicios_02_04 {

	// ProductoConsumidor

	private static volatile int product; // Lo protejo con volatile, porque la espera activa no es un mecanismo de
											// proteccion bueno.
	private static boolean produced; // Garantizar que siempre consultas la ultima

	/**
	 * Los while == true para que esten todo el rato haciendolo, tener los produced
	 * para controlarlo Sin los while true, lo produce y consume 1 vez
	 * 
	 * Los sleep de 1000 en java 11 se pueden cambiar por Thread.onSpinWait -> Los
	 * ordenadores de examen solo java 8
	 */

	public static void productor() {
		
		while (true) {
			while (produced) {
				sleep(1000);
			}
			
			product = new Random().nextInt(100);
			System.out.println("He producido " + product);
			sleep(1000);
			produced = true;
		}
	}

	public static void consumidor() {
		
		while (true) {
			while (!produced) {
				sleep(1000);
			}
			
			System.out.println("El productor es " + product);
			product = -1;
			produced = false;
		}
	}

	private static void sleep(int bound) {
		
		try {
			Thread.sleep(bound);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		new Thread(() -> productor()).start();
		new Thread(() -> consumidor()).start();
	}

}