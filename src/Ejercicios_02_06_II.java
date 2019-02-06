import java.util.concurrent.Semaphore;

public class Ejercicios_02_06_II {

	private static Semaphore semME; // Mutex, mutual expression

	public static void process() {
		try {
			semME.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("SC1");
		System.out.println("SC2");
		semME.release();
		System.out.println("SN3");
		System.out.println("SN4");

	}

	public static void main(String[] args) {

		semME = new Semaphore(1); // permits
		for (int i = 0; i < 5; i++) {
			new Thread(() -> process()).start();
		}

	}

}
