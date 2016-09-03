/**
 * Created by bold on 9/3/16.
 */
public class Queue {
    private Node start;
    private int size;

    public Queue() {
        start = null;
        //  end = null;
        size = 0;
    }

    public Queue(int data) {
        start = new Node(data, null);
        // end = start;
        size = 1;
    }

    public void enqueue(int data) {
        if (start == null) {
            start = new Node(data, null);
            return;
        }
        Node n = start;
        while (n.getNext() != null) {
            n = n.getNext();
        }
        n.setNext(new Node(data, null));
        //end = n.getNext();
        size++;
    }

    public void dequeue() {
        if (start == null) {
            System.err.println("Cannot dequeue an empty queue.");
            return;
        }
        Node temp;
        temp = start.getNext();
        start = temp;
        size--;
    }

    public void peek() {
        if (start == null)
            System.err.println("Cannot peek on empty Queue");
        else
            System.out.println(start.getData());
    }

    public Boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    public void isFull() {
        //?
    }
}
