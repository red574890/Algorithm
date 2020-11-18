public class MinPQ1<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MinPQ1(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private boolean greater(int a, int b) {
        return pq[a].compareTo(pq[b]) > 0;
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
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return min;
    }

    public Key print(int i) {
        return pq[i];
    }
}
