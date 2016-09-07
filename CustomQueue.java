/**
 * Created by bold on 9/6/16.
 */
public class CustomQueue {
    CustomLinkedList l = new CustomLinkedList();

    public void enQueue(int val) {
        l.insertAtEnd(val);
    }

    public void deQueue() {
        if (l.getSize() < 1) {
            System.err.println("error");
            return;
        }
        l.deleteAtPos(1);
    }

    public void peek() {
        System.out.println(l.getStart().getData());
    }

    public void isFull() {
        return;
    }

    public Boolean isEmpty() {
        return l.getSize() == 0;
    }

    public void display() {
        if (l.getStart() == null) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = l.getStart();

        System.out.print("Queue => ");
        while (temp.getNext() != null) {
            System.out.print(temp.getData() + ",");
            temp = temp.getNext();
        }
        System.out.print(temp.getData() + "\n");

        System.out.println("Front Pointer => " + l.getStart().getData());
        System.out.println("Rear Pointer  => " + l.getEnd().getData());
    }
}
