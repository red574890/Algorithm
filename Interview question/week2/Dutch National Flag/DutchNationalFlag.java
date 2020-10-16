public class DutchNationalFlag {

    private String[] flag;

    public void DutchNationalFlag(String[] a) {
        flag = a;
    }

    public void swap(int i, int j) {
        String temp = flag[i];
        flag[i] = flag[j];
        flag[j] = temp;
    }

    public String color(int i) {
        return flag[i];
    }

    public void colorsort() {
        int current = 0;
        int low = 0;
        int N = flag.length;
        int high = N - 1;

        while (current <= high) {
            if (flag[current] == "red") {
                if (current == low) {
                    current++;
                    low++;
                }
                else {
                    swap(current, low);
                    low++;

                }
            }
            else if (flag[current] == "white") {
                current++;
            }
            else if (flag[current] == "blue") {
                swap(current, high);
                high--;
            }
        }
    }

    public void show() {
        int N = flag.length;
        for (int i = 0; i < N; i++) {
            System.out.print(flag[i]);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {

        String[] a = { "blue", "white", "red", "blue", "blue", "white", "white", "blue", "red" };
        DutchNationalFlag test = new DutchNationalFlag();
        test.DutchNationalFlag(a);
        test.colorsort();
        test.show();


    }
}
