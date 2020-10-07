import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] stack;
    private int size = 0;


    // construct an empty randomized queue
    public RandomizedQueue() {
        stack = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = stack[i];
        }
        stack = copy;
    }


    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == stack.length) resize(2 * stack.length);
        stack[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int random = StdRandom.uniform(size);
        Item item = stack[random];
        stack[random] = stack[size - 1];
        stack[--size] = null;
        if (size > 0 && size == stack.length / 4) resize(stack.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int random = StdRandom.uniform(size);
        Item item = stack[random];
        return item;


    }

    private class ArrayIterationr implements Iterator<Item> {
        private int[] randomlists = StdRandom.permutation(size);
        private int i;

        public boolean hasNext() {
            return i < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return stack[randomlists[i++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterationr();

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        test.enqueue("Hello there");
        test.enqueue("1");
        test.enqueue("3");
        test.enqueue("2");
        //StdOut.println(test.sample());
        //StdOut.println(test.size());
        for (String s : test) {
            StdOut.println(s);
        }
        test.dequeue();
        for (String s : test) {
            StdOut.println(s);
        }


    }


}
