import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

class TopSorter {
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	boolean directed;
	int nvertices;
	int nedges;
	int numComp;

	public TopSorter() {
	}

	void readfile_graph(String filename) throws FileNotFoundException {
		int x, y;
		// read the input
		FileInputStream in = new FileInputStream(new File(filename));
		Scanner sc = new Scanner(in);
		int temp = sc.nextInt(); // if 1 directed; 0 undirected
		directed = (temp == 1);
		nvertices = sc.nextInt();
		for (int i = 0; i <= nvertices - 1; i++) {
			Vertex tempv = new Vertex(i + 1); // kludge to store proper key starting at 1
			vertices.add(tempv);
		}

		nedges = sc.nextInt(); // m is the number of edges in the file
		int nedgesFile = nedges;
		for (int i = 1; i <= nedgesFile; i++) {

			x = sc.nextInt();
			y = sc.nextInt();

			insert_edge(x, y, directed);
		}
		sc.close();
		// order edges to make it easier to see what is going on
		for (int i = 0; i <= nvertices - 1; i++) {
			Collections.sort(vertices.get(i).edges);
		}
	}

	static void process_vertex_early(Vertex v) {
		timer++;
		v.entry_time = timer;
		// System.out.printf("entered vertex %d at time %d\n",v.key, v.entry_time);
	}

	static void process_vertex_late(Vertex v) {
		timer++;
		v.exit_time = timer;
		// System.out.printf("exit vertex %d at time %d\n",v.key , v.exit_time);
	}

	static void process_edge(Vertex x, Vertex y) {
		int c = edge_classification(x, y);
		if (c == BACK)
			System.out.printf("back edge (%d,%d)\n", x.key, y.key);
		else if (c == TREE)
			System.out.printf("tree edge (%d,%d)\n", x.key, y.key);
		else if (c == FORWARD)
			System.out.printf("forward edge (%d,%d)\n", x.key, y.key);
		else if (c == CROSS)
			System.out.printf("cross edge (%d,%d)\n", x.key, y.key);
		else
			System.out.printf("edge (%d,%d)\n not in valid class=%d", x.key, y.key, c);
	}

	static void initialize_search(TopSorter g) {
		for (Vertex v : g.vertices) {
			v.processed = v.discovered = false;
			v.parent = null;
		}
	}

	static final int TREE = 1, BACK = 2, FORWARD = 3, CROSS = 4;
	static int timer = 0;

	static int edge_classification(Vertex x, Vertex y) {
		if (y.parent == x)
			return (TREE);
		if (y.discovered && !y.processed)
			return (BACK);
		if (y.processed && (y.entry_time > x.entry_time))
			return (FORWARD);
		if (y.processed && (y.entry_time < x.entry_time))
			return (CROSS);
		System.out.printf("Warning: self loop (%d,%d)\n", x, y);
		return -1;
	}

	void insert_edge(int x, int y, boolean directed) {
		Vertex one = vertices.get(x - 1);
		Vertex two = vertices.get(y - 1);
		one.edges.add(two);
		vertices.get(x - 1).degree++;
		if (!directed)
			insert_edge(y, x, true);
		else
			nedges++;
	}

	void remove_edge(Vertex x, Vertex y) {
		if (x.degree < 0)

			x.edges.remove(y);
		x.degree--;
	}

	void print_graph() {
		for (Vertex v : vertices) {

			for (Vertex w : v.edges)
				System.out.print("  adjacency list: " + w.key);

		}
	}

	class Vertex implements Comparable<Vertex> {
		int key;
		int degree;
		int component;
		int color = -1; // use mod numColors, -1 means not colored
		boolean discovered = false;
		boolean processed = false;
		int entry_time = 0;
		int exit_time = 0;
		Vertex parent = null;
		ArrayList<Vertex> edges = new ArrayList<Vertex>();

		public Vertex(int tkey) {
			key = tkey;
		}

		public int compareTo(Vertex other) {
			if (this.key > other.key) {
				return 1;
			} else if (this.key < other.key) {
				return -1;
			} else
				return 0;
		}
	}

	Vertex unProcessedV() {
		for (Vertex v : vertices) {
			if (!v.processed)
				return v;
		}
		return null;
	}

	// Topological sort via source removal
	static ArrayList<Integer> topSortGenerator(String filename) {
		TopSorter sorter=new TopSorter();
		try {
			sorter.readfile_graph(filename);
			//
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		// build adjacency matrix
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer> adjList[] = new ArrayList[sorter.nvertices];
		for (int i = 0; i < sorter.nvertices; i++) {
			adjList[i] = new ArrayList<>();
		}
		// for each vertex v, add v's key to the adjacency list of it's edges:w
		for (Vertex v : sorter.vertices) {
			for (Vertex w : v.edges) {
				// w.key-1 since array indexing starts at 0 but our vertices are keyed naturally
				adjList[w.key - 1].add(v.key);
			}
		}
		ArrayList<Integer> topSorted = new ArrayList<Integer>();
		// queue for vertices with degree 0
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// enqueue vertices with degree 0
		for (int i = 0; i < sorter.nvertices; i++) {
			if (adjList[i].size() == 0) {
				// add i+1 to convert index to vertex key
				queue.add(i + 1);
			}
		}

		while (queue.size() != 0) {
			int vKey = queue.poll();
			// insert vertKey into topSorted
			topSorted.add(vKey);

			// remove vertKey from adjacency list of each neighbour
			// find keys of vertices that have edgex vertKey---->wKey
			// .get(vKey-1) to convert key to index
			for (Vertex w : sorter.vertices.get(vKey - 1).edges) {
				// for each of these, remove vKey from it's adjacency list
				// w.key-1 index for adjacency list
				adjList[w.key - 1].remove((Integer) vKey);
				// if new length of adjList[w.key-1] is 0, enqueue it
				if (adjList[w.key - 1].size() == 0) {
					queue.add(w.key);

				}
			}
		}

		// add a -1 to the arraylist for the elements that
		// couldn't be sorted = nvertices-topSorted.size
		int numNeg = sorter.nvertices - topSorted.size();
		for (int i = 0; i < numNeg; i++) {
			topSorted.add(-1);
		}
		return topSorted;
	}

}
