
//Assumption, number of prices =length of rod
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class RodCutter {
	private static ArrayList<Integer> cuts;

	static int[] rodCutting(int[] prices, int numPrices) {
		int[] table = new int[numPrices + 1];
		for (int i = 1; i <= numPrices; i++) {
			int maxMoney = 0;
			for (int j = 0; j < i; j++) {
				maxMoney = Math.max(maxMoney, prices[j] + table[i - j - 1]);
			}
			table[i] = maxMoney;
		}
		return table;
	}

	static int[] traceBack(int[] table, int[] prices) {
		int size = table.length - 1;
		int[] newPrices = new int[prices.length + 1];
		// copy prices
		System.arraycopy(prices, 0, newPrices, 1, prices.length);

		int[] cuts = new int[size];
		int i = size;
		while (i > 0) {
			int x = i;
			while (x >= 0) {

				if (table[i] - table[x] == newPrices[i - x]) {
					cuts[i - x]++;
					i = x;
				}
				x--;
			}
		}
		return cuts;
	}

	public static void main(String[] arg) throws FileNotFoundException{
		// obtain number of cases and 
		String filename="rodOptTest.txt";
		//create a scanner object for file
		Scanner scanner = new Scanner(new File(filename));
		
		int[] table;
		int arr[] = new int[] { 2, 4, 4, 5, 12, 13, 14, 15, 40, 41 };
		int arr1[] = new int[] { 1, 4, 6, 25, 28, 31, 80, 81, 82, 83, 84, 85, 86, 88, 90, 92 };
		int size = arr1.length;
		table = new int[size + 1];
		table = rodCutting(arr1, size);

		for (int k = 1; k < table.length; k++) {
			System.out.println("total for length " + k + " = " + table[k]);
		}
		table = traceBack(table, arr1);

		for (int k = 1; k < table.length; k++) {
			System.out.println("number of cuts of size " + k + " = " + table[k]);
		}
	}
}
