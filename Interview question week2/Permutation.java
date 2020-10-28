public class Permutation {

    public boolean compare(int[] a, int[] b) {
        int N = a.length;
        int N1 = b.length;
        if (N1 != N) return false;
        shellsort(a);
        shellsort(b);
        for (int i = 0; i < N; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;

    }

    public void shellsort(int[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }

    }

    private boolean less(int x, int y) {
        return x < y;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Permutation test = new Permutation();
        int[] first = { 1, 2, 3, 4, 5, 6, 7 };
        int[] second = { 7, 6, 5, 4, 3, 2, 1 };

        System.out.println(test.compare(first, second));


    }
}
