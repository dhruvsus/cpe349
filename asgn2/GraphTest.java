// driver program to demonstrate MyGraph.java
public class GraphTest {

	public static void main(String[] args) {
		TopSorter sort = new TopSorter();
		try {
			sort.readfile_graph("topSortTest1.txt");
			System.out.println(sort.topSortGenerator("topSortTest1"));
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
	}
}
