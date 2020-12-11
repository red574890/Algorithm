//第 4 個問題
// Web tracking. Suppose that you are tracking
// n web sites and m users and you want to support the following API:
//
// User visits a website.
// How many times has a given user visited a given site?
// What data structure or data structures would you use?

public class Web {

    BST<String, BST<String, Integer>> users = new BST<String, BST<String, Integer>>();
    String[] site = new String[] { "ebay", "yahoo", "google", "amazon" };
    String[] userid = new String[] { "A", "B", "C", "D", };

    public Web() {
        for (int i = 0; i < 4; i++) {
            BST<String, Integer> sites = new BST<String, Integer>();
            for (int j = 0; j < site.length; j++) {
                sites.put(site[j], 0);
            }
            users.put(userid[i], sites);
        }

    }

    public void sitevisit(String user, String site) {
        int number = users.get(user).get(site);
        number += 1;
        BST temp = users.get(user);
        temp.changevalue(site, number);
    }

    public void showvisittimes(String user, String site) {
        System.out.print(user);
        System.out.print(" ");
        System.out.print("visit ");
        System.out.print(site);
        System.out.print(" for ");
        System.out.print(users.get(user).get(site));
        System.out.println(" times");


    }


    //public Iterable<Key> iterator() {

    //}
    public static void main(String[] args) {
        Web test = new Web();
        test.sitevisit("A", "ebay");
        test.sitevisit("A", "ebay");
        test.sitevisit("A", "ebay");
        test.sitevisit("A", "ebay");
        test.showvisittimes("A", "ebay");
        test.sitevisit("B", "google");
        test.sitevisit("B", "google");
        test.sitevisit("B", "google");
        test.sitevisit("B", "google");
        test.showvisittimes("A", "google");
        test.showvisittimes("B", "google");


    }
}
