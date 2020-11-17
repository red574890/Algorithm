import edu.princeton.cs.algs4.Queue;

public class Board {
    private int[][] tiles;
    private int zrow = 0;
    private int zcol = 0;
    private int N;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] board) {
        if (board == null) throw new NullPointerException("Null blocks");
        tiles = new int[board.length][board.length];
        N = board.length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                tiles[i][j] = board[i][j];
                if (board[i][j] == 0) {
                    zrow = i;
                    zcol = j;
                }
            }
        }

    }

    // string representation of this board
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(N + "\n");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                str.append(String.format("%2d ", tiles[i][j]));
            str.append("\n");
        }

        return str.toString();
    }


    // board dimension n
    public int dimension() {
        return N;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        int n = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != n) {
                    count++;
                }
                n++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int man = 0;
        int n = 1;
        int absi;
        int absj;
        int dim = dimension();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tiles[i][j] == 0 || tiles[i][j] == n) {
                    n++;
                    continue;
                }
                if (tiles[i][j] != n) {
                    if (tiles[i][j] % dim == 0) {
                        absj = N - 1;
                        absi = tiles[i][j] / N - 1;
                    }
                    else {
                        absj = tiles[i][j] % dim - 1;
                        absi = tiles[i][j] / dim;
                    }

                    man += Math.abs(i - absi) + Math.abs(j - absj);
                    // System.out.print("i=");
                    // System.out.print(i);
                    // System.out.print("j=");
                    // System.out.println(j);
                    // System.out.print(tiles[i][j]);
                    // System.out.print(",");
                    // System.out.print("Man");
                    // System.out.println(Math.abs(i - absi) + Math.abs(j - absj));
                }
                n++;
            }
        }
        return man;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 1;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != this.dimension()) return false;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;

    }

    private int[][] exch(int oi, int oj, int ni, int nj) {
        N = tiles.length;
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = tiles[i][j];
            }
        }
        int temp;
        temp = blocks[oi][oj];
        blocks[oi][oj] = blocks[ni][nj];
        blocks[ni][nj] = temp;
        return blocks;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        int[] offsets = { -1, 1, 0, 0 };
        Queue<Board> neighbors = new Queue<Board>();
        for (int i = 0, j = offsets.length - 1; i < offsets.length; i++, j--) {
            int row = zrow + offsets[i];
            int col = zcol + offsets[j];

            if (row < 0 || row >= dimension() || col < 0 || col >= dimension())
                continue;

            Board neighbor = new Board(tiles);
            neighbor.tiles[zrow][zcol] = neighbor.tiles[row][col];
            neighbor.tiles[row][col] = 0;
            neighbor.zrow = row;
            neighbor.zcol = col;

            neighbors.enqueue(neighbor);
        }

        return neighbors;

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int i = 0;
        int j = 0;
        int[][] twin;
        while (tiles[i][j] == 0 || tiles[i][j + 1] == 0) {
            j++;
            if (j >= tiles.length - 1) {
                i++;
                j = 0;
            }
        }
        twin = exch(i, j, i, j + 1);
        return new Board(twin);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tile = { { 1, 0 }, { 2, 3 } };
        Board tiles = new Board(tile);

        System.out.print(tiles.toString());


        System.out.println(tiles.twin().toString());

        System.out.print(tiles.manhattan());


    }

}
