/*
 * Write a program BruteCollinearPoints.java
 * that examines 4 points at a time and checks whether they all
 * lie on the same line segment, returning all such line segments.
 *
 * To check whether the 4 points p, q, r, and s are collinear,
 * check whether the three slopes between p and q, between p and r,
 * and between p and s are all equal.
 */

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> Segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException("points is null");
        }
        int length = points.length;
        int number = 0;
        for (int i = 0; i < length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("points is null");
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                else if (points[i].equals(points[j])) {
                    throw new IllegalArgumentException("points is null");
                }
            }
        }


        Point[] copy = points;
        Arrays.sort(copy);


        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (isCollinear(points, i, j, k) == false) {
                        continue;
                    }
                    else {
                        for (int l = k + 1; l < length; l++) {
                            if (isCollinear(points, i, j, l)) {
                                number += 1;
                                LineSegment line = new LineSegment(copy[i], copy[l]);
                                Segments.add(line);
                            }

                        }

                    }

                }
            }
        }
    }

    public int numberOfSegments() {    // the number of line segments
        return Segments.size();

    }

    public LineSegment[] segments() {    // the line segments
        return Segments.toArray(new LineSegment[0]);

    }

    private boolean isCollinear(Point[] points, int i, int j, int k) {
        double firstslope = points[i].slopeTo(points[j]);
        double secondslope = points[i].slopeTo(points[k]);
        return firstslope == secondslope;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        //StdDraw.enableDoubleBuffering();
        //StdDraw.setXscale(0, 32768);
        //StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            //p.draw();
        }
        //StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            //segment.draw();
        }
        StdOut.println(collinear.numberOfSegments());
        //StdDraw.show();
    }

}
