import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Ejercicios_02_11_I {

	// Buffer; dividirlo en clase buffer y el resto
	
	// Productores / Consumidores

	private static final int BUFFER_SIZE = 10;
	private int[] datos;
	private int posInsert;
	private int posExtract;

	private Semaphore emPosInsert;
	private Semaphore emPosExtract;
	private Semaphore nHuecos;
	private Semaphore nProds;

	public Ejercicios_02_11_I() { // buffer
		this.datos = new int[BUFFER_SIZE];
		Arrays.fill(datos, -1); // (datos, val:-1)
		posInsert = 0;
		posExtract = 0;
		emPosExtract = new Semaphore(1); //Exclusion mutua siempre a 1 permits:1
		emPosInsert = new Semaphore(1); //Exclusion mutua siempre a 1 permits:1
		nHuecos = new Semaphore(BUFFER_SIZE);
		nProds = new Semaphore(0); //permits:0
	}

	public void insert(int val) {

		try {
			nHuecos.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			emPosInsert.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		datos[posInsert] = val;
		// posInsert++; //problema: si sumo de 1 en 1 me salgo del array
		posInsert = (posInsert + 1) % BUFFER_SIZE;
		
		emPosInsert.release();
		nProds.release();
	}

	public int extract() {

		try {
			nProds.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			emPosExtract.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int dato = datos[posExtract];
		datos[posExtract] = -1;
		posExtract = (posExtract + 1) % BUFFER_SIZE;
		
		emPosExtract.release();
		nHuecos.release();

		return dato;
	}

	///////////////////////
	private static final int NPROD = 10;
	private static final int NCONS = 3;
	private static Ejercicios_02_11_I buffer;

	private static void sleep(int bound) {
		try {
			Thread.sleep(new Random().nextInt(bound));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void producer(int id) {
		for (int i = 0; i <= 20; i++) {
			buffer.insert(id * 20 + i); // val: id * 20 + i
			sleep(1000); // bound:1000
		}
	}

	private static void consumer() {
		while (true) {
			int val = buffer.extract();
			System.out.println(val);
			sleep(1000);
		}
	}

	public static void main(String[] args) throws InterruptedException {

		buffer = new Ejercicios_02_11_I();// buffer
		List<Thread> ths = new ArrayList<>();

		for (int i = 0; i < NPROD; i++) {
			int finalI = i;
			ths.add(new Thread(() -> producer(finalI), "Producer" + i)); // name: "Producer"
		}

		for (int i = 0; i < NCONS; i++) {
			ths.add(new Thread(() -> consumer(), "Consumer" + i));
		}

		for (Thread th : ths) {
			th.start();
		}

		for (Thread th : ths) {
			th.join();
		}

	}

}
