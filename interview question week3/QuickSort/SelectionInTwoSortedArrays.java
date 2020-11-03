public class SelectionInTwoSortedArrays {

    public int findK(int[] a, int[] b, int k) {
        int i = min(a.length, k / 2);
        int j = min(b.length, k - i);
        if (k == a.length + b.length) {
            if (a[a.length - 1] > b[b.length - 1]) return a[a.length - 1];
            else return b[b.length - 1];
        }
        else if (a.length == 0 && b.length >= k) {
            return b[k - 1];
        }
        else if ((b.length == 0 && a.length >= k)) {
            return a[k - 1];
        }
        else if (k == 1) {
            return min(a[0], b[0]);
        }
        else {
            return findK(a, i, b, j, k / 2);
        }

    }


    public int findK(int[] a, int i, int[] b, int j, int k) {
        //System.out.print("i=");
        //System.out.println(i);
        //System.out.print("j=");
        //System.out.println(j);
        int move;

        if (i == 0) {
            return b[j - 1];
        }
        else if (j == 0) {
            return a[i - 1];
        }
        if (k == 1 && (a[i] > b[j - 1] && b[j] > a[i - 1])) return max(a[i - 1], b[j - 1]);
        if (k == 1) k++;
        if (a[i - 1] < b[j - 1]) {
            move = min(a.length - i, k / 2);
            i = i + move;
            j = j - min(j, move);
            return findK(a, i, b, j, k / 2);
        }
        else {
            move = min(b.length - j, k / 2);
            i = i - min(i, move);
            j = j + move;
            return findK(a, i, b, j, k / 2);
        }


    }

    public int min(int a, int b) {
        if (a > b) {
            return b;
        }
        else {
            return a;
        }
    }

    public int max(int a, int b) {
        if (a > b) {
            return a;
        }
        else {
            return b;
        }
    }

    public static void main(String[] args) {
        int[] a = { 1, 3, 7, 9, 100, 112, 115 };
        int[] b = { 5, 6, 11, 13 };
        SelectionInTwoSortedArrays test = new SelectionInTwoSortedArrays();
        System.out.println(test.findK(a, b, 6));
        //System.out.println(1 / 2);
    }
}
