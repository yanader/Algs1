/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private int segmentCount;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Null argument failure.");
        }
        Arrays.sort(points);
        this.segmentCount = 0;
        int a = 10;
        segments = new LineSegment[a];
        final int N = points.length;
        for (int p1 = 0; p1 < N; p1++) {
            for (int p2 = p1 + 1; p2 < N; p2++) {
                for (int p3 = p2 + 1; p3 < N; p3++) {
                    for (int p4 = p3 + 1; p4 < N; p4++) {
                        double slope1 = points[p1].slopeTo(points[p2]);
                        double slope2 = points[p1].slopeTo(points[p3]);
                        double slope3 = points[p1].slopeTo(points[p4]);

                        // Because we have 5 Points on the same slope, we are checking,
                        // and adding, the segment multiple times.

                        if (slope1 == slope2 && slope1 == slope3) {
                            segments[segmentCount] = new LineSegment(points[p1], points[p4]);
                            segmentCount++;
                            if (segmentCount == segments.length) {
                                grow();
                            }
                        }
                    }
                }
            }
        }
        //////////
        // Double check whether or not this is needed
        //////////
        LineSegment[] temp = new LineSegment[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

