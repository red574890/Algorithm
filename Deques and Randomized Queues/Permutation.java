import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> test = new RandomizedQueue<String>();


        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            test.enqueue(s);
        }
        int stop = 0;
        for (String t : test) {

            if (stop == k) {
                break;
            }
            StdOut.println(t);
            stop++;
        }

    }
}
