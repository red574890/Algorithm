//Add one change value API to update the visit times

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.val = val;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;

    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Key min() {
        return min(root).key;
    }


    private Node min(Node a) {
        if (a.left == null) {
            return a;
        }
        else {
            return min(a.left);
        }

    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node a) {
        if (a.right == null) {
            return a;
        }
        else {
            return max(a.right);
        }

    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }


    private boolean isBST(Node a, Key min, Key max) {
        if (a == null) return true;
        if (min != null && a.key.compareTo(min) < 0) return false;
        if (max != null && a.key.compareTo(max) > 0) return false;
        return isBST(a.left, min, a.key) && isBST(a.right, a.key, max);
    }

    public void changevalue(Key key, Value newval) {
        Node x = root;
        int t = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            }
            else if (cmp > 0) {
                x = x.right;
            }
            else {
                x.val = newval;
                t = 1;
                break;
            }
        }
        if (t == 0) {
            System.out.println("No such Key");
        }
    }

    //public Iterable<Key> iterator() {

    //}

    public static void main(String[] args) {
        BST<Integer, String> test = new BST<Integer, String>();
        String[] strarray1 = new String[] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "F", "G", "ER",
                "EF", "WERF", "WE", "YUI"
        };
        for (int i = 0; i < 20; i++) {
            test.put(StdRandom.uniform(0, 20), strarray1[i]);
        }
        System.out.println(test.isBST());


    }
}
