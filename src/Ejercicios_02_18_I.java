public class Ejercicios_02_18_I {

//	Ejercicios 4.1 (Diapositivas)

	public static void mensajes() {
		String[] frases = new String[] { "MSJ: La vida es bella", "MSJ: O no...", "MSJ: Los pajaritos cantan",
				"MSJ: Y molestan" };

		for (String frase : frases) {
			System.out.println(frase);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Se acabo!");
				return;
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread th = new Thread(() -> mensajes());
		th.start();
		boolean continuar = true;
		int iters = 0;
		while (continuar) {
			if (!th.isAlive()) {
				continuar = false;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (iters == 5) {
					System.out.println("MAIN: Cansado de esperar!");
					th.interrupt();
					try {
						th.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continuar = false;

				} else {
					System.out.println("MAIN: Todavia esperando...");
					iters++;
				}
			}

		}
		System.out.println("Por fin!");
	}

}
