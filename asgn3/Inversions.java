import java.util.Arrays;
import java.util.Collections;

public class Inversions {
	public Inversions() {
	}

	public static int invCounter(int[] data) {
		// mergeSort is the main program that splits the arrays
		// and calls itself recursively
		return mergeSortCountInversion(data);
	}

	public static int mergeSortCountInversion(int[] data) {
		int length = data.length;
		int inversions = 0;
		// while the input array length is greater than one
		// DIVIDE and call mergeSortCountInversions recursively
		if (length > 1) {
			int mid = length / 2;
			// split array
			int[] left = new int[mid];
			left = Arrays.copyOfRange(data, 0, mid);
			int[] right = new int[length - mid];
			right = Arrays.copyOfRange(data, mid, length);

			// DIVIDE and COMBINE
			// count left side inversions
			// ie i<j A[i]>A[j] and i,j<mid
			inversions += mergeSortCountInversion(left);
			// count right side inversions
			// ie i<j A[i]>A[j] and i,j>mid
			inversions += mergeSortCountInversion(right);
			// count split inversions
			// ie i<mid<j and A[i]>A[j]
			// also the CONQUER step
			inversions += mergeSortedCountSplitInversion(left, right, data);
		}
		return inversions;
	}

	// CONQUER
	public static int mergeSortedCountSplitInversion(int[] left, int[] right, int[] data) {
		int i = 0, j = 0, k = 0;
		int inv_count = 0;

		// Merge left and right
		// Algorithm for counting split inversions:
		// If right puts a value y in data, then it has
		// a split inversion with every remaining element
		// in left as index of right > index of left
		// and if right puts before left, then the remaining
		// values in left are greater than y since both
		// left and right are initially sorted.

		while (i < left.length && j < right.length) {
			// we don't care if left adds to data
			if (left[i] < right[j]) {
				data[k] = left[i];
				++i;
			} else {
				// real shit
				data[k] = right[j];
				++j;
				// add to number of split inversions the number of
				// elements that remain to be added to data from left
				inv_count = inv_count + left.length - i;
			}
			++k;
		}
		// Leftovers have nothing to do with split inversion
		while (i < left.length) {
			data[k] = left[i];
			++i;
			++k;
		}
		while (j < right.length) {
			data[k] = right[j];
			++j;
			++k;
		}
		return inv_count;
	}

	public static void main(String[] args) {
		int[] data = new int[] {10,9,8,7,6,5,4,3,2,1 };
		System.out.println(invCounter(data));
	}
}
