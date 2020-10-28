import java.awt.Point;

public class IntersectionOfTwoSets {
    public int samepoint(Point[] a, Point[] b) {
        int count = 0;
        sort(a);
        sort(b);


        count = bsearch(a, b);

        return count;


        // use binoary search to find the same point


    }

    public void sort(Point[] a) {   //shellsort the points' length to make sure that we consider x value and y value
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(vector(a[j]), vector(a[j - h])); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }


    }

    private boolean less(double x, double y) {
        return x < y;
    }

    private int bsearch(Point[] a, Point[] b) {
        int aN = a.length;
        int bN = b.length;
        int low;
        int high;
        int mid;
        int count = 0;
        int stop = 1;

        for (int i = 0; i < aN; i++) {
            low = 0;
            high = bN - 1;
            stop = 1;
            while (low <= high && stop == 1) {
                mid = low + (high - low) / 2;

                if (vector(a[i]) < vector(b[mid])) {
                    high = mid - 1;
                }
                else if (vector(a[i]) > vector(b[mid])) {
                    low = mid + 1;
                }
                else {
                    if (a[i].equals(b[mid])) {
                        count++;
                    }
                    stop = -1;

                }
            }
        }
        return count;
    }

    private double vector(Point a) {
        return a.getX() * a.getX() + a.getY() * a.getY();
    }


    private static void exch(Point[] a, int i, int j) {
        Point swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        IntersectionOfTwoSets test = new IntersectionOfTwoSets();
        Point p1 = new Point(1, 2);
        Point pe = new Point(1, 3);
        Point pe2 = new Point(1, 1);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 4);
        Point p4 = new Point(1, 4);
        Point p10 = new Point(7, 4);
        Point p111 = new Point(6, 6);

        Point pe1 = new Point(1, 3);
        Point pe7 = new Point(1, 8);
        Point p5 = new Point(2, 4);
        Point p6 = new Point(3, 4);
        Point p7 = new Point(7, 7);
        Point p8 = new Point(1, 2);
        Point p11 = new Point(8, 4);
        Point p222 = new Point(6, 6);


        Point[] aPoints = { p1, p2, p3, p4, p10, p111, pe, pe2 };
        Point[] bPoints = { p5, p6, p7, p8, p11, p222, pe1, pe7 };


        System.out.println(test.samepoint(aPoints, bPoints));


    }
}
