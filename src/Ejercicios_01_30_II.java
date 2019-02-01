import java.util.Scanner;

public class Ejercicios_01_30_II {

//	Calculadora concurrente (condicional -> flag)
//	1. Un hilo para llevar a cabo las operaciones (calculator)
//	2. Un hilo para mostrar los resultados (writer)

	static volatile boolean flag;
	static volatile int result = 0;

	public static void main(String[] args) {

		flag = false;

		Thread writer = new Thread(() -> {
			while (flag == false)
				; // Espera activa
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
				// Una vez que hemos realizado la operacion, liberamos la escritura
				flag = true;

			} catch (ArithmeticException e) {
				e.printStackTrace();
			}
		});

		try {
			writer.start();
			calculator.start();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
}
