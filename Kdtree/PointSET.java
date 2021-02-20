import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.SET;

public class PointSET {

    private SET<Point2D> points;

    public PointSET() {
        points = new SET<Point2D>();
    }                               // construct an empty set of points

    public boolean isEmpty() {
        return size() == 0;
    }                      // is the set empty?

    public int size() {
        return points.size();
    }                         // number of points in the set

    public void insert(
            Point2D p) {
        points.add(p);
    }              // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);

    }            // does the set contain point p?

    public void draw() {
        StdDraw.setPenRadius(0.05);
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setPenColor(StdDraw.RED);
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }

    }                         // draw all points to standard draw

      public Iterable<Point2D> range(
            RectHV rect) {
        if(isEmpty()) return null;
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> bounded = new LinkedList<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                bounded.add(point);
            }
        }
        return bounded;
    }             // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(
            Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (points.isEmpty()) {
            return null;
        }
        else {
            double neardistance = 0;
            int n = 0;
            Point2D res = p;
            for (Point2D point : points) {
                if (n == 0) {
                    neardistance = point.distanceTo(p);
                    res = point;
                }
                else {
                    if (neardistance > point.distanceTo(p)) {
                        neardistance = point.distanceTo(p);
                        res = point;
                    }
                }
                n += 1;
            }
            return res;
        }
    }            // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        PointSET test = new PointSET();
        test.insert(new Point2D(1, 2));
        test.insert(new Point2D(3, 4));

        test.insert(new Point2D(8, 8));
        test.draw();
        //System.out.println(test.contains(new Point2D(1, 2)));

    }                  // unit testing of the methods (optional)
}
