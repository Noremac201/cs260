/**
 * Created by bold on 9/3/16.
 */
public class Queue {
    private Node start;
    private Node end;
    private int size;

    public Queue() {
        start = null;
        end = null;
        size = 0;
    }

    public Queue(int d, int... data) {
        start = new Node(d, null);
        end = start;
        size = 1;

        for (int tData : data) {
            enQueue(tData);
        }
        // end = start;
    }

    public void enQueue(int data) {
        if (start == null) {
            start = new Node(data, null);
            end = start.getNext();
            return;
        }
/*
        Node n = end;
        while (n.getNext() != null) {
            n = n.getNext();
        }
        n.setNext(new Node(data, null));
*/
        end.setNext(new Node(data, null));
        end = end.getNext();
        size++;
    }

    public void deQueue() {
        if (start == null) {
            System.err.println("Cannot deQueue an empty queue.");
            return;
        }
        //peek();
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

    public void display() {
        if (start == null) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = start;

        System.out.print("Queue => ");
        while (temp.getNext() != null) {
            System.out.print(temp.getData() + ",");
            temp = temp.getNext();
        }
        System.out.print(temp.getData() + "\n");

        System.out.println("Front Pointer => " + start.getData());
        System.out.println("Rear Pointer  => " + end.getData());
    }
}
