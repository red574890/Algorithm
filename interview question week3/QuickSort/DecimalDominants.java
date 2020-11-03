/*
Decimal dominants. Given an array with n keys, design an algorithm to find all values that occur more than
n/10 times. The expected running time of your algorithm should be linear.
 */


import java.util.ArrayList;

public class DecimalDominants {
    private int n;
    private int times;
    private int[] array;

    public void DecimalDominants(int[] a) {
        n = a.length;
        times = n / 10;
        array = a;
    }

    ArrayList<Integer> elemList = new ArrayList<Integer>();


    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static int select(int[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }

    public void count() {
        int j = n;
        int count = 0;
        int temp;
        //StdOut.println(times);
        temp = select(array, j - 1);
        while (j > 0) {

            if (temp == select(array, j - 1)) {
                count++;
            }
            if (temp != select(array, j - 1) || j == 1) {
                if (count > times) {
                    elemList.add(array[j]);
                }
                temp = select(array, j - 1);
                count = 0;
            }

            j--;
        }

    }

    public void show() {
        System.out.println(elemList.toString());
    }

    public static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean less(int a, int b) {
        return a < b;
    }

    public static void main(String[] args) {
        DecimalDominants test = new DecimalDominants();
        int[] a = { 2, 2, 2, 4, 5, 5, 5, 6, 7, 10 };
        test.DecimalDominants(a);
        test.count();
        test.show();


    }
}
