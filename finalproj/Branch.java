import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
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

	class NodeComparator implements Comparator<Node> {
		public int compare(Node a, Node b) {
			return Double.compare(b.ub, a.ub); // to fool the priority queue into giving max ub on head
		}
	}

	public void getMaxProfit() {
		int maxValue = 0;
		int[] pickedUpItems = new int[n];
		String maxString = "";

		// sorts identifiers based on v/w ratio
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
		pq.add(new Node("", vWRatio[sortedIdentifers.get(0)] * capacity, 0, capacity));
		System.out.printf("capacity = %d, max vWRatio = %f\n", capacity, vWRatio[sortedIdentifers.get(0)]);
		while (!pq.isEmpty()) {

			Node temp = pq.poll();
			if (temp.ub <= maxValue) {
				// all the other paths have upperbounds less than already found solution
				break;
			}

			// checks if a node is the last possible parent in it's chain and finds it's
			// value
			// to increase maxValue if applicable
			if (temp.solution.length() == n - 1) {
				// last parent if it is now choosing to take the last item
				if (temp.remainingCap >= weights[sortedIdentifers.get(temp.solution.length()) - 1]) {
					int lastVal = values[sortedIdentifers.get(temp.solution.length()) - 1];
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
				int lastVal = values[sortedIdentifers.get(temp.solution.length()) - 1];
				int lastWeight = weights[sortedIdentifers.get(temp.solution.length()) - 1];

				if (temp.remainingCap >= weights[sortedIdentifers.get(temp.solution.length()) - 1]) {
					Node temp1; // take the next item
					double temp1UB = getUB(temp.solution.length()+1, temp.value + lastVal, temp.remainingCap - lastWeight,
							sortedIdentifers);
					temp1 = new Node(temp.solution + "1", temp1UB, temp.value + lastVal,
							temp.remainingCap - lastWeight);
					pq.add(temp1);
				}
				Node temp2;
				double temp2UB = getUB(temp.solution.length()+1, temp.value, temp.remainingCap, sortedIdentifers);
				temp2 = new Node(temp.solution + "0", temp2UB, temp.value, temp.remainingCap);
				pq.add(temp2);
			}
		}
		int maxWeight = 0;
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

	// method takes numSeen and goes over the remaining largest v/w items and tries
	// to pick them up
	// finding the ub in the process
	public double getUB(int numSeen, int combinedVal, int remainingCap, ArrayList<Integer> sortedIdentifers) {
		// get iterator starting at numSeen
		// System.out.printf("numSeen = %d, combinedVal = %d, remainingCap=%d\n",
		// numSeen, combinedVal, remainingCap);
		double upperBound = combinedVal;
		int tempCapacity = remainingCap;
		ListIterator<Integer> li = sortedIdentifers.listIterator(numSeen);

		while (li.hasNext() && tempCapacity > 0) {
			// try to pick up item
			int picked = li.next();
			if (weights[picked - 1] <= tempCapacity) {
				upperBound += values[picked - 1];
				tempCapacity -= weights[picked - 1];
			} else {
				double fractionalUB;
				fractionalUB = vWRatio[picked - 1] * (tempCapacity);
				upperBound += fractionalUB;
				tempCapacity = 0;
			}
		}
		return upperBound;
	}
}
