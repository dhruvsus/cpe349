import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Knapsack {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            return;
        }
        String filename = args[0];
        // scanner to scan in file
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
        // sanity checks
        System.out.printf("capacity = %d\nnumber of items = %d\n", capacity, numItems);
        // done scanning file
        // Brute Force
        BruteForce bf = new BruteForce(numItems, value, weight, capacity);
        // bf.getMaxProfit();
        GreedySearch gs = new GreedySearch(numItems, identifier, value, weight, capacity);
        gs.getMaxProfit();
        Dynamic dp = new Dynamic(numItems, value, weight, capacity);
        dp.getMaxProfit();
        Branch bb = new Branch(numItems, value, weight, capacity);
        bb.getMaxProfit();
        return;
    }
}
