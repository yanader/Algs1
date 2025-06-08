/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private static final int INITIAL_SIZE = 10;
    private int segmentCount;
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Null argument failure");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Null point in array");
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicate points");
                }
            }
        }


        this.segmentCount = 0;
        segments = new LineSegment[INITIAL_SIZE];
        final int N = points.length;

        // create a copy

        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);

        // Loop over each point in the original array and, for each,
        // sort the copy in slope order relative to the current point in the loop

        for (int i = 0; i < N; i++) {
            // Sort pointsCopy in slope order relative to points[i] - each in turn
            Arrays.sort(pointsCopy, points[i].slopeOrder());
            double slope;

            try {
                slope = findSlope(points[i], pointsCopy);
            }
            catch (IllegalStateException e) {
                continue;
            }

            // Once a slope has been found, sort the array by (x,y) coordinate (standard sort)
            Arrays.sort(pointsCopy);

            // Take the first and last point that have either the current slope or -infinity
            Point first = null;
            Point last = null;
            boolean firstNeeded = true;

            for (int j = 0; j < pointsCopy.length; j++) {
                if (points[i].slopeTo(pointsCopy[j]) == slope
                        || points[i].slopeTo(pointsCopy[j]) == Double.NEGATIVE_INFINITY) {
                    if (firstNeeded) {
                        first = pointsCopy[j];
                        firstNeeded = false;
                    }
                    else {
                        last = pointsCopy[j];
                    }
                }
            }

            LineSegment ls = new LineSegment(first, last);

            if (segmentCount == segments.length) {
                grow();
            }

            if (first == points[i]) {
                segments[segmentCount] = ls;
                segmentCount++;
            }

        }
        trimSegmentArray();
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segmentCount;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] returnSegments = new LineSegment[segments.length];
        for (int i = 0; i < returnSegments.length; i++) {
            returnSegments[i] = segments[i];
        }
        return returnSegments;
    }

    private void trimSegmentArray() {
        LineSegment[] temp = new LineSegment[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
    }

    private double findSlope(Point p, Point[] points) {
        int count = 0;
        for (int i = 1; i < points.length; i++) {
            double slope1 = p.slopeTo(points[i - 1]);
            double slope2 = p.slopeTo(points[i]);
            if (slope1 == slope2) {
                count++;
            }
            if (count == 2) {
                return slope2;
            }
        }
        throw new IllegalStateException("No matching slope found");
    }

    private void grow() {
        final int N = segments.length;
        LineSegment[] newArray = new LineSegment[N * 2];
        for (int i = 0; i < N; i++) {
            newArray[i] = segments[i];
        }
        segments = newArray;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
