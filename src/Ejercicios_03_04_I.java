import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ejercicios_03_04_I {

	private static List<String> l;

	public static void process(int id) {
		try {
			for (int i = 0; i < 5; i++) {
				Thread.sleep((long) (Math.random() * 500));
				l.add("	T	H	" + id + "_IT" + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		l = Collections.synchronizedList(new ArrayList<>());
		List<Thread> ths = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			int id = i + 1;
			ths.add(new Thread(() -> process(id)));
		}
		for (Thread thread : ths) {
			thread.start();
		}

		for (Thread thread : ths) {
			thread.join();
		}

		for (String s : l) {
			System.out.println(s);
		}
	}

}
