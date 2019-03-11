import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class GreedySearch {
    int n; // number of items
    int[] identifiers;
    int[] weights;
    int[] values;
    double[] vWRatio;
    int capacity;

    public GreedySearch(int n, int[] identifier, int[] value, int[] weight, int capacity) {
        this.n = n;
        this.identifiers = identifier;
        this.values = value;
        this.weights = weight;
        this.capacity = capacity;
        vWRatio = new double[n];
        for (int i = 0; i < n; i++) {
            vWRatio[i] = (double) values[i] / weights[i];
        }
    }

    public void getMaxProfit() {
        // sort identifiers
        List<Integer> identifierList = Arrays.stream(identifiers) // IntStream
                .boxed() // Stream<Integer>
                .collect(Collectors.toList());
        @SuppressWarnings("unchecked")
        ArrayList<Integer> sortedIdentifers = new ArrayList(identifierList);
        Collections.sort(sortedIdentifers, (right, left) -> Double.compare(vWRatio[identifierList.indexOf((left))],
                vWRatio[identifierList.indexOf((right))]));
        int tempCapacity = 0, removed = -1, tempValue = 0;
        int[] pickedUpItems = new int[n];
        // sanity check
        while (tempCapacity < capacity) {
            removed = sortedIdentifers.remove(0);
            if (tempCapacity + weights[removed - 1] <= capacity) {
                pickedUpItems[removed - 1] = 1;
                tempValue += values[removed - 1];
                tempCapacity += weights[removed - 1];
            } else {
                break;
            }
        }
        System.out.printf("Greedy solution (not necessarily optimal): Value %d, Weight %d\n", tempValue, tempCapacity);
        for (int i = 0; i < n; i++) {
            if (pickedUpItems[i] == 1) {
                System.out.printf("%d ", i + 1);
            }
        }
        System.out.println();
    }
}