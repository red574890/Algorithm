import java.util.Iterator;
import java.util.Stack;

public class StackWithMax {
    private Stack box = new Stack();
    private int max = 0;
    private int sec = 0;

    public Object pop() {
        int del = (int) box.pop();
        if (del == max) {

            max = sec;
            Iterator value = box.iterator();
            int min = 0;
            int t = 0;
            int temp;
            int bsec = sec;
            while (value.hasNext()) {
                temp = (int) value.next();
                if (t == 0) {
                    min = sec - temp;
                    sec = temp;
                }
                else {
                    if (bsec == temp) {
                        continue;
                    }
                    else if ((bsec - temp) < min) {
                        sec = temp;
                    }
                }


                t += 1;


            }

        }

        return del;
    }

    public void push(int item) {
        if (box.empty()) {
            max = item;
        }
        else {
            if (item > max) {
                sec = max;
                max = item;
            }

        }
        box.push(item);


    }

    public int max() {
        if (box.empty()) throw new java.util.NoSuchElementException();
        return max;
    }


    public static void main(String[] args) {

        StackWithMax test = new StackWithMax();
        test.push(3);
        test.push(1);
        test.push(2);
        test.push(4);
        test.push(5);
        System.out.println(test.max());
        test.pop();
        System.out.println(test.max());
        test.pop();
        System.out.println(test.max());
        test.push(4);
        test.push(100);
        System.out.println(test.max());

    }
}
