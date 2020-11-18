/*

Dynamic median. Design a data type that supports insert in logarithmic time,
find-the-median in constant time, and remove-the-median in logarithmic time.
If the number of keys in the data type is even, find/remove the lower median.

*/


public class DynamicMedian {
    private static class ListNode implements Comparable<ListNode> {
        private int value;

        public ListNode(int value) {
            this.value = value;

        }

        @Override
        public int compareTo(ListNode o) {
            if (this.value < o.value) return -1;
            else if (this.value > o.value) return 1;
            else return 0;
        }


    }

    public MinPQ1<ListNode> rightmin = new MinPQ1<>(10);
    public MaxPQ<ListNode> leftmax = new MaxPQ<>(10);
    int mid = 0;

    public void add(int i) {
        ListNode a = new ListNode(i);
        if (rightmin.size() + leftmax.size() == 0) {
            mid = a.value;
            leftmax.insert(a);
        }
        else {
            if (i > mid) {
                rightmin.insert(a);
            }
            else {
                leftmax.insert(a);
            }
        }

        adjust();


    }

    private void adjust() {
        if (rightmin.size() - leftmax.size() > 1) {
            leftmax.insert(rightmin.delMin());
            mid = leftmax.print(1).value;
        }
        else if (leftmax.size() - rightmin.size() > 1) {
            rightmin.insert(leftmax.delMax());
            mid = leftmax.print(1).value;
        }
    }

    public int findmedian() {
        if (rightmin.size() > leftmax.size()) {
            return rightmin.print(1).value;
        }
        else if (rightmin.size() < leftmax.size()) {
            return leftmax.print(1).value;
        }
        else {
            return (leftmax.print(1).value + rightmin.print(1).value) / 2;
        }
    }

    public int removemedian() {
        return leftmax.delMax().value;
    }


    public static void main(String[] args) {
        DynamicMedian test = new DynamicMedian();

        test.add(200);
        test.add(1);
        test.add(3);
        test.add(4);
        test.add(5);
        test.add(6);
        test.add(8);
        test.add(11);
        test.add(13);
        test.add(14);


        System.out.println(test.findmedian());

    }
}
