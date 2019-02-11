import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Ejercicios_02_11_II extends Thread {

	// Filosofo

	private int numPhil;
	private int leftFork;
	private int rightFork;
	private Semaphore[] semFork;

	private static void sleep(int bound) {
		try {
			Thread.sleep(new Random().nextInt(bound));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Ejercicios_02_11_II(String name, int numPhil, Semaphore[] semFork) {
		super(name);
		this.numPhil = numPhil;
		this.semFork = semFork;
		this.leftFork = numPhil;
		this.rightFork = (numPhil + 1) % semFork.length; // cola circular
	}

	public void eat() {
		try {
			semFork[leftFork].acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			semFork[rightFork].acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Filosofo: " + numPhil + " comiendo");
		sleep(1500);
		System.out.println("Filosofo: " + numPhil + " ha terminado de comer");
		semFork[rightFork].release();
		semFork[leftFork].release();
	}

	public void think() {
		System.out.println("Filosofo: " + numPhil + " pensando");
		sleep(2000);
		System.out.println("Filosofo: " + numPhil + " ha terminado de pensar");
	}

	@Override
	public void run() {
		while (true) {
			eat();
			think();
		}
	}

	//// MAIN

	private static final int NUMFILOSOFOS = 5;

	public static void main(String[] args) throws InterruptedException {
		Semaphore[] semForks = new Semaphore[NUMFILOSOFOS]; // null pointer
															// hay que inicializar cada uno de los semaforos
		for (int i = 0; i < NUMFILOSOFOS; i++) {
			semForks[i] = new Semaphore(1);
		}

		List<Thread> ths = new ArrayList<>();

		for (int i = 0; i < NUMFILOSOFOS; i++) {
			Ejercicios_02_11_II ph = new Ejercicios_02_11_II("Philo" + i, i, semForks);
			ths.add(ph);
			ph.start();
		}

		for (Thread th : ths) {
			th.join();
		}

	}

}
