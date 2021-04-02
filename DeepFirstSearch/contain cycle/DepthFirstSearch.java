import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch {
    private boolean[] marked;
    public int[] edgeTo;
    public int[] edgeTo1;
    private int s;
    private Graph g;


    public DepthFirstSearch(Graph G, int start) {

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        g = G;
        s = start;
        dfs(G, s);

    }

    private void dfs(Graph G, int v) {
        //System.out.println(v);
        //System.out.println("hi");
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                //System.out.println(w);
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public void printedgeTo() {
        for (int i = 0; i < edgeTo.length; i++) {
            System.out.println(edgeTo[i]);
        }

    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
            System.out.println(x);
        }
        return path;
    }

    public boolean isCycle() {
        int n = 0;
        Set<Integer> visited = new HashSet<Integer>();
        while (n < edgeTo.length) {
            if (Cycle(n, n, visited, n)) {
                return true;
            }
            n += 1;
            visited.clear();
        }

        return false;

    }

    private boolean Cycle(int start, int n, Set visited, int last) {
        visited.add(n);

        for (int w : g.adj(n)) {
            //System.out.print("w: ");
            //System.out.println(w);
            //System.out.print("start: ");
            //System.out.println(start);
            if (w == start && last != w) {
                // System.out.println("Ya");
                return true;
            } else if (!visited.contains(w)) {
                last = n;
                return Cycle(start, w, visited, last);
            }
        }
        return false;
    }

    public void test(int n) {
        for (int w : g.adj(n)) {
            System.out.println(w);
        }
    }


    public static void main(String[] args) {
        Graph test = new Graph(4);
        //test.addEdge(0, 1);
        //test.addEdge(0, 2);
        //test.addEdge(0, 6);
        //test.addEdge(0, 5);
        //test.addEdge(5, 4);
        //test.addEdge(4, 6);
        //test.addEdge(1, 3);
        //test.addEdge(3, 2);
        //test.addEdge(2, 4);
        //test.addEdge(4, 6);
        test.addEdge(0, 1);
        test.addEdge(1, 2);
        test.addEdge(3, 2);
        test.addEdge(3, 0);

        DepthFirstSearch D = new DepthFirstSearch(test, 0);

        //System.out.println(D.edgeTo[0]);
        //System.out.println(D.edgeTo[1]);
        //System.out.println(D.edgeTo[2]);
        //System.out.println(D.edgeTo[3]);
        //D.test(3);
        System.out.println(D.isCycle());


        //System.out.println(D.pathTo(6));


    }

}
