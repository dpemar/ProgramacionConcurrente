
public class Introduccion {

	public static void main(String[] args) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread 1");
			}
		}).start();

		new Thread(() -> System.out.println("Thread 2")).start();

		new Thread(() -> {
			System.out.println("Thread 3");
		}).start();
	}
}
