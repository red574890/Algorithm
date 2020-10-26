public class CountInversion {
    static int count = 0;

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {


                a[k] = aux[i++];
                // when j is higher than hi, which means that all the following aux[i] is bigger than all elements from mid+1 to hi
                // For example, in [4,8]  [1,2]. When j>hi, [8,1] and [8,2] are both inversion. 
                // Therefore, we can directly add (mid - lo) to our count

                if (hi - lo >= 2) {
                    count += (mid - lo);
                }


            }
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];

                count++;
            }
            else {
                a[k] = aux[i++];
            }
        }
    }

    public int InversionTimes() {
        return count;
    }

    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (!less(a[mid + 1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    public void sort(int[] a) {
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }


    private static boolean less(int a, int b) {
        return a < b;
    }

    public static void main(String[] args) {
        int[] test = { 1, 2, 4, 3 };
        CountInversion Cinversion = new CountInversion();
        Cinversion.sort(test);
        System.out.print(Cinversion.InversionTimes());

    }
}
