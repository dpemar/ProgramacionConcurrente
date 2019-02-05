/**
 * 
 */


import java.util.Random;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */

public class ClienteServidor {
	
	/*
	 * Diferencia con el anterior es que el cliente le dice al servidor cuando le tiene que servir
	 */
	private static boolean peticion;
	private static boolean realizada;
	private static volatile int product;
	
	private static void sleep(int bound) {
		try {
			Thread.sleep(bound);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void cliente () {
		while (true){
			while (realizada) {
				sleep(5000);
			}
			System.out.println("Sirveme un numero ");
			sleep(2000);
			peticion = true;
			realizada = false;
		}
	}
	
	public static void servidor () {
		while (true){
			while (peticion) {
				sleep(5000);
			}
			product = new Random().nextInt(100);
			System.out.println("Sirvo el numero: " + product );
			sleep(2000);
			realizada = true;
			peticion = false;
		}
	}
	
	/*
	 * 	Variable nueva booleana que dice si hay peticion o no
	 *  Cliente pone peticion a true, se duerme y esperar hasta que este "realizada" otra boole
	 *  Con una peticion el servidor marca al final que ya no hay más peticiones
	 *  Servidor peticion a false
	 */
	
	public static void main(String[] args) {
		peticion = false;
		realizada = false;
		
		new Thread(()-> cliente()).start();
		new Thread (() -> servidor()).start();
	}
	
	// Para N peticiones solo con while true 
}
