import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashSet;

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
			System.out.println(i + " compare " + (i <= nedges) + " nedges " + nedges);
			x = sc.nextInt();
			y = sc.nextInt();
			System.out.println("x  " + x + "  y:  " + y + " i " + i);
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
			System.out.println("Warning: no edge --" + x + ", " + y);
		x.edges.remove(y);
		x.degree--;
	}

	void print_graph() {
		for (Vertex v : vertices) {
			System.out.println("vertex: " + v.key);
			for (Vertex w : v.edges)
				System.out.print("  adjacency list: " + w.key);
			System.out.println();
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

	//Topological sort via source removal
	public ArrayList<Integer> topSortGenerator(String filename){
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		//find vertices with degree 0
		for(int i=0;i<nvertices;i++){
			System.out.println("degree of vertex "+vertices.get(i).key+" is "+vertices.get(i).degree);
		}
		return sorted;
	}

}
