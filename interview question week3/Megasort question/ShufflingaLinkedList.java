import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShufflingaLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node first = null;
    private Node last = null;
    private int size = 0;


    private class Node {
        T item;
        Node next;
    }


    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (current == null) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(T t) {
        if (t == null) throw new NullPointerException();
        Node OldLast;
        OldLast = last;
        last = new Node();
        last.item = t;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            OldLast.next = last;
        }
        size++;
    }

    private Node merge(Node left, Node right) {
        Node aux = new Node();
        Node l = left;
        Node r = right;
        Node current = aux;
        while (l != null && r != null) {
            int rand = StdRandom.uniform(2);
            if (rand == 0) { // if the random number is 0, pick list from left
                current.next = l;
                current = current.next;
                l = l.next;
            }
            else {          // if the random number is 1, pick list from right
                current.next = r;
                current = current.next;
                r = r.next;
            }
        }
        if (l != null) current.next = l;
        else if (r != null) current.next = r;
        return aux.next;

    }

    private Node shuffle(Node head) {
        if (head == null || head.next == null) return head;
        Node right;
        Node left;
        Node mid = head;
        Node fast = head;


        // Find the size of this linked-list

        //if (N == 1) return;

        // Got one spot before the halfway point.
        while (fast.next != null && fast.next.next != null) {
            //System.out.println(fast.item);
            mid = mid.next;
            fast = fast.next.next;
        }
        //System.out.println("--------");


        right = mid.next;
        left = head;
        mid.next = null;
        left = shuffle(left);
        right = shuffle(right);
        return merge(left, right);

    }

    public void mergeSort() {
        first = shuffle(first);
    }

    public String toString() {
        Iterator<T> iter = iterator();
        String res = iter.next().toString();
        while (iter.hasNext()) {
            res += "," + iter.next().toString();
        }
        return res;
    }

    public int size() {

        return size;
    }


    public static void main(String[] args) {
        ShufflingaLinkedList<Integer> test = new ShufflingaLinkedList<Integer>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        test.add(6);
        test.add(7);
        test.add(8);
        test.add(9);
        test.add(10);


        System.out.println(test);
        test.mergeSort();
        System.out.println(test);

    }
}
