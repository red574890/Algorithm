public class QueueWith2Stacks<Item> {
    private Stack inbox = new Stack();
    private Stack outbox = new Stack();

    public void enqueue(Item item) {
        inbox.push(item);
    }

    public Object dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty())
                outbox.push(inbox.pop());
        }
        return outbox.pop();

    }


    public static void main(String[] args) {
        QueueWith2Stacks test = new QueueWith2Stacks();

        for (int i = 0; i < 5; i++) {
            test.enqueue(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(test.dequeue());
        }

    }
}
