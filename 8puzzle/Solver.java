import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    private static class SearchNode implements Comparable<SearchNode> {
        Board board;
        int manhattan;
        int moves = 0;
        int priority;
        SearchNode previousNode;

        public SearchNode(Board board, SearchNode previousNode) {
            if (board == null) throw new IllegalArgumentException();
            this.board = board;
            this.manhattan = board.manhattan();
            if (previousNode != null) {
                this.moves = previousNode.moves + 1;
                this.previousNode = previousNode;
            }

            this.priority = this.moves + this.manhattan;
        }

        @Override
        public int compareTo(SearchNode o) {
            if (this.priority < o.priority) return -1;
            else if (this.priority > o.priority) return 1;
            else return 0;
        }
    }

    private MinPQ<SearchNode> pq = new MinPQ<>();
    private MinPQ<SearchNode> pq1 = new MinPQ<>();
    private SearchNode curr, twin;
    private boolean solvable = false;
    private List<Board> dequer = new ArrayList<>();


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        curr = new SearchNode(initial, null);
        twin = new SearchNode(initial.twin(), null);
        pq.insert(curr);
        pq1.insert(twin);

        while (!curr.board.isGoal() && !twin.board.isGoal()) {
            curr = pq.delMin();
            twin = pq1.delMin();
            for (Board nb : curr.board.neighbors()) {
                if (curr.previousNode == null || !nb.equals(curr.previousNode.board)) {
                    pq.insert(new SearchNode(nb, curr));
                }
            }
            for (Board nb : twin.board.neighbors()) {
                if (twin.previousNode == null || !nb.equals(curr.previousNode.board)) {
                    pq1.insert(new SearchNode(nb, twin));
                }
            }

        }
        if (curr.board.isGoal()) {
            solvable = true;
        }

    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        else {
            return curr.moves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        SearchNode temp = new SearchNode(curr.board, curr.previousNode);
        if (!isSolvable()) {
            return null;
        }
        else {
            while (temp != null) {
                dequer.add(temp.board);
                temp = temp.previousNode;
            }
            Collections.reverse(dequer);
            return dequer;
        }

    }


    // test client (see below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }

}
