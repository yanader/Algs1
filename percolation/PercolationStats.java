/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thresholds;
    private int size;
    private int t;
    private double stdDevConstant;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid Arguments.");
        }
        else {
            stdDevConstant = 1.96;
            size = n;
            t = trials;
            thresholds = new double[trials];
            for (int i = 0; i < trials; i++) {
                thresholds[i] = runTrial(n);
            }

        }
    }

    private double runTrial(int n) {
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int x = StdRandom.uniformInt(1, n + 1);
            int y = StdRandom.uniformInt(1, n + 1);
            if (p.isOpen(x, y)) {
                continue;
            }
            else {
                p.open(x, y);
            }
        }
        return p.numberOfOpenSites() / ((size * size) * 1.0);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - stdDevConstant * Math.sqrt(stdDevSquared() / t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + stdDevConstant * Math.sqrt(stdDevSquared() / t));
    }

    private double stdDevSquared() {
        return StdStats.stddev(thresholds) * StdStats.stddev(thresholds);
    }

    // test client (see below)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(size, trials);
        StdOut.println("mean\t\t        = " + Double.toString(ps.mean()));
        StdOut.println("stddev\t\t        = " + Double.toString(ps.stddev()));
        StdOut.println("95% confidence interval = [" + Double.toString(ps.confidenceLo()) + ", "
                               + Double.toString(ps.confidenceHi()) + "]");
    }


}
