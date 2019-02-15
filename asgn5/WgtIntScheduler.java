public class WgtIntScheduler {
    public static int[] getOptSet(int[] stime, int[] ftime, int[] weight) {
        int[] jobs;
        // find max ftime
        int fMax = Arrays.stream(ftime).max().getAsInt();
        // table of solutions
        // dimensions = #jobs+1 x max_finish_time+1(since we can assume that the 0th job
        // starts and ends at 0)
        int[][] table = new int[ftime.length + 1][fMax+1];
        // stime, ftime, and weights may not be sorted.
        // implement my own damn sort
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