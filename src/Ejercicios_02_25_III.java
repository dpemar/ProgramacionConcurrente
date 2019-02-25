import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicios_02_25_III {

//	Ejercicios 4.1 (Diapositiva 55 CyclicBarrier)

	private static int NPROC = 4;
	private static CyclicBarrier barrier;
	private static Lock lock = new ReentrantLock();

	public static void process(int id) {
		try {
			while (true) {
				lock.lock();
				for (int i = 0; i < id; i++) {
					System.out.print("\t");
					Thread.sleep(10);
				}
				System.out.println("A");
				lock.unlock();
				Thread.sleep(1000);
				barrier.await();
			}

		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		barrier = new CyclicBarrier(NPROC, () -> System.out.println("*"));

		for (int i = 0; i < NPROC; i++) {
			int id = i;
			new Thread(() -> process(id)).start();
		}
	}
}
