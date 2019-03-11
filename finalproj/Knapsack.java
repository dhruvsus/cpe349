import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

public class Knapsack {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            return;
        }
        // populate data
        String filename = args[0];
        Scanner scanner = new Scanner(new File(filename));
        int numItems = scanner.nextInt();
        int[] identifier = new int[numItems]; // not really needed
        int[] value = new int[numItems];
        int[] weight = new int[numItems];
        for (int i = 0; i < numItems; i++) {
            identifier[i] = scanner.nextInt();
            value[i] = scanner.nextInt();
            weight[i] = scanner.nextInt();
        }
        int capacity = scanner.nextInt();
        // start timer
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
        
        // Brute Force
        /* BruteForce bf = new BruteForce(numItems, value, weight, capacity);
        bf.getMaxProfit();
        elapsedTime = (new Date()).getTime() - startTime;
        System.out.printf("Brute force on %s: %d milliseconds\n",filename,elapsedTime);
        System.out.println(); */

        // Greedy Search with v/w sorting
        startTime=System.currentTimeMillis();
        GreedySearch gs = new GreedySearch(numItems, identifier, value, weight, capacity);
        gs.getMaxProfit();
        elapsedTime = (new Date()).getTime() - startTime;
        System.out.printf("Greedy Search on %s: %d milliseconds\n",filename,elapsedTime);
        System.out.println();
        
        // Dynamic Programming
        startTime=System.currentTimeMillis();
        Dynamic dp = new Dynamic(numItems, value, weight, capacity);
        dp.getMaxProfit();
        elapsedTime = (new Date()).getTime() - startTime;
        System.out.printf("Dynamic Programming on %s: %d milliseconds\n",filename,elapsedTime);
        System.out.println();
        
        // Branch and Bound with Fractional Upper Bounds
        startTime=System.currentTimeMillis();
        Branch bb = new Branch(numItems, identifier, value, weight, capacity);
        bb.getMaxProfit();
        elapsedTime = (new Date()).getTime() - startTime;
        System.out.printf("Branch and Bound on %s: %d milliseconds\n",filename,elapsedTime);
        System.out.printf("\n\n");
        return;
    }
}
