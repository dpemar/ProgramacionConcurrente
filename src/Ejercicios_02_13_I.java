import java.util.concurrent.Semaphore;

public class Ejercicios_02_13_I {

	private static Semaphore emResource;
	private static Semaphore semReader; // Exclusion mutua de semaforo
	private static int readerCount; // Cuantos lectores activos

	// Con que haya un lector dentro, no entra un escritor de nuevo

	private static void sleep(int bound) {
		try {
			Thread.sleep(bound);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void writter() {
		while (true) {
			try {
				emResource.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Escritor escribiendo");
			sleep(1000);
			emResource.release();
		}
	}

	public static void reader() {
		while (true) {
			try {
				semReader.acquire();
				readerCount++;
				if (readerCount == 1) { // Exclusion mutua, si soy el primero lo pido
					emResource.acquire();
				}
				semReader.release();
				System.out.println("Lector leyendo");
				sleep(1000);
				semReader.acquire();
				readerCount--;
				if (readerCount == 0) {
					emResource.release(); // No suelta el recurso hasta que paren todos los lectores y siempe hay
				}
				semReader.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		emResource = new Semaphore(1);
		semReader = new Semaphore(1);
		readerCount = 0;

		for (int i = 0; i < 10; i++) {
			new Thread(() -> reader()).start();
		}

		for (int i = 0; i < 3; i++) {
			new Thread(() -> writter()).start();
		}

		// Con bucle while infinito no entra nunca el escritor, en el "Bien" si
	}
}