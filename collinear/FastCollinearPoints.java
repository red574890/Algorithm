import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private ArrayList<LineSegment> Segments = new ArrayList<>();

    public FastCollinearPoints(
            Point[] points) { // finds all line segments containing 4 or more points
        if (points == null) throw new IllegalArgumentException();
        int length = points.length;
        for (int i = 0; i < length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                else if (points[i].equals(points[j])) {
                    throw new IllegalArgumentException();
                }
            }
        }
        ArrayList<Point> ends = new ArrayList<>();

        Point[] sortedpoints = points;
        Point p;
        int end = 0;
        int ci = 0;
        int start = 0;


        Arrays.sort(sortedpoints);
        //for (int i = 0; i < length; i++) {
        //    StdOut.println(sortedpoints[i].toString());
        //}
        Point[] sortslop = points;

        for (int i = 0; i < length - 3; i++) {

            Arrays.sort(sortslop);
            p = sortslop[i];
            Arrays.sort(sortslop, p.slopeOrder());
            //System.out.println(p.toString());

            //for (int j = 0; j < length; j++) {
            //    StdOut.print(sortslop[j].toString());
            //    StdOut.print(" , ");
            ///     StdOut.println(p.slopeTo(sortslop[j]));
            //}

            //StdOut.println("-----");
            end = 1;
            ci = 0;
            start = 0;

            while (end + 1 <= length) {
                //System.out.println(end);
                if (p.equals(sortslop[end])) {
                    end++;
                    start++;
                    continue;
                }
                else if (end < length - 1 && (p.compareTo(sortslop[end]) == -1) && (
                        p.slopeTo(sortslop[end]) == Double.POSITIVE_INFINITY
                                && p.slopeTo(sortslop[end + 1]) == Double.POSITIVE_INFINITY)) {

                    ci++;
                    //System.out.println(ci);


                }
                else if (end < length - 1 && (p.compareTo(sortslop[end]) == -1
                        && Double.compare(p.slopeTo(sortslop[end]),
                                          p.slopeTo(
                                                  sortslop[end + 1]))
                        == 0)) {
                    ci++;


                }


                else if ((end == length - 1) || ((p.compareTo(sortslop[end]) == -1)
                        && Double.compare(p.slopeTo(sortslop[end]),
                                          p.slopeTo(
                                                  sortslop[end
                                                          + 1]))
                        != 0)) {
                    //System.out.println(ci);
                    if (ci >= 2) {


                        if (!ends.contains(sortslop[end])) {
                            //System.out.println("HI");
                            //System.out.println(start);
                            // System.out.println(end);
                            LineSegment line = new LineSegment(sortslop[start], sortslop[end]);
                            Segments.add(line);
                            ends.add(sortslop[end]);
                        }


                    }
                    ci = 0;

                }

                end++;


            }

        }
    }


    public int numberOfSegments() { // the number of line segments
        return Segments.size();
    }

    public LineSegment[] segments() {    // the line segments
        return Segments.toArray(new LineSegment[0]);

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
            StdOut.println(segment.toString());
            //segment.draw();
        }


    }
}

