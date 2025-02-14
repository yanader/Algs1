/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // The structure here is that we use a 2d array to track which sites are actually open or closed
    // We use the WeightedQuickUnionUF data type to track connections between sites. (One for open and one for full)
    // In this case =   "open" is that the site has opened
    //                  "full" is that water has managed to reach it because it is connected to the "top" virtual site.

    // I will need some helper functions to do things like translate coordinates into a 1 dimensional coordinate (and maybe vice versa)

    private boolean[][] sites;
    private int sideLength;
    private int openSiteCount;
    private int topSite;
            // This and the attribute below represent the top ad bottom sites in the 1D array/WeightQuickUnionUF
    private int bottomSite;
    private WeightedQuickUnionUF ufOpenSiteConnections;
    private WeightedQuickUnionUF ufFilledSiteConnections;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        sites = new boolean[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return -1;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(19);
        System.out.println(p.sites[1][1]);

    }
}
