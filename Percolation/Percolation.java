public class Percolation {
    private int[] id;
    private int[] sz;
    private boolean[] sites;
    private final int width;
    private final int all;
    private int bsite;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        width = n;
        all = n * n + 2;
        bsite = 0;
        id = new int[all];
        sz = new int[all];
        for (int i = 0; i < all; i++) {
            if (i <= n) {
                id[i] = 0;
                //top fake point//
                sz[i] = n + 1;
            }
            else if (i > n * n - n) {
                id[i] = all - 1;
                sz[i] = n + 1;
            }
            else {
                id[i] = i;
                sz[i] = 1;
            }

        }

        sites = new boolean[n * n + 2];
        sites[0] = true;//top fake point
        sites[all - 1] = true;//bottom fake point

    }

    private int loc(int row, int col) {
        //convert row and col to straight position
        if (row < 1 || row > width || col < 1 || col > width)
            throw new IllegalArgumentException();
        return width * (row - 1) + col;
    }

    private int root(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    private boolean connected(int p, int q) {
        return root(p) == root(q);
    }


    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) return;

        // make smaller root point to larger one
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        //1. open one site 2. check if adjacent is also open 3. if open, then connect to them also
        int position = loc(row, col);
        if (!sites[position]) {
            sites[position] = true;
            bsite += 1;
            if (width == 1 && position == 1) {   // n=1
                union(position, all - 1);
            }
            else if (position % width == 1) {


                // union with right site

                if (sites[position + 1]) {
                    union(position, position + 1);
                }

                if ((position + width) <= (width * width)) {
                    if (sites[position + width]) {
                        union(position, position + width);
                    }
                }

                if ((position - width) > 0) {
                    if (sites[position - width]) {
                        union(position, position - width);
                    }
                }
            }
            else if (position % width == 0) { //last col
                if (sites[position - 1]) {//union with right site
                    union(position, position - 1);
                }

                if ((position + width) <= (width * width)) {
                    if (sites[position + width]) {
                        union(position, position + width);
                    }
                }

                if ((position - width) > 0) {
                    if (sites[position - width]) {
                        union(position, position - width);
                    }
                }
            }
            else {
                if (sites[position - 1]) {//  union with right site
                    union(position, position - 1);
                }

                if (sites[position + 1]) {//  union with right site
                    union(position, position + 1);
                }

                if ((position + width) < (width * width - 1)) {
                    if (sites[position + width]) {
                        union(position, position + width);
                    }
                }

                if ((position - width) > 0) {
                    if (sites[position - width]) {
                        union(position, position - width);
                    }
                }
            }
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int position = loc(row, col);
        return sites[position];
    }


    // is the site (row, col) full?
    /*
     * isFull
     * checks whether or not the current square is connected to the top
     * /and/ the site is open
     * Currently makes all sites 'full'
     */
    public boolean isFull(int row, int col) {
        int position;
        position = loc(row, col);
        return isOpen(row, col) && connected(0, position);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return bsite;
    }


    // does the system percolate?
    public boolean percolates() {
        //  boolean top = false;
        //  boolean bottom = false;
        //  for (int i = 1; i <= width; i++) {
        //  if (sites[i]) {
        //    top = sites[i];
        //  break;
        // }
        /// }

        //  for (int i = 1; i <= width; i++) {
        //  if (sites[all - 1 - i]) {
        //    bottom = sites[i];
        //      break;
        //}
        //}

        //  if (top && bottom) {
        //    return connected(0, all - 1);
        // }
        //else {
        //   return false;
        //}
        return connected(0, all - 1);


    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 2);
        percolation.open(2, 2);
        // percolation.open(2, 2);
        percolation.open(3, 1);
        percolation.isFull(3, 1);


        System.out.print(percolation.isFull(3, 2));


    }
}
