import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Comparador {

	public static void main(String[] args) {
		
		List<Integer> a = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) {
			a.add(new Random().nextInt(100));
		}
		
		a.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		a.sort((o1,o2) -> o1 - o2);
		
	}
}
