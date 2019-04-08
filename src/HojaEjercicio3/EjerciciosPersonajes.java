/**
 * 
 */
package HojaEjercicio3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class EjerciciosPersonajes {

	private List<Personajes> personajes;

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public EjerciciosPersonajes(String path) throws FileNotFoundException, IOException {
        personajes = new ArrayList<>();
       
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            personajes = bf.lines()
                    .skip(1)
                    .map(line -> line.trim().split(";"))
                    .filter(line -> line.length == 13) // Ojo son 13
                    .map(col -> 
                        new Personajes(
                                    col[0],
                                    col[1],
                                    col[2],
                                    col[3],
                                    col[4],
                                    col[5],
                                    col[6],
                                    col[7],
                                    col[8],
                                    Double.parseDouble(col[9]),
                                    Double.parseDouble(col[10]),
                                    Double.parseDouble(col[11]),
                                    Double.parseDouble(col[12]))
                    )
                    .collect(Collectors.toList());
        }
    }
	/*
	 * Carga el contenido del fichero e imprime el nombre de cada 
	 * personaje en una l�nea diferente
	 */
	public void ejercicio1 () {
		this.personajes.parallelStream()
				.map(p -> p.getName())
				.forEach(p -> System.out.println(p));
	}
	
	/*
	 * Implementa un m�todo que reciba como argumento el g�nero (�M� o �F�) e
	 * imprima por pantalla el nombre de todas las personas de ese g�nero.
	 */
	public void ejercicio2(String genero) {
		this.personajes.parallelStream()
			.filter(p -> p.getSex().startsWith(genero))
			.map (p -> p.getName())
			.forEach(System.out::println);
	}
	
	/*
	 * Implementa un m�todo que devuelva una lista con todas las localizaciones
	 * (sin repeticiones) que aparecen en el fichero ordenadas de forma ascendente
	 */
	public void ejercicio3() {
		this.personajes.parallelStream()
			.map(p -> p.getContinent())
			.distinct()
			.sorted((s1,s2) -> Integer.compare(s2.length(), s1.length()))
			.sequential()
			.forEach(p -> System.out.println(p));
	}
	
	/*
	 * Implementa un m�todo que imprima por pantalla el nombre y el a�o de nacimiento 
	 * (separados por un tabulador) de todos los europeos que nacieron antes del a�o 0
	 */
	public void ejercicio4() {
		this.personajes.parallelStream()
			.filter(p -> p.getContinent().equals("Europe"))
			.filter (p -> !p.getBirth().startsWith("-"))
			.forEach(p -> { 
				System.out.println(p.getName() + " " + p.getBirth());
			});
	}
	
	/*
	 * Implementa un m�todo que imprima por pantalla la suma total de visitas que
	 * han recibido todos los personajes del fichero
	 */
	public void ejercicio5() {
		Double num = this.personajes.parallelStream()
				.map(p -> p.getViews())
				.reduce( 0.0 , Double::sum);
		
		System.out.println(num);
	}
	
	/*
	 * Implementa un m�todo que imprima por pantalla el valor m�ximo de
	 * popularidad dada una ocupaci�n que se recibe como par�metro.
	 */
	public void ejercicio6(String occupation) {
		Double max = this.personajes.parallelStream()
				.filter(p -> p.getOccupation().equals(occupation))
				.map(p -> p.getPopularity())
				.reduce(0.0 , Double::max);
		System.out.println("Con la ocupacion: " + occupation + " ,el maximo es: " + max);
	}
	
	/*
	 * Implementa un m�todo que imprima por pantalla el nombre y 
	 * la popularidad del personaje m�s popular del fichero -> se puede en un Stream todo 
	 */
	public void ejercicio7 () {
		Double valor = this.personajes.parallelStream()
			.map(p -> p.getPopularity())
			.reduce( 0.0 , Double::max);
		
		String name = this.personajes.parallelStream()
			.filter(p -> p.getPopularity().equals(valor))
			.map(p -> p.getName())
			.findAny().get();
		
 		System.out.println("EL m�s popular es: " + name + " con: " + valor);
	}
	
	/*
	 * Implementa un m�todo que reciba un dominio como par�metro e imprima por
	 * pantalla a todos los personajes del dominio con el formato
	 */
	public void ejercicio8 (String domain) {
		List<String> nombres = this.personajes.parallelStream()
			.filter(p -> p.getDomain().equals(domain))
			.map(p -> p.getName())
			.collect(Collectors.toList());
		
		System.out.println("En la base de datos: ");
		for (String s : nombres) {
			System.out.println(s);
		}
		System.out.println("pertenecen al dominio de " + domain);
	}
	
	/*
	 * Implementa un m�todo que imprima por pantalla por cada industria,
	 * qu� ocupaciones aparecen en el fichero -> Agrupar por industria
	 */
	public void ejercicio9 () {
//		this.personajes.parallelStream()
//			.collect()
//			.distinct();
			
	}
	
	/*
	 * Implementa un m�todo que reciba como argumento una cadena de caracteres
	 * e imprima por pantalla el nombre de todos los personajes cuyo cualquier
	 * atributo contenga esa cadena de caracteres. Por ejemplo: si se pasa por
	 * par�metro �ab�, el m�todo tendr� que imprimir Abraham, Pablo o Muhamad 
	 */
	public void ejercicios10 (String att) {
		this.personajes.parallelStream()
			.filter(p -> p.getName().contains(att))
			.forEach(p -> System.out.println(p.getName()));
	}
	
	/*
	 * Implementa un m�todo que reciba como argumento un n�mero y muestre los
	 * nombres y la ocupaci�n de todos aquellos personajes cuyo nombre sea de la
	 * longitud del argumento dado. Por ejemplo: si se pasa por par�metro 5, el
	 * m�todo tendr� que imprimir �Name: Plato Occupation:Philosopher�
	 */
	public void ejercicio11 (int num) {
		this.personajes.parallelStream()
			.filter(p -> p.getName().length() == num)
			.forEach(p -> {
				System.out.println("Nombre: " + p.getName() + " Ocupacion: " + p.getOccupation());
			});
	}
	
	/*
	 * Implementa un m�todo que reciba como argumento el nombre de la columna 
	 * del fichero y muestre los datos ordenados de menor a mayor. Por ejemplo: si el 
	 * m�todo recibe el nombre de columna �occupation� entonces tendr� que 
	 * imprimir las ocupaciones de todos los personajes ordenados alfab�ticamente. 
	 * En el caso de que la columna sea num�rica, el orden deber� ser de menor a mayor
	 */
	
	/*
	 * Implementa un m�todo que ordene los personajes por longitud de nombre. 
	 * Por ejemplo: si tuvi�ramos Abraham y Plato, Plato se encontrar�a en primera
	 * posici�n con respecto a Abraham puesto que el nombre es m�s corto
	 */
	public void ejercicio13 () {
		Comparator<String> comparador =  (a,b) -> a.length() - b.length();
		this.personajes.parallelStream()
			.map(p -> p.getName())
			.sorted(comparador)
			.sequential()
			.forEach(System.out::println); // Imprimir secuencial
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		EjerciciosPersonajes ejercicios = new EjerciciosPersonajes("Resources/database.csv");
		
		//ejercicios.ejercicio1();
		//ejercicios.ejercicio2("F");
		//ejercicios.ejercicio3();
		//ejercicios.ejercicio4();
		//ejercicios.ejercicio5();
		//ejercicios.ejercicio6("Military Personnel");
		//ejercicios.ejercicio7();
		//ejercicios.ejercicio8("Arts");
		
		// El 9 el formato de salida
		
		ejercicios.ejercicios10("aba");
		//ejercicios.ejercicio11(3);
		//ejercicios.ejercicio13();
	}
	
}

