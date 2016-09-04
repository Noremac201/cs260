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

    public Queue(int d, int... data) {
        start = new Node(d, null);
        for (int tData : data) {
            enqueue(tData);
        }
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
        peek();
        Node temp;
        temp = start.getNext();
        start = temp;
        size--;
    }

    public void peek() {
        if (start == null)
            System.out.println("Cannot peek on empty Queue");
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
/*
        Runtime run = Runtime.getRuntime();
        System.out.println((run.totalMemory() - run.freeMemory()) / 1024 / 1024 + " Mb used");
*/
    }
}
