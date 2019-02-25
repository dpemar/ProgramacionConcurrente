import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicios_02_13_III {

//	  Similar a un semaforo, pero no hay que estar pendiente del numero de permisos
//	  que le das, ni el numero de release()

	private static int x;
	private static Lock xLock;

	public static void inc() {
		xLock.lock();
		x = x + 1;
		xLock.unlock();
	}

	public static void main(String[] args) {

		xLock = new ReentrantLock(); // Casi siempre esta implementacion, viene de una interfaz
		List<Thread> ths = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			ths.add(new Thread(() -> inc()));
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
