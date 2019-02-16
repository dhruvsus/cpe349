import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class WgtIntScheduler {
	public static ArrayList<Integer> getOptSet(int[] stime, int[] ftime, int[] weight) {
		int size = ftime.length + 1;
		ArrayList<Integer> jobs = new ArrayList<Integer>();
		// table of solutions
		int[] table = new int[size];
		// stime, ftime, and weights may not be sorted.
		// sort stime, ftime, and weights according to ftime
		for (int i = 0; i < ftime.length; i++) {
			for (int j = i; j < ftime.length; j++) {
				if (ftime[j] < ftime[i]) {
					// swap positions of stime, ftime, and job weights
					int temp = ftime[j];
					ftime[j] = ftime[i];
					ftime[i] = temp;
					temp = stime[j];
					stime[j] = stime[i];
					stime[i] = temp;
					temp = weight[j];
					weight[j] = weight[i];
					weight[i] = temp;
				}
			}
		}

		// build table
		for (int i = 1; i < table.length; i++) {
			// find latest job compatible with job i
			int compatible = i;
			while (compatible > 0) {
				if (ftime[compatible - 1] <= stime[i - 1]) {
					break;
				} else {
					compatible--;
				}
			}
			// compatible is the latest job compatible with job i. may be 0
			table[i] = Math.max(weight[i - 1] + table[compatible], table[i - 1]);
		}
		
		for (int i = 0; i < stime.length; i++) {
			System.out.printf("job %d starts at %d and ends at %d with weight %d\n", i, stime[i], ftime[i], weight[i]);
		}

		// traceback
		for (int i = table.length - 1; i > 0; i--) {
			// if table[i] is less than table[i-1], job selected
			// set new i
			if (table[i] > table[i - 1]) {
				jobs.add(i);
				// set i to latest compatible job
				int compatible = i;
				while (compatible > 0) {
					if (ftime[compatible - 1] <= stime[i - 1]) {
						break;
					} else {
						compatible--;
					}
				}
				i = compatible + 1;
			}
		}
		for (Integer job : jobs) {
			System.out.printf("job %d\n", job);
		}
		return jobs;
	}

	public static void main(String[] args) {
		int[] stime = { 4, 3, 2, 10, 7 };
		int[] ftime = { 7, 10, 6, 13, 9 };
		int[] weights = { 6, 6, 5, 2, 8 };
		ArrayList<Integer> jobs = new ArrayList<Integer>();
		jobs = getOptSet(stime, ftime, weights);
	}
}
