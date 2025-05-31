/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class FastCollinearPoints {

    private int segmentCount;
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Null argument failure");
        }
        Arrays.sort(points);
        this.segmentCount = 0;
        segments = new LineSegment[10];
        final int N = points.length;

        // create a copy

        Point[] pointsCopy = new Point[N];
        for (int i = 0; i < N; i++) {
            pointsCopy[i] = points[i];
        }

        // Loop over each point in the original array and, for each,
        // sort the copy in slope order relative to the current point in the loop

        for (int i = 0; i < N; i++) {
            Arrays.sort(pointsCopy, points[i].slopeOrder());
            double slope = findSlope(points[i], pointsCopy);

            if (slope != 9.9) {
                // Once a slope has been found, sort the array by (x,y) coordinate (standard sort)
                Arrays.sort(pointsCopy);

                // Take the first and last point that have either the current slope of -infinity
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

        }
        trimSegmentArray();
    }

    // the number of line segments
    public int numberOfSegments() {
        return -1;
    }

    // the line segments
    public LineSegment[] segments() {
        return null;
    }

    private void trimSegmentArray() {

    }

    private double findSlope(Point p, Point[] points) {
        return -1.0;
    }

    private void grow() {

    }

    public static void main(String[] args) {
        // For testing
    }
}
