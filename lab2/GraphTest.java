// driver program to demonstrate MyGraph.java
public class GraphTest {

    public static void main(String[] args) {
        MyGraph g = new MyGraph();
        try {
            g.readfile_graph("mytest4.txt");
            //System.out.println(g.connectCheck());
            System.out.println(g.bipartiteCheck());
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}
