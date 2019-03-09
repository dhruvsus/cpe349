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

    public void getMaxProfit(){
        // variables
        int[][] table = new int[n+1][capacity+1];
        // fill up table
        for(int i=1;i<n+1;i++){
            for(int j=1;j<capacity+1;j++){
                
            }
        }
    }
}