
public class Ejercicios_01_23 {

	private static int a;

	private static void sleep() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

//		Programa concurrente que 
//		incremente (hilo 1) una variable
//		decremente (hilo 2) una variable
		a = 0;
		new Thread(() -> a++).start();
		new Thread(() -> a--).start();
		System.out.println("a = " + a);

//		Programa concurrente que escriba 
//		5 veces "XXX" (hilo 1),
//		5 veces "---" (hilo 2),
//		1 vez "***" (hilo 3)
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("XXX");
				sleep();
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("---");
				sleep();
			}
		}).start();

		new Thread(() -> {
			System.out.println("***");
			sleep();
		}).start();
	}

}
