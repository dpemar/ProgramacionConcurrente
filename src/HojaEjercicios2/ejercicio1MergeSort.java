/**
 * 
 */
package HojaEjercicios2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author Ivan.Perez
 *
 *         https://github.com/IvanPerez9 -> TODO CAMBIAR
 */
@SuppressWarnings("serial")
public class ejercicio1MergeSort extends RecursiveAction {

	private static final int SIZE = 10;

	private int[] array;

	public ejercicio1MergeSort(int[] array) {
		this.array = array;
	}

	@Override
	protected void compute() {
		if (array.length < 2) {
			return;
		}
		int mid = array.length / 2;
		int[] arr1 = Arrays.copyOfRange(array, 0, mid);
		int[] arr2 = Arrays.copyOfRange(array, mid, array.length);
		if (array.length < SIZE) {
			mergeSort(array);
			mergeSort(array);
		} else {
			invokeAll(new ejercicio1MergeSort(arr1), new ejercicio1MergeSort(arr2));
			merge(array, arr1, arr2);
		}

	}

	private void merge(int[] arr, int[] arr1, int[] arr2) {
		
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] <= arr2[j]) {
				arr[k++] = arr1[i++];
			} else {
				arr[k++] = arr2[j++];
			}
		}
		while (i < arr1.length) {
			arr[k++] = arr1[i++];
		}
		while (j < arr2.length) {
			arr[k++] = arr2[j++];
		}
	}

	public void mergeSort (int[] arr) {
		if (arr.length < 2) {
			return;
		}
		int mid = arr.length / 2;
		int[] arr1 = Arrays.copyOfRange(arr, 0, mid);
		int[] arr2 = Arrays.copyOfRange(arr, mid, arr.length);
		mergeSort(arr1);
		mergeSort(arr2);
		merge(arr, arr1, arr2);
	}

	public static int[] generateRandomArray(int size) {
		int[] aux = new int[size];
		Random rdn = new Random();
		for (int i = 0; i < aux.length; i++) {
			aux[i] = rdn.nextInt(size);
		}
		return aux;
	}

	public static void main(String[] args) {
		int[] array = generateRandomArray(SIZE);
		System.out.println("Array: " + Arrays.toString(array));
		ForkJoinPool.commonPool().invoke(new ejercicio1MergeSort(array));
		System.out.println("Ordenado: " + Arrays.toString(array));
	}
}
