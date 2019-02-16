import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;


public class WgtIntScheduler {
	public static int[] getOptSet(int[] stime, int[] ftime, int[] weight) {
		int size = ftime.length + 1;
		int[] jobs=new int[size];
		// table of solutions
		// dimensions = #jobs+1 x jobs+1(since we can assume that the 0th job
		// starts and ends at 0)
		int[][] table = new int[size][size];
		// stime, ftime, and weights may not be sorted.
		// sort stime, ftime, and weights according to ftime
		for (int i=0;i<ftime.length;i++) {
			for(int j=i;j<ftime.length;j++){
                if(ftime[j]<ftime[i]){
                    // swap positions of stime, ftime, and job weights
                    int temp=ftime[j];
                    ftime[j]=ftime[i];
                    ftime[i]=temp;
                    temp=stime[j];
                    stime[j]=stime[i];
                    stime[i]=temp;
                    temp=weight[j];
                    weight[j]=weight[i];
                    weight[i]=temp;
                }
            }
        }
        // print for testing if sort worked
        for(int i=0;i<stime.length;i++){
            System.out.printf("job %d starts at %d and ends at %d with weight %d\n",i, stime[i], ftime[i], weight[i]);
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
