import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        Double x1;
        Double x2;
        Double y1;
        Double y2;
        if ((that.y - this.y == 0) && (that.x - this.x == 0)) {
            return Double.NEGATIVE_INFINITY;
        }
        else if (that.y - this.y == 0) {
            return 0.0;
        }
        else if (that.x - this.x == 0) {
            return Double.POSITIVE_INFINITY;
        }
        else {
            x1 = Double.valueOf(that.x);
            x2 = Double.valueOf(this.x);
            y1 = Double.valueOf(that.y);
            y2 = Double.valueOf(this.y);
            return (y1 - y2) / (x1 - x2);
        }

    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        int res = 0;
        if (this.y < that.y) {
            res = -1;
        }
        else if (this.y == that.y && this.x < that.x) {
            res = -1;
        }
        else if (this.y > that.y) {
            res = 1;
        }
        else if (this.y == that.y && this.x > that.x) {
            res = 1;
        }
        else if (this.y == that.y && this.x == that.x) {
            res = 0;
        }
        return res;

    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point point0, Point point1) {
            double Slope0 = slopeTo(point0);
            double Slope1 = slopeTo(point1);
            if (Slope0 > Slope1) {
                return 1;
            }
            else if (Slope0 < Slope1) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point point0 = new Point(1, 2);
        Point point1 = new Point(3, 4);

        point0.drawTo(point1);


        System.out.println(point0.slopeTo(point1));
    }
}
