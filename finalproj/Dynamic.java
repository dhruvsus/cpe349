public class Dynamic {
    int n; // number of items
    int[] weights;
    int[] values;
    int capacity;

    public Dynamic(int n, int[] value, int[] weight, int capacity) {
        this.n = n;
        this.values = value;
        this.weights = weight;
        this.capacity = capacity;
    }
}