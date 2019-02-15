import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream;
import java.util.stream.Collectors;

public class WgtIntScheduler {
    public static int[] getOptSet(int[] stime, int[] ftime, int[] weight) {
        int[] jobs;
        int size = ftime.length + 1;
        // table of solutions
        // dimensions = #jobs+1 x jobs+1(since we can assume that the 0th job
        // starts and ends at 0)
        int[][] table = new int[size][size];
        // stime, ftime, and weights may not be sorted.
        // sort stime, ftime, and weights according to ftime
        List<Integer> stimeCopy = IntStream.of(stime).boxed().collect(Collectors.toList());
        ArrayList<Integer> sortedStime = new ArrayList(stimeCopy);
        Collections.sort(sortedStime, Comparator.comparing(s -> ftime[stimeCopy.indexOf(s)]));
        for (Integer i : sortedStime) {
            System.out.printf("%d\n", i);
        }
        return jobs;
    }

    public static void main(String[] args) {
        int[] stime = { 4, 3, 2, 10, 7 };
        int[] ftime = { 7, 10, 6, 13, 9 };
        int[] weights = { 6, 6, 5, 2, 8 };
        int[] jobs = new int[stime.length];
        jobs = getOptSet(stime, ftime, weights);
    }
}