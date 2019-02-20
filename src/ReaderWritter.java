/**
 * 
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */

public class ReaderWritter {

	private static int NCLIENTS = 10;
	private static int NSELERS = 5;
	private static Monitor monitor;
	
	private static void buyers () {
		for (int i = 0; i < NCLIENTS; i++) {
			try {
				monitor.buy(i+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void seller (int id) {
		while(true) {
			monitor.sell(id);
		}
	}
	
	public static void main(String[] args) {
		monitor = new Monitor();
		List<Thread> ths = new ArrayList<>();
		ths.add(new Thread(()-> buyers()));
		
		for (int i = 0; i < NSELERS; i++) {
			int idSeller = i+1;
			ths.add(new Thread(() -> seller(idSeller)));
		}
		
		for (Thread thread : ths) {
			thread.start();
		}
		
		for (Thread thread : ths) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
