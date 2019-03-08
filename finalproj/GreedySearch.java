public class GreedySearch {
    int n; // number of items
    int[] weights;
    int[] values;
    int[] vWRatio;
    int capacity;

    public GreedySearch(int n, int[] value, int[] weight, int capacity) {
        this.n = n;
        this.values = value;
        this.weights = weight;
        this.capacity = capacity;
        vWRatio = new int[n];
        for (int i = 0; i < n; i++) {
            vWRatio[i] = values[i] / weights[i];
        }
    }

    public void getMaxProfit() {
        int tempCapacity = capacity;
        while (tempCapacity > 0) {

        }
    }
}