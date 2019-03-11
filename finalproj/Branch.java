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

        public Node() {
            // solution: string 01010 where length=levels in the tree and 0: not taken
            String solution = "";
            int ub = 0;
            int value = 0;
            int remainingCap = capacity;
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
        List<Integer> identifierList = Arrays.stream(identifiers) // IntStream
                .boxed() // Stream<Integer>
                .collect(Collectors.toList());
        @SuppressWarnings("unchecked")
        ArrayList<Integer> sortedIdentifers = new ArrayList(identifierList);
        Collections.sort(sortedIdentifers, (right, left) -> Double.compare(vWRatio[identifierList.indexOf((left))],
                vWRatio[identifierList.indexOf((right))]));
        Comparator<Node> comparator = new NodeComparator();
        // create initial node
        PriorityQueue<Node> pq = new PriorityQueue<Node>(1, comparator);
        Node temp, temp1, temp2;
        pq.add(new Node());
        
    }
}