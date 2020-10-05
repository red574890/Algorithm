import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node OldFirst = first;
        first = new Node();
        first.item = item;

        first.previous = null;
        //OldFirst.previous = null;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = OldFirst;
            OldFirst.previous = first;
        }
        size++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node OldLast = last;
        last = new Node();
        last.item = item;
        last.previous = OldLast;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            OldLast.next = last;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) last = first;

        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        last.next = null;
        size--;

        if (isEmpty()) last = first;

        return item;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> test = new Deque<String>();
        StdOut.println(test.isEmpty());
        test.addFirst("I");
        test.addFirst("love");
        test.addFirst("Java");
        //
        // StdOut.println(test.isEmpty());
        // StdOut.println(test.size());

        test.addLast("he");
        test.addLast("is");
        test.addLast("cool");
        StdOut.println(test.size());
        //StdOut.println(test.removeFirst());
        //StdOut.println(test.removeLast());
        StdOut.println(test.size());
        for (String s : test) {
            StdOut.println(s);
        }
    }

}
