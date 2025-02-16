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
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal grid size");
        }
        // There will need to be an adjustment made for the fact that we want to act from index 1 (check spec)
        sites = new boolean[n + 1][n + 1];
        sideLength = n;
        openSiteCount = 0;
        topSite = 0;
        bottomSite = n * n + 2
                - 1; // bottom relares only to the index of the bottom site in the WeightedQuickUnionUF (Not in the 2d array)
        ufOpenSiteConnections = new WeightedQuickUnionUF(n * n + 2);
        ufFilledSiteConnections = new WeightedQuickUnionUF(n * n + 2);

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validCoordinates(row, col)) {
            throw new IllegalArgumentException("Invalid Coordinates");
        }
        else {
            sites[row][col] = true;
            openSiteCount++;
            connectOpenedSite(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    private void connectOpenedSite(int x, int y) {
        // TODO: connect opened sites in the weighted quick union at position x,y converted to 1 dimensional
    }

    private int rowColToOneDimensional(int x, int y) {
        // TODO: convert the x, y coordinate provide (from sites) to the index that can be used in the weighted quick union
        return -1;
    }

    private boolean validCoordinates(int x, int y) {
        return x >= 1 && y >= 1 && x <= sideLength && y <= sideLength;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(19);
        System.out.println(p.sites[1][1]);

    }
}
