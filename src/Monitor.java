/**
 * 
 */


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */

public class Monitor {

	static final int TICKETPRICE = 100;
	private int money;
	private Queue<Integer> request; // Interfaz Queue 
	
	public Monitor() {
		request = new LinkedList<>(); // Puedo tambien ArryaDeck
		// Uso linked list como una cola por el Queue 
	}
	
	// synchronized para exclusion mutua
	// La sincronizacion condicional la meto yo un monitor tiene ambas 
	
	// Entradas infinitas
	public synchronized void buy (int client) throws InterruptedException {
		Thread.sleep(1000); // Sleep para ir viendolo 
		request.add(client);
		System.out.println("El cliente " + client + "ha llegado");
	}
	
	// Si hay alguien que quiere comprar 
	public synchronized void sell (int id) { // Lo mismo que poner Throws Interrumped 
		while(request.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int cliente = request.poll(); // Codigo de linkedlist, complejidad de Linked list , Queue es interfaz
		System.out.println("Procesado cliente " + cliente + "por la ventanilla " + id);
		money += TICKETPRICE;
		show();
		this.notifyAll();
	}
	
	public synchronized void show () {
		System.out.println("\tClientes esperando: ");
		for (Integer r : request) {
			System.out.println(r + " ");
		}
		System.out.println();
		System.out.println("\tDinero: " + money);
	}
}
