import java.util.Random;
import java.util.concurrent.Semaphore;

public class Ejercicios_02_11_III extends Thread {

	// Escritores lectores

	private static Semaphore emResource;

	private static void sleep(int bound) {
		try {
			Thread.sleep(new Random().nextInt(bound));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void writer() {
		try {
			emResource.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Escritor escribiendo");
		sleep(1000);
		emResource.release();
	}

	public static void reader() {
		try {
			emResource.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Lector leyendo");
		sleep(1000);
		emResource.release();
	}

	//// MAIN
	public static void main(String[] args) throws InterruptedException {
		emResource = new Semaphore(1);
	}

}
