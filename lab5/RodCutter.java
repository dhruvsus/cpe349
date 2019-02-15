
//Assumption, number of prices =length of rod
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class RodCutter {
	private static ArrayList<Integer> cuts;

	static int[] rodCutting(int[] prices) {
		int size = prices.length;
		int[] table = new int[size];
		// price for 0 inches
		table[0] = prices[0];
		for (int i = 1; i < size; i++) {
			for (int j = 1; j <= i; j++) {
				table[i] = Math.max(table[i], prices[j] + table[i - j]);
			}
		}
		return table;
	}

	static int[] traceBack(int[] table, int[] prices) {
		int size = table.length - 1;
		int[] cuts = new int[size];
		int i = size;
		while (i > 0) {
			int x = i;
			while (x >= 0) {
				if (table[i] - table[x] == prices[i - x]) {
					cuts[i - x]++;
					i = x;
				}
				x--;
			}
		}
		return cuts;
	}

	public static void main(String[] arg) throws FileNotFoundException {
		// vars
		int[] table;
		int[] prices;
		int[] cuts;
		int rodLength;
		int size;
		String filename = "rodOptTest.txt";
		// create a scanner object for file
		Scanner scanner = new Scanner(new File(filename));
		int numCases = scanner.nextInt();
		for (int i = 0; i < numCases; i++) {
			rodLength = scanner.nextInt();
			size = rodLength + 1;
			table = new int[size];
			prices = new int[size];
			cuts = new int[size];
			// load in prices
			// assuming number of prices provided equals rod length
			// price for 0 inches hehe
			prices[0] = 0;
			for (int j = 1; j <= rodLength; j++) {
				prices[j] = scanner.nextInt();
			}
			// obtain solution table
			table = rodCutting(prices);
			// traceback
			cuts = traceBack(table, prices);
			// Output
			System.out.printf("Case %d:\n",i+1);
			for (int k = 1; k < table.length; k++) {
				System.out.printf("total for length %d     = %d\n", k, table[k]);
			}
			System.out.printf("Optimal rod cutting\n");
			for (int k = 1; k < cuts.length; k++) {
				if(cuts[k]>0){
					System.out.printf("Number of rods of length %d      = %d\n",k,cuts[k]);
				}
			}
		}

		/*
		 * int arr[] = new int[] { 2, 4, 4, 5, 12, 13, 14, 15, 40, 41 }; int arr1[] =
		 * new int[] { 1, 4, 6, 25, 28, 31, 80, 81, 82, 83, 84, 85, 86, 88, 90, 92 };
		 * table = rodCutting(arr1, size);
		 * 
		 * for (int k = 1; k < table.length; k++) {
		 * System.out.println("total for length " + k + " = " + table[k]); } table =
		 * traceBack(table, arr1);
		 * 
		 * for (int k = 1; k < table.length; k++) {
		 * System.out.println("number of cuts of size " + k + " = " + table[k]); }
		 */
	}
}
