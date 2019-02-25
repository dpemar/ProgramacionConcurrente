import java.util.concurrent.CountDownLatch;

public class Ejercicios_02_25_I {

//	Ejercicios 4.1 (Diapositivas)

	private static final int NRUNNERS = 10;
	private static CountDownLatch latch = new CountDownLatch(4);
//	private static Semaphore semEx = new Semaphore(0);
	
	public static void runner (int id) {
		System.out.println("READY " + id);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("RUNNING " + id);
	}
	
	public static void judge() {
		for (int i = 3; i >= 0 ; i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("COUNTER " + i);
			latch.countDown();
		}
	}
	
	public static void main(String[] args) {
		new Thread(() -> judge()).start();
		for (int i = 0; i < NRUNNERS; i++) {
			int id = i+1;
			new Thread(() -> runner(id)).start();
		}
	}

}
