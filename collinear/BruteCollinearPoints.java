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
        segments = new LineSegment[10];
        final int N = points.length;

        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);


        for (int p1 = 0; p1 < N; p1++) {
            for (int p2 = p1 + 1; p2 < N; p2++) {
                for (int p3 = p2 + 1; p3 < N; p3++) {
                    for (int p4 = p3 + 1; p4 < N; p4++) {
                        double slope1 = pointsCopy[p1].slopeTo(pointsCopy[p2]);
                        double slope2 = pointsCopy[p1].slopeTo(pointsCopy[p3]);
                        double slope3 = pointsCopy[p1].slopeTo(pointsCopy[p4]);

                        // Because we have 5 Points on the same slope, we are checking,
                        // and adding, the segment multiple times.

                        if (Double.compare(slope1, slope2) == 0
                                && Double.compare(slope1, slope3) == 0) {
                            Point[] collinear = {
                                    pointsCopy[p1], pointsCopy[p2], pointsCopy[p3], pointsCopy[p4]
                            };
                            Arrays.sort(collinear);
                            segments[segmentCount++] = new LineSegment(collinear[p1],
                                                                       collinear[p4]);
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
        return Arrays.copyOf(segments, segmentCount);
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

        // for (int i = 0; i < points.length; i++) {
        //     System.out.println(points[i]);
        // }

    }
}

