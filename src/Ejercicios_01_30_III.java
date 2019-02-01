import java.util.Scanner;

public class Ejercicios_01_30_III {

//	Calculadora concurrente (condicional -> flag)
//	1. Un hilo para llevar a cabo las operaciones (calculator)
//	2. Un hilo para mostrar los resultados (writer)

	static volatile boolean flagW1, flagW2, flagW3, flagW4;
	static volatile boolean flagR1;
	static volatile int result1, result2, result3, result4;
	static volatile int num1, num2;

	public static void main(String[] args) {

		flagW1 = false;
		flagW2 = false;
		flagW3 = false;
		flagW4 = false;

		result1 = 0;
		result2 = 0;
		result3 = 0;
		result4 = 0;

		num1 = 0;
		num2 = 0;

		Thread writer = new Thread(() -> {
			while ((flagW1 == false) && (flagW2 == false) && (flagW3 == false) && (flagW4 == false))
				; // Espera activa
			System.out.println(result1);
			System.out.println(result2);
			System.out.println(result3);
			System.out.println(result4);
		});

		Thread reader = new Thread(() -> {

			try {
				System.out.println("Primer operando: ");
				Scanner sc1 = new Scanner(System.in);
				int num1 = sc1.nextInt();

				System.out.println("Segundo operando: ");
				Scanner sc2 = new Scanner(System.in);
				int num2 = sc2.nextInt();

			} catch (ArithmeticException e) {
				e.printStackTrace();
			}

			flagR1 = true;
		});

		Thread suma = new Thread(() -> {
			while (flagR1 == false)
				; // Espera activa
			result1 = num1 + num2;
			flagW1 = true;
		});

		Thread resta = new Thread(() -> {
			while (flagR1 == false)
				; // Espera activa
			result2 = num1 - num2;
			flagW2 = true;
		});

		Thread multiplicacion = new Thread(() -> {
			while (flagR1 == false)
				; // Espera activa
			result3 = num1 * num2;
			flagW3 = true;
		});

		Thread division = new Thread(() -> {
			while (flagR1 == false)
				; // Espera activa
			result4 = num1 / num2;
			flagW4 = true;
		});

		try {
			// Lanzamos los hilos
			suma.start();
			resta.start();
			multiplicacion.start();
			division.start();
			reader.start();
			writer.start();
		} catch (RuntimeException e2) {
			e2.printStackTrace();
		}

	}
}
