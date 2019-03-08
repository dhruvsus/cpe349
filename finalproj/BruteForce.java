import java.util.ArrayList;

public class BruteForce {
    int n; // number of items
    int[] weights;
    int[] values;
    int capacity;
    ArrayList<String> strings; // generated binary strings for arrangements

    public BruteForce(int n, int[] value, int[] weight, int capacity) {
        this.n = n;
        this.values = value;
        this.weights = weight;
        this.capacity = capacity;
    }

    private ArrayList<String> getGrayCode(int n) {
        ArrayList<String> grayCode = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        if (n > 1) {
            // recursive
            temp = getGrayCode(--n);
            // prepend a 0
            for (int i = 0; i < temp.size(); i++) {
                grayCode.add("0" + temp.get(i));
            }
            // prepend 1
            for (int i = temp.size(); i-- > 0;) {
                grayCode.add("1" + temp.get(i));
            }
            return grayCode;
        } else {
            // graycode for n=1 is just {0,1}, it's the base case
            grayCode.add("0");
            grayCode.add("1");
            return grayCode;
        }
    }

    public void getMaxProfit() {
        ArrayList<String> binString = getGrayCode(n);
        int numStrings = binString.size();
        int maxProfit = 0;
        String[] binStr = new String[n];
        int[] binInt = new int[n];
        String maxbinString;
        for (String str : binString) {
            binStr = str.toCharArray();
            for (int i = 0; i < binStr.length; i++) {
                
            }
        }
    }
}