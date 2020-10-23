/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double T;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        int col, row;
        double[] xTrials;
        xTrials = new double[trials];
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        for (int i = 0; i < trials; i++) {
            Percolation temp = new Percolation(n);
            // System.out.println(i);
            while (!temp.percolates()) {
                col = StdRandom.uniform(n) + 1;
                row = StdRandom.uniform(n) + 1;


                if (temp.isOpen(row, col)) {
                    continue;
                }
                else {
                    temp.open(row, col);
                }


            }
            xTrials[i] = (double) temp.numberOfOpenSites() / (n * n);
        }

        mean = StdStats.mean(xTrials);
        stddev = StdStats.stddev(xTrials);

        T = (double) (trials);


    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (stddev * 1.96) / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (stddev * 1.96) / Math.sqrt(T);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats temp = new PercolationStats(200, 100);

        StdOut.println("mean                    = " + (temp.stddev));
        StdOut.println("stddev                  = " + temp.stddev());
        StdOut.println(
                "95% confidence interval = [" + temp.confidenceLo() + ", " + temp.confidenceHi()
                        + "]");


    }


}
