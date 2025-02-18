/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] sites;
    private int sideLength;
    private int openSiteCount;
    private int topSite;
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
        bottomSite = n * n + 2 - 1;
        ufOpenSiteConnections = new WeightedQuickUnionUF(n * n + 2);

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validCoordinates(row, col)) {
            throw new IllegalArgumentException("Invalid Coordinates");
        }
        else if (!isOpen(row, col)) {
            sites[row][col] = true;
            openSiteCount++;
            connectOpenedSite(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validCoordinates(row, col)) {
            throw new IllegalArgumentException("Invalid Coordinates");
        }
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validCoordinates(row, col)) {
            throw new IllegalArgumentException("Invalid Coordinates");
        }
        return ufOpenSiteConnections.find(topSite) == ufOpenSiteConnections.find(
                rowColToOneDimensional(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufOpenSiteConnections.find(topSite) == ufOpenSiteConnections.find(bottomSite);
    }

    private void connectOpenedSite(int x, int y) {
        if (x == 1) {
            ufOpenSiteConnections.union(topSite, rowColToOneDimensional(x, y));
        }
        if (x == sideLength) {
            ufOpenSiteConnections.union(rowColToOneDimensional(x, y), bottomSite);
        }
        if (validCoordinates(x - 1, y) && isOpen(x - 1, y)) {
            ufOpenSiteConnections.union(rowColToOneDimensional(x, y),
                                        rowColToOneDimensional(x - 1, y));
        }
        if (validCoordinates(x + 1, y) && isOpen(x + 1, y)) {
            ufOpenSiteConnections.union(rowColToOneDimensional(x, y),
                                        rowColToOneDimensional(x + 1, y));
        }
        if (validCoordinates(x, y - 1) && isOpen(x, y - 1)) {
            ufOpenSiteConnections.union(rowColToOneDimensional(x, y),
                                        rowColToOneDimensional(x, y - 1));
        }
        if (validCoordinates(x, y + 1) && isOpen(x, y + 1)) {
            ufOpenSiteConnections.union(rowColToOneDimensional(x, y),
                                        rowColToOneDimensional(x, y + 1));
        }

    }

    private int rowColToOneDimensional(int x, int y) {
        return ((x - 1) * sideLength) + y;
    }

    private boolean validCoordinates(int x, int y) {
        return x >= 1 && y >= 1 && x <= sideLength && y <= sideLength;
    }


    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        System.out.println(p.percolates());
        p.open(2, 1);
        System.out.println(p.percolates());
        p.open(3, 1);
        System.out.println(p.percolates());
        System.out.println("2, 2 is open: " + p.isOpen(2, 2));
        System.out.println("1, 3 is full: " + p.isFull(1, 3));

    }
}
