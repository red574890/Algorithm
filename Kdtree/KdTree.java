import java.util.ArrayDeque; 
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stack;



public class KdTree {
    private KdNode root;
    private int size;


    private static class KdNode {
        private KdNode left;
        private KdNode right;
        private final boolean vert; //True is vertial, False is Horizontal
        private final Point2D p;

        public KdNode(Point2D p, boolean v) {
            this.p = p;
            this.vert = v;
        }

        public boolean isRightorTop(Point2D p) {
            if (this.vert) {
                return (this.p.x() < p.x());
            }
            else {
                return (this.p.y() < p.y());
            }
        }
    }


    public KdTree() {
        root = null;
        size = 0;
    }                                              // construct an empty set of points

    public boolean isEmpty() {
        return size == 0;
    }                      // is the set empty?

    public int size() {
        return size;
    }                         // number of points in the set

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = new KdNode(p, true);
            size++;
        }
        else {
            KdNode curr = root;
            KdNode prev = null;
            while (curr != null) {
                if (curr.p.equals(p)) {
                    return;
                }
                prev = curr;
                if (curr.isRightorTop(p)) {
                    curr = curr.right;
                }
                else {
                    curr = curr.left;
                }
            }
            if (prev.vert) {
                KdNode insert = new KdNode(p, false);
                if (prev.isRightorTop(p)) {
                    prev.right = insert;
                    size++;
                }
                else {
                    prev.left = insert;
                    size++;

                }
            }
            else {
                KdNode insert = new KdNode(p, true);
                if (prev.isRightorTop(p)) {
                    prev.right = insert;
                    size++;
                }
                else {
                    prev.left = insert;
                    size++;
                }

            }
        }

    }

    public boolean contains(Point2D p) {
        KdNode curr = root;
        if (p == null) throw new IllegalArgumentException();
        while (curr != null) {
            if (curr.p.equals(p)) {
                return true;
            }
            if (curr.isRightorTop(p)) {
                curr = curr.right;
            }
            else {
                curr = curr.left;
            }
        }
        return false;

    }

    public void draw() {
        StdDraw.clear();
        StdDraw.setCanvasSize(600, 600);
        drawline(root);


    }                         // draw all points to standard draw

    private void drawline(KdNode node) {
        if (node != null) {
            StdDraw.point(node.p.x(), node.p.y());
            if (node.vert) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.p.x(), -1000, node.p.x(), 1000);
            }
            else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(-1000, node.p.y(), 1000, node.p.y());
            }

            drawline(node.left);
            drawline(node.right);

        }
        return;

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        if (isEmpty()) throw new IllegalArgumentException();
        Stack<KdNode> stack = new Stack<KdNode>();
        SET<Point2D> points = new SET<Point2D>();
        boolean goleft = false;
        boolean goright = false;
        stack.push(root);
        while (!stack.isEmpty()) {
            goleft = false;
            goright = false;
            KdNode n = stack.pop();

            if (rect.contains(n.p)) {
                points.add(n.p);
                goleft = true;
                goright = true;
            }
            else if (interect(n, rect)) {
                goleft = true;
                goright = true;
            }
            else if (n.isRightorTop(new Point2D(rect.xmin(), rect.ymin()))) {
                goright = true;
            }
            else if (!n.isRightorTop(new Point2D(rect.xmin(), rect.ymin()))) {
                goleft = true;
            }

            if (goleft && n.left != null) {
                stack.push(n.left);
            }

            if (goright && n.right != null) {
                stack.push(n.right);
            }
    


        }

        return points;


    }             // all points that are inside the rectangle (or on the boundary)

    private boolean interect(KdNode n, RectHV rect) {
        if (n.vert) {
            if (n.isRightorTop(new Point2D(rect.xmin(), rect.ymin())) && !n
                    .isRightorTop(new Point2D(rect.xmax(), rect.ymin()))) {
                return true;
            }
            else if (!n.isRightorTop(new Point2D(rect.xmin(), rect.ymin())) && n
                    .isRightorTop(new Point2D(rect.xmax(), rect.ymin()))) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (n.isRightorTop(new Point2D(rect.xmin(), rect.ymin())) && !n
                    .isRightorTop(new Point2D(rect.xmin(), rect.ymax()))) {
                return true;
            }
            else if (!n.isRightorTop(new Point2D(rect.xmin(), rect.ymin())) && n
                    .isRightorTop(new Point2D(rect.xmin(), rect.ymax()))) {
                return true;
            }
            else {
                return false;
            }
        }

    }






    private double PointtoLine(Point2D input, KdNode origin) {
        double res = 0;
        if (origin.vert) {
            res = input.distanceSquaredTo(new Point2D(origin.p.x(), input.y()));
        }
        else {
            res = input.distanceSquaredTo(new Point2D(input.x(), origin.p.y()));
        }
        return res;
    }


    public Point2D nearest(Point2D po) {
        if (po == null) throw new IllegalArgumentException();

        if (isEmpty()) throw new IllegalArgumentException();

        ArrayDeque<KdNode> queue = new ArrayDeque<KdNode>();
        queue.addLast(root);
        KdNode node;
        KdNode closet = root;

        while (queue.size() != 0) {
            node = queue.removeFirst();

            if (node.p.distanceSquaredTo(po) == 0) {
                closet = node;
                break;
            }

            if (po.distanceSquaredTo(node.p) < po.distanceSquaredTo(closet.p)) {
                closet = node;
            }
            //if points to line is bigger than closet than no need to keep searching

            if (node.vert && node.isRightorTop(po) && po.distanceSquaredTo(closet.p) <= PointtoLine(
                    po,
                    node)
                    && node.right != null) {
                queue.addLast(node.right);
            }
            else if (node.vert && !node.isRightorTop(po)
                    && po.distanceSquaredTo(closet.p) <= PointtoLine(po, node)
                    && node.left != null) {
                queue.addLast(node.left);
            }
            else if (!node.vert && node.isRightorTop(po)
                    && po.distanceSquaredTo(closet.p) <= PointtoLine(po, node)
                    && node.right != null) {
                queue.addLast(node.right);
            }
            else if (!node.vert && node.isRightorTop(po)
                    && po.distanceSquaredTo(closet.p) <= PointtoLine(po, node)
                    && node.left != null) {
                queue.addLast(node.left);
            }
            else {
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }

        }

        return closet.p;


    }            // a       // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        KdTree test = new KdTree();
        test.insert(new Point2D(0.372, 0.497));
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        test.insert(new Point2D(0.564, 0.413));
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        test.insert(new Point2D(0.226, 0.577));
        System.out.println(test.isEmpty());
        System.out.println(test.size());

    }                  // unit testing of the methods (optional)
}
