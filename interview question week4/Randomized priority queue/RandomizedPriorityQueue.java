/*Randomized priority queue. Describe how to add the methods sample() and
delRandom() to our binary heap implementation. The two methods return a key that is chosen uniformly
at random among the remaining keys,
with the latter method also removing that key.
The sample() method should take constant time; the delRandom() method
should take logarithmic time. Do not worry about resizing the underlying array.
 */

public class RandomizedPriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public RandomizedPriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public Key sample() {
        if (isEmpty()) throw new IllegalArgumentException();
        int r;
        r = StdRandom.uniform(1, N + 1);
        return pq[r];
    }

    public Key delRandom() {
        if (isEmpty()) throw new IllegalArgumentException();
        int r;
        r = StdRandom.uniform(1, N + 1);
        Key random = pq[r];
        exch(r, N--);
        pq[N + 1] = null;
        sink(r);
        return random;

    }


    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public int size() {
        return N;
    }

    public boolean less(int a, int b) {
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void exch(int i, int j) {
        Key temp;
        temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    public Key print(int i) {
        return pq[i];
    }

    public static void main(String[] args) {
        RandomizedPriorityQueue test = new RandomizedPriorityQueue(10);

        test.insert(200);
        test.insert(1);
        test.insert(3);
        test.insert(4);
        test.insert(5);
        test.insert(6);
        test.insert(8);
        test.insert(11);
        test.insert(13);
        test.insert(14);

        System.out.println(test.sample());
        System.out.println(test.delRandom());
        System.out.println(test.delMax());


    }


}
