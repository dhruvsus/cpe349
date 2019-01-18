// driver program to demonstrate MyGraph.java
public class GraphTest {

	public static void main(String[] args) {
		TopSorter sort = new TopSorter();
		try {
			sort.readfile_graph("mytest4.txt");
			sort.topSortGenerator("mytest4.txt");
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
	}
}
