import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class Branch {
	int n; // number of items
	int[] identifiers;
	int[] weights;
	int[] values;
	int capacity;
	int maxWeight;
	int maxValue;
	String maxString;
	double[] vWRatio;

	public Branch(int n, int[] identifier, int[] value, int[] weight, int capacity) {
		this.maxString = "";
		this.maxWeight = 0;
		this.maxValue = 0;
		this.n = n;
		this.identifiers = identifier;
		this.values = value;
		this.weights = weight;
		this.capacity = capacity;
		// populate v/w ratios for the items
		vWRatio = new double[n];
		for (int i = 0; i < n; i++) {
			vWRatio[i] = (double) values[i] / weights[i];
		}
	}
	// A node represents a node in the state space tree
	class Node {
		String solution;
		double ub;
		int value;
		int remainingCap;

		public Node(String solution, double ub, int value, int remainingCap) {
			this.solution = solution;
			this.ub = ub;
			this.value = value;
			this.remainingCap = remainingCap;
		}
	}

	// comparator for ordering nodes in priority queue
	// node with higher upper bound comes first in priority queue
	class NodeComparator implements Comparator<Node> {
		public int compare(Node a, Node b) {
			return Double.compare(b.ub, a.ub);
		}
	}

	public void getMaxProfit() {
		// start timer
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
		int[] pickedUpItems = new int[n];
		
		// sort identifiers based on the the v/w of the items they refer to
		List<Integer> identifierList = Arrays.stream(identifiers)
				.boxed()
				.collect(Collectors.toList());
		@SuppressWarnings("unchecked")
		ArrayList<Integer> sortedIdentifers = new ArrayList(identifierList);
		Collections.sort(sortedIdentifers, (right, left) -> Double.compare(vWRatio[identifierList.indexOf((left))],
				vWRatio[identifierList.indexOf((right))]));

		Comparator<Node> comparator = new NodeComparator();

		// create root node and insert it into the queue
		PriorityQueue<Node> pq = new PriorityQueue<Node>(n, comparator);
		// new node has upper bound = maximum v/w *capacity, and value =0
		pq.add(new Node("", vWRatio[sortedIdentifers.get(0)] * capacity, 0, capacity));

		// explore state space tree for 30 seconds
		// or until we explore the entire tree
		// or until we prune all the remaining branches
		while (elapsedTime < 30 * 1000 && !pq.isEmpty()) {
			Node temp = pq.poll();
			if (temp.ub <= maxValue) {
				// all the other paths have upperbounds less than already found solution
				break;
			}

			// checks if a node is the last possible parent in it's branch and finds it's value
			// increase maxValue if applicable
			if (temp.solution.length() == n - 1) {
				// last parent if it can take the last item
				if (temp.remainingCap >= weights[sortedIdentifers.get(temp.solution.length()) - 1]) {
					int lastVal = values[sortedIdentifers.get(temp.solution.length()) - 1];
					if (temp.value + lastVal > maxValue) {
						maxValue = temp.value + lastVal;
						maxString = temp.solution + "1";
					}
				} 
				// last parent if it can't take the last item
				else {
					if (temp.value > maxValue) {
						maxValue = temp.value;
						maxString = temp.solution + "0";
					}
				}
			} 
			else {
				// value and weight of next best v/w item
				int lastVal = values[sortedIdentifers.get(temp.solution.length()) - 1];
				int lastWeight = weights[sortedIdentifers.get(temp.solution.length()) - 1];

				// create and enque left and right node, for the next best v/w item being taken or not
				// create right node only if the next best item can be picked up
				if (temp.remainingCap >= weights[sortedIdentifers.get(temp.solution.length()) - 1]) {
					Node right; // take the next item
					// upper bound is value of next best item + value of parent + potential value
					double rightUB = getUB(temp.solution.length() + 1, temp.value + lastVal,
							temp.remainingCap - lastWeight, sortedIdentifers);
					right = new Node(temp.solution + "1", rightUB, temp.value + lastVal,
							temp.remainingCap - lastWeight);
					pq.add(right);
				}
				Node left; // don't take the next item
				// upper bound is value of next best item + potential value
				double leftUB = getUB(temp.solution.length() + 1, temp.value, temp.remainingCap, sortedIdentifers);
				left = new Node(temp.solution + "0", leftUB, temp.value, temp.remainingCap);
				pq.add(left);
			}
			// update timer
			elapsedTime = (new Date()).getTime() - startTime;
		}

		// each 1 at position i represents that the i'th best v/w item was picked up
		// each 0 at position i represents that the i'th best v/w item wasn't picked up
		for (int i = 0; i < maxString.length(); i++) {
			if (maxString.charAt(i) == '1') {
				maxWeight += weights[sortedIdentifers.get(i) - 1];
				pickedUpItems[sortedIdentifers.get(i) - 1] = 1;
			}
		}

		System.out.printf("Using Branch and Bound the best feasible solution found: Value %d, Weight %d\n", maxValue,
				maxWeight);
		for (int i = 0; i < n; i++) {
			if (pickedUpItems[i] == 1) {
				System.out.printf("%d ", i + 1);
			}
		}
		System.out.println();
	}

	// Goes over the remaining best v/w items, and picks them up
	// if it has the capacity to
	// and picks up a fraction of them if it can't pick up the entire item

	public double getUB(int numSeen, int combinedVal, int remainingCap, ArrayList<Integer> sortedIdentifers) {
		// upper bound starts at parent value for left and parent value + next item value for right
		double upperBound = combinedVal;
		int tempCapacity = remainingCap;
		ListIterator<Integer> li = sortedIdentifers.listIterator(numSeen);

		while (li.hasNext() && tempCapacity > 0) {
			int picked = li.next();
			// try to pick up item whole
			if (weights[picked - 1] <= tempCapacity) {
				upperBound += values[picked - 1];
				tempCapacity -= weights[picked - 1];
			}
			// pick up fractional item 
			else {
				double fractionalUB;
				fractionalUB = vWRatio[picked - 1] * (tempCapacity);
				upperBound += fractionalUB;
				tempCapacity = 0;
			}
		}
		return upperBound;
	}
}
