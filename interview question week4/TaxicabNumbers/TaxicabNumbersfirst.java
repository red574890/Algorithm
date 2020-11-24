import java.util.ArrayList;
import java.util.Collections;

public class TaxicabNumbersfirst {
    int n;

    public QuickSort sort = new QuickSort();
    ArrayList<ListNode> test = new ArrayList();
    int N;

    private static class ListNode implements Comparable<ListNode> {
        private int a;
        private int b;
        private int sum;

        public ListNode(int a, int b) {
            this.a = a;
            this.b = b;
            this.sum = (int) (Math.pow(this.a, 3) + Math.pow(this.b, 3));

        }

        @Override
        public int compareTo(ListNode o) {
            if (this.sum < o.sum) return -1;
            else if (this.sum > o.sum) return 1;
            else return 0;
        }


    }


    public void Numbers(int capacity) {
        n = capacity;

        for (int i = 0; i < n; i++) {
            if (Math.pow(i, 3) > n) {
                break;
            }
            for (int j = i + 1; j < n; j++) {
                if (Math.pow(j, 3) > n) {
                    break;
                }
                else if ((Math.pow(i, 3) + Math.pow(j, 3)) > n) {
                    break;
                }
                else {
                    ListNode temp = new ListNode(i, j);
                    test.add(temp);
                }
            }

        }
        Collections.sort(test);


        for (int i = 0; i + 1 < test.size(); i++) {
            if (test.get(i).sum == test.get(i + 1).sum) {
                System.out.print(test.get(i).a);
                System.out.print("^3+");
                System.out.print(test.get(i).b);
                System.out.print("^3");
                System.out.print("=");
                System.out.print(test.get(i + 1).sum);
                System.out.print("=");
                System.out.print(test.get(i + 1).a);
                System.out.print("^3+");
                System.out.print(test.get(i + 1).b);
                System.out.println("^3");

            }

        }

    }


    public static void main(String[] args) {
        TaxicabNumbersfirst test = new TaxicabNumbersfirst();
        test.Numbers(20000);
    }
}
