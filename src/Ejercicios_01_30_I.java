import java.util.Scanner;

public class Ejercicios_01_30_I {

//	Calculadora concurrente (join)
//	1. Un hilo para llevar a cabo las operaciones (calculator)
//	2. Un hilo para mostrar los resultados (writer)

	static volatile int result = 0;

	public static void main(String[] args) {

		Thread writer = new Thread(() -> {
			System.out.println("El resultado es: " + result);
		});

		Thread calculator = new Thread(() -> {

			System.out.println("Primer operando: ");
			Scanner sc1 = new Scanner(System.in);
			int num1 = sc1.nextInt();

			System.out.println("Operacion: ");
			Scanner op = new Scanner(System.in);
			String operation = op.next();

			System.out.println("Segundo operando: ");
			Scanner sc2 = new Scanner(System.in);
			int num2 = sc2.nextInt();

			try {
				if ((!operation.isEmpty())) {
					switch (operation) {
					case "+":
						result = num1 + num2;
						break;
					case "-":
						result = num1 - num2;
						break;
					case "*":
						result = num1 * num2;
						break;
					case "/":
						result = num1 / num2;
						break;
					default:
						System.out.println("Error operation");
					}
				}
			} catch (ArithmeticException e) {
				e.printStackTrace();
			}

		});

		try {
			calculator.start();
			calculator.join();
			writer.start();
			writer.join();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

	}
}
