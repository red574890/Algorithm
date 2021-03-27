import java.util.Stack;

public class DepthFirstSearchC1 {
    private boolean[] marked;
    private int[] edgeTo;
    private String[] colorto;
    private int s;

    public DepthFirstSearchC1(Graphwithattribute G, int start) {

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        colorto = new String[G.V()];
        s = start;
        dfs(G, s);

    }

    private void dfs(Graphwithattribute G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
                colorto[w] = G.colors[v];
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }


    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        return path;
    }

    public boolean isBipartite(Graphwithattribute G) {
        for (int i = 0; i < G.colors.length; i++) {
            if (G.colors[i] == colorto[i]) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        Graphwithattribute A = new Graphwithattribute(7);
        A.addcolor(0, "r");
        A.addcolor(1, "w");
        A.addcolor(2, "w");
        A.addcolor(6, "w");
        A.addcolor(5, "w");
        A.addcolor(3, "r");
        A.addcolor(4, "r");
        A.addEdge(0, 5);
        A.addEdge(0, 1);
        A.addEdge(0, 2);
        A.addEdge(0, 6);
        A.addEdge(1, 3);
        A.addEdge(3, 2);
        A.addEdge(2, 4);
        A.addEdge(4, 6);
        A.addEdge(5, 4);
        DepthFirstSearchC1 Depth = new DepthFirstSearchC1(A, 0);
        System.out.println(Depth.isBipartite(A));


    }

}
