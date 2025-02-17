/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // The structure here is that we use a 2d array to track which sites are actually open or closed
    // We use the WeightedQuickUnionUF data type to track connections between sites.
    // The 2D array has a "dead" row and column so that coordinates start at 1, 1 but the WeightQuickUnion
    // Data type does not (so is instantiated with "n" (instead of n + 1 like in the 2D array)

    private boolean[][] sites;
    private int sideLength;
    private int openSiteCount;
    private int topSite;
    // This and the attribute below represent the top ad bottom sites in the 1D array/WeightQuickUnionUF
    private int bottomSite;
    private WeightedQuickUnionUF ufOpenSiteConnections;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal grid size");
        }
        sites = new boolean[n + 1][n + 1];
        sideLength = n;
        openSiteCount = 0;
        topSite = 0;
        // bottom relates only to the index of the bottom site in the WeightedQuickUnionUF (Not in the 2d array)
        bottomSite = n * n + 2 - 1;
        ufOpenSiteConnections = new WeightedQuickUnionUF(n * n + 2);

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
        // .connected is a deprecated method but would achieve the same thing.
        // return ufOpenSiteConnections.connected(topSite, bottomSite);
        return ufOpenSiteConnections.find(topSite) == ufOpenSiteConnections.find(bottomSite);
    }

    private void connectOpenedSite(int x, int y) {
        // TODO: connect opened sites in the weighted quick union at position x,y converted to 1 dimensional
    }

    private int rowColToOneDimensional(int x, int y) {
        // I'm removing 1 from x here to account for the fact that the user will give a row number instead of a strict index
        // It's possible that I may need to remove one from y to achieve the same thing in the other dimension.
        return ((x - 1) * sideLength) + y;
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
