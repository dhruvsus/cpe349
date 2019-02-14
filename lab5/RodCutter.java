import java.util.ArrayList;

public class RodCutter {
	private static int[] table;
	// private static int[] prices;
	private static ArrayList<Integer> cuts;

	static int[] rodCutting(int[] prices, int numPrices) {
		table = new int[numPrices + 1];
		for (int i = 1; i <= numPrices; i++) {
			int maxMoney = 0;
			for (int j = 0; j < i; j++) {
				maxMoney = Math.max(maxMoney, prices[j] + table[i - j - 1]);
			}
			table[i] = maxMoney;

		}
		System.out.println("Output: ");
		System.out.println("Case: ");
		for (int k = 1; k <= numPrices; k++) {
			System.out.println("total for length " + k + " = " + table[k]);
		}
		return table;
	}

	// TODO
	static void traceBack()

	public static void main(String[] arg) {
		int arr[] = new int[] { 2, 4, 4, 5, 12, 13, 14, 15, 40, 41 };
		int arr1[] = new int[] { 1, 4, 6, 25, 28, 31, 80, 81, 82, 83, 84, 85, 86, 88, 90, 92 };
		int size = arr1.length;
		rodCutting(arr1, size);
	}
}
