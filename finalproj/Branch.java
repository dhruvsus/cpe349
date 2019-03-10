import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Branch {
    int n; // number of items
    int[] weights;
    int[] values;
    int capacity;

    public Branch(int n, int[] value, int[] weight, int capacity) {
        this.n = n;
        this.values = value;
        this.weights = weight;
        this.capacity = capacity;
    }

    class Node {
        String solution;
        int ub;
        int value;

        public Node() {
            // solution: string 01010 where length=levels in the tree and 0: not taken
            solution = "";
            ub = 0;
            value = 0;
        }
    }

    class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return b.ub - a.ub; // to fool the priority queue into giving max ub on head
        }
    }

    public void getMaxProfit() {
        Comparator<Node> comparator = new NodeComparator();
        // create initial node
        PriorityQueue<Node> pq = new PriorityQueue<Node>(1, comparator);
        Node temp;
        pq.add(new Node());
        while (!pq.isEmpty()) {
            temp = pq.poll();
            System.out.printf("Node string %s, ub %d value %d\n", temp.solution, temp.ub, temp.value);
        }
    }
}