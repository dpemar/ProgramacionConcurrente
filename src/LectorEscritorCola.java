/**
 * 
 */


import java.util.concurrent.Semaphore;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class LectorEscritorCola {

	private static Semaphore emResource;
	private static Semaphore semReader; // Exclusion mutua de semaforo
	private static int readerCount; // Cuantos lectores activos
	private static Semaphore queueSem; // Semaforo de cola para resolver el problema
	
	// Tanto de escritores como de lectores. Asegura la entrada del escritor, usa un semaforo como "cola"
	
	private static void sleep(int bound) {
		try {
			Thread.sleep(bound);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void writter () {
		while (true) {
			try {
				queueSem.acquire();
				emResource.acquire();
				queueSem.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Escritor escribiendo");
			sleep(1000);
			emResource.release();
		}
	}

	public static void reader() {
		while(true) {
			try {
				queueSem.acquire();
				semReader.acquire();
				readerCount++;
				if (readerCount == 1) { // Exclusion mutua, si soy el primero lo pido
					emResource.acquire();
				}
				queueSem.release();
				semReader.release();
				System.out.println("Lector leyendo");
				sleep(1000);
				semReader.acquire();
				readerCount--;
				if (readerCount == 0) {
					emResource.release();
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
		queueSem = new Semaphore(1); // Exclusion mutua permitidos 1
		readerCount = 0;
		
		for (int i = 0; i < 10; i++) {
			new Thread(() -> reader()).start();
		}
		
		for (int i = 0; i < 3; i++) {
			new Thread(() -> writter()).start();
		}
		
		// Con este cola, se le da prioridad al escritor
	}
	
}
