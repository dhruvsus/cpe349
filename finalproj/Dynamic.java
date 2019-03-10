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

    public void getMaxProfit() {
        // variables
        int[][] table = new int[n + 1][capacity + 1];
        // fill up table
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                // check if the item fits the backpack
                if (weights[i - 1] > j) {
                    // item doesn't fit
                    table[i][j] = table[i - 1][j];
                } else {
                    // max of item picked up or not
                    table[i][j] = Math.max(table[i - 1][j], values[i - 1] + table[i - 1][j - weights[i - 1]]);
                }
            }
        }
        // table completed
        // print for sanity check
        for (int i = 0; i < n + 1; i++) {
            System.out.println();
            for (int j = 0; j < capacity + 1; j++) {
                System.out.printf("%d\t", table[i][j]);
            }
        }
        System.out.println();
        // traceback
        int[] pickedUpItems = new int[n];
        int maxValue = table[n][capacity], tempValue = maxValue, i = n, j = capacity;
        while(tempValue!=0){
            //System.out.printf("table[i-1][j] = %d, table[i-1][j-weights[i-1]=%d\n", table[i-1][j], table[i-1][j-weights[i-1]]);
            if(tempValue==table[i-1][j]){
                // not picked up
                System.out.printf("item %d not picked up\n",i);
                System.out.printf("capacity remaining = %d\n",j);
                tempValue=table[i-1][j];
                i--;
            }
            else{
                // item picked up
                System.out.printf("item %d picked up\n",i);
                tempValue=table[i-1][j-weights[i]];
                i--;
                j-=weights[i-1];
                System.out.printf("capacity remaining = %d\n",j);

            }
        }
    }
}