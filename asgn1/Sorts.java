import java.util.Arrays;
import java.util.Collections;

/*
   Sorts arrray of integers and return a long that contains the number of key comparisons
   done by the sorting algorithm.
   */
public class Sorts {

	public Sorts() {
	}

	public static long selectSort(int[] data) {
		int length = data.length;
		long numComp = 0;
		for (int i = 0; i < length - 1; ++i) {
			// find lowest term
			// int lowest_index=i;
			for (int j = i + 1; j < length; ++j) {
				if (data[j] < data[i]) {
					// swap the two
					int temp = data[j];
					data[j] = data[i];
					data[i] = temp;
				}
				++numComp;
			}
		}
		return numComp;
	}

	public static long insertSort(int[] data) {
		int length = data.length;
		long numComp = 0;
		for (int i = 1; i < length; ++i) {
			int toInsert = data[i];
			// move everything greater than toInsert one index over
			int j = i - 1;
			while (j >= 0 && toInsert < data[j]) {
				data[j + 1] = data[j];
				--j;
				++numComp;
			}
			// insertToInsert into created location
			data[j + 1] = toInsert;
		}
		return numComp;
	}

	public static long mergeSort(int[] data) {
		long numComp = 0;
		if (data == null) {
			return 0;
		}

		if (data.length > 1) {
			int mid = data.length / 2;

			// Split left part
			int[] left = new int[mid];
			for (int i = 0; i < mid; i++) {
				left[i] = data[i];
			}

			// Split right part
			int[] right = new int[data.length - mid];
			for (int i = mid; i < data.length; i++) {
				right[i - mid] = data[i];
			}
			mergeSort(left);
			mergeSort(right);

			int i = 0;
			int j = 0;
			int k = 0;

			// Merge left and right datas
			while (i < left.length && j < right.length) {
				if (left[i] < right[j]) {
					data[k] = left[i];
					i++;
				} else {
					data[k] = right[j];
					j++;
				}
				++numComp;
				k++;
			}
			// Collect remaining elements
			while (i < left.length) {
				data[k] = left[i];
				i++;
				k++;
			}
			while (j < right.length) {
				data[k] = right[j];
				j++;
				k++;
			}
		}
		return numComp;
	}

	public static void main(String[] args) {
		int[] a = { 10, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] temp = a.clone();
		long numComp = 0;

		System.out.println("test array to sort:  " + Arrays.toString(a));
		numComp = Sorts.insertSort(temp);
		System.out.println("Sorted by insertion sort: ");
		System.out.println(Arrays.toString(temp));
		System.out.println("# of comparisons in insertion sort:  " + numComp);
		System.out.println();

		temp = a.clone();
		System.out.println("test array to sort:  " + Arrays.toString(a));
		numComp = Sorts.selectSort(temp);
		System.out.println("Sorted by selection sort: ");
		System.out.println(Arrays.toString(temp));
		System.out.println("# of comparisons in selection sort:  " + numComp);
		System.out.println();

		temp = a.clone();
		System.out.println("test array to sort:  " + Arrays.toString(a));
		numComp = Sorts.mergeSort(temp);
		System.out.println("Sorted by merge sort: ");
		System.out.println(Arrays.toString(temp));
		System.out.println("# of comparisons by mergesort is = " + numComp);
		System.out.println();

	}
}
