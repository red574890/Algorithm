public class MergingwithSmallerAux {
    public void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                a[k] = a[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];

            }
            else if (less(aux[i], a[j])) {
                a[k] = aux[i++];
            }
            else {
                a[k] = a[j++];
            }

        }
    }

    public boolean less(int a, int b) {
        return a < b;
    }

    public static void main(String[] args) {
        int[] list = { 1, 4, 7, 111, 2, 5, 6, 10 };
        int[] aux = { 0, 0, 0, 0 };
        MergingwithSmallerAux test = new MergingwithSmallerAux();
        test.merge(list, aux, 0, 3, 7);
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }


    }
}
