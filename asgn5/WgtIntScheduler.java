import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class WgtIntScheduler {
	public static int[] getOptSet(int[] stime, int[] ftime, int[] weight) {
		int size = ftime.length + 1;
		ArrayList<Integer> jobs = new ArrayList<Integer>();
		int[] jobsArray;
		int[] conversionArray = new int[size];
		for (int i = 0; i < size; i++) {
			conversionArray[i] = i;
		}
		// table of solutions
		int[] table = new int[size];
		// stime, ftime, and weights may not be sorted.
		// sort stime, ftime, and weights according to ftime
		for (int i = 0; i < ftime.length; i++) {
			for (int j = i; j < ftime.length; j++) {
				if (ftime[j] < ftime[i]) {
					// swap positions of stime, ftime, and job weights
					int temp;
					temp = conversionArray[j + 1];
					conversionArray[j + 1] = conversionArray[i + 1];
					conversionArray[i + 1] = temp;
					temp = ftime[j];
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

		// traceback
		for (int i = table.length - 1; i > 0; i--) {
			// if table[i] is less than table[i-1], job selected
			// set new i
			if (table[i] > table[i - 1]) {
				jobs.add(conversionArray[i]);
				// set i to latest compatible job
				int compatible = i;
				while (compatible > 0) {
					if (ftime[compatible - 1] <= stime[i - 1]) {
						break;
					} else {
						compatible--;
					}
				}
				// set i to compatible +1 to deal with the i--
				i = compatible + 1;
			}
		}
		jobs.sort(null);
		jobsArray = new int[jobs.size()];
		for (int i = 0; i < jobsArray.length; i++) {
			jobsArray[i] = jobs.get(i);
		}
		return jobsArray;
	}
}
