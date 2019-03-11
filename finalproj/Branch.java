import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class Branch {
    int n; // number of items
    int[] identifiers;
    int[] weights;
    int[] values;
    int capacity;
    double[] vWRatio;

    public Branch(int n, int[] identifier, int[] value, int[] weight, int capacity) {
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

    class Node {
        String solution;
        int ub;
        int value;
        int remainingCap;

        public Node(String solution, double ub, int value, int remainingCap) {
            this.solution = solution;
            this.ub = (int)ub;
            this.value = value;
            this.remainingCap = remainingCap;
        }
        public Node(String solution, int ub, int value, int remainingCap) {
            this.solution = solution;
            this.ub = ub;
            this.value = value;
            this.remainingCap = remainingCap;
        }
    }

    class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return b.ub - a.ub; // to fool the priority queue into giving max ub on head
        }
    }

    public void getMaxProfit() {
        int maxValue = 0;
        String maxString = "";
        List<Integer> identifierList = Arrays.stream(identifiers) // IntStream
                .boxed() // Stream<Integer>
                .collect(Collectors.toList());
        @SuppressWarnings("unchecked")
        ArrayList<Integer> sortedIdentifers = new ArrayList(identifierList);
        Collections.sort(sortedIdentifers, (right, left) -> Double.compare(vWRatio[identifierList.indexOf((left))],
                vWRatio[identifierList.indexOf((right))]));
        Comparator<Node> comparator = new NodeComparator();
        // create initial node
        PriorityQueue<Node> pq = new PriorityQueue<Node>(n, comparator);
        pq.add(new Node("",vWRatio[sortedIdentifers.get(0)]*capacity,0,capacity));
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            System.out.printf("Node solution %s, upper bound %d, value = %d, remaining capacity %d\n", temp.solution,
                    temp.ub, temp.value, temp.remainingCap);
            if (temp.ub <= maxValue) {
                break; // found max value
            }
            if (temp.solution.length() == n - 1) {
                if (temp.remainingCap >= weights[temp.solution.length()]) {
                    int lastVal = values[temp.solution.length()];
                    if (temp.value + lastVal > maxValue) {
                        maxValue = temp.value + lastVal;
                        maxString = temp.solution + "1";
                    }
                } else {
                    if (temp.value > maxValue) {
                        maxValue = temp.value;
                        maxString = temp.solution + "0";
                    }
                }
            } else {
                Node temp1; // take the next item
                int lastVal = values[temp.solution.length()];
                int lastWeight = weights[temp.solution.length()];
                int temp1UB = getUB(temp.solution.length(), temp.value + lastVal, temp.remainingCap - lastWeight,
                        sortedIdentifers);
            }

        }
    }

    // method takes numSeen and goes over the remaining largest v/w items and tries
    // to pick them up
    // finding the ub in the process
    public int getUB(int numSeen, int combinedVal, int remainingCap, ArrayList<Integer> sortedIdentifers) {
        return 0;
    }
}