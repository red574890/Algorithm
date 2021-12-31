import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BoggleSolver {
    private static final int R = 26;
    private Node root = new Node();

    private char[] board;
    private int rows;
    private int cols;

    private static class Node {
        private boolean isWord;
        private Node[] next = new Node[R];
    }

    private void put(String key) {
        root = put(root, key, 0);
    }

    private Node put(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.isWord = true;
            return x;
        }
        char c = key.charAt(d);
        x.next[c - 'A'] = put(x.next[c - 'A'], key, d + 1); // A = 65
        return x;

    }

    private boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        if (get(root, key, 0) == null) {
            return false;
        }
        else {
            return get(root, key, 0).isWord;
        }
    }


    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c - 'A'], key, d + 1);

    }


    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException("the argument to BoggleSolver() is null\n");

        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i] == null) {
                throw new IllegalArgumentException("the argument to BoggleSolver() is null\n");
            }
            put(dictionary[i]);

        }


    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null)
            throw new IllegalArgumentException("the argument to getAllValidWords() is null\n");

        if (this.cols != board.cols() || this.rows != board.rows()) {
            this.cols = board.cols();
            this.rows = board.rows();
            this.board = new char[this.cols * this.rows];
        }

        int idx = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.board[idx] = board.getLetter(i, j);
                idx += 1;
            }

        }

        SET<String> res = DFS();
        return res;

    }

    private SET<String> DFS() {
        SET<String> words = new SET<String>();
        for (int i = 0; i < this.board.length; i++) {
            boolean[] marked = new boolean[this.cols * this.rows];
            DFS(i, "", words, marked, root);
        }
        return words;

    }

    private void DFS(int visit, String input, SET<String> words, boolean[] marked, Node n
    ) {

        boolean[] newmarked = marked.clone();

        int current_row = get_row(visit);
        int current_col = get_col(visit);
        char c = board[visit];
        Node nextone = n.next[c - 'A'];
        if (c == 'Q' && nextone != null)
            nextone = nextone.next['U' - 'A'];
        if (nextone == null) return;


        ArrayList<Integer> next = next_visit_points(current_row, current_col, newmarked);
        if (next.isEmpty()) {
            return;
        }
        else if (newmarked[visit]) {
            return;
        }
        else {
            // mark visited point
            newmarked[visit] = true;


            String text = text_convert(String.valueOf(this.board[visit]));
            input = input.concat(text);


            if (contains(input) && input.length() >= 3) {
                words.add(input);
            }

            for (int i = 0; i < next.size(); i++) {
                text = input.concat(text_convert(String.valueOf(this.board[next.get(i)])));
                if (contains(text) && text.length() >= 3) {
                    words.add(text);
                }
                //   StdOut.print("Next ");
                //StdOut.println();
                DFS(next.get(i), input, words, newmarked, nextone);
            }
        }


    }


    private int get_row(int n) {
        int row = n / this.cols;
        return row;

    }

    private int get_col(int n) {
        int col = n % this.cols;
        return col;
    }

    private int get_location_by_row_col(int row, int col) {
        return row * this.cols + col;
    }

    private ArrayList<Integer> next_visit_points(int row, int col, boolean[] marked) {
        ArrayList<Integer> points = new ArrayList<Integer>();
        int temp;
        // up

        if (row - 1 >= 0) {
            temp = get_location_by_row_col(row - 1, col);
            if (!marked[temp]) points.add(temp);
        }

        // down
        if (row + 1 < this.rows) {
            temp = get_location_by_row_col(row + 1, col);
            if (!marked[temp]) points.add(temp);

        }

        // left
        if (col - 1 >= 0) {
            temp = get_location_by_row_col(row, col - 1);
            if (!marked[temp]) points.add(temp);

        }


        // right
        if (col + 1 < this.cols) {
            temp = get_location_by_row_col(row, col + 1);
            if (!marked[temp]) points.add(temp);

        }

        // upleft
        if (row - 1 >= 0 && col - 1 >= 0) {
            temp = get_location_by_row_col(row - 1, col - 1);
            if (!marked[temp]) points.add(temp);

        }


        // upright
        if (row - 1 >= 0 && col + 1 < this.cols) {
            temp = get_location_by_row_col(row - 1, col + 1);
            if (!marked[temp]) points.add(temp);

        }

        // downleft
        if (row + 1 < this.rows && col - 1 >= 0) {
            temp = get_location_by_row_col(row + 1, col - 1);
            if (!marked[temp]) points.add(temp);

        }

        // downright
        if (row + 1 < this.rows && col + 1 < this.cols) {
            temp = get_location_by_row_col(row + 1, col + 1);
            if (!marked[temp]) points.add(temp);
        }

        return points;

    }

    private String text_convert(String text) {
        if (text.equals("Q")) {
            return "QU";
        }
        else {
            return text;
        }

    }


    //}

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (!contains(word)) return 0;
        
        if (word.length() == 3 || word.length() == 4) {
            return 1;
        }
        else if (word.length() == 5) {
            return 2;
        }
        else if (word.length() == 6) {
            return 3;
        }
        else if (word.length() == 7) {
            return 5;
        }
        else if (word.length() > 7) {
            return 11;
        }
        return 0;
    }

    //}
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }

        StdOut.println("Score = " + score);
    }
}
