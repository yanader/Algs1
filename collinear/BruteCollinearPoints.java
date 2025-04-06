/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

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

        LineSegment[] temp = new LineSegment[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
    }

    // the number of line segments
    public int numberOfSegments() {
        return -1;
    }

    // the line segments
    public LineSegment[] segments() {
        return null;
    }

    public static void main(String[] args) {

    }
}
