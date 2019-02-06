import java.util.concurrent.Semaphore;

public class Ejercicios_02_06 {

	private static Semaphore semSync;

	public static void procesoA() {
		System.out.println("PA1");
		semSync.release();
		System.out.println("PA2");

	}

	public static void procesoB() {
		System.out.println("PB1");
		try {
			semSync.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("PB2");
	}

	public static void main(String[] args) {

		semSync = new Semaphore(0); // permits

		new Thread(() -> procesoA()).start();
		new Thread(() -> procesoB()).start();

	}

}
