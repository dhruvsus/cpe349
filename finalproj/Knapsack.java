import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

        // Brute Force
        BruteForce bf = new BruteForce(numItems, value, weight, capacity);
        // bf.getMaxProfit();
        
        // Greedy Search with v/w sorting
        GreedySearch gs = new GreedySearch(numItems, identifier, value, weight, capacity);
        gs.getMaxProfit();
        
        // Dynamic Programming
        Dynamic dp = new Dynamic(numItems, value, weight, capacity);
        dp.getMaxProfit();

        // Branch and Bound with Fractional Upper Bounds
        Branch bb = new Branch(numItems, identifier, value, weight, capacity);
        bb.getMaxProfit();
        return;
    }
}
