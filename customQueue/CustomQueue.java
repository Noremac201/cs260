/**
 * Created by bold on 9/6/16.
 */
public class CustomQueue {
    CustomLinkedList list = new CustomLinkedList();

    public void enQueue(int val) {
        list.insertAtEnd(val);
    }

    public void deQueue() {
        if (list.getSize() < 1) {
            System.out.println("\nCannot DeQueue an empty Queue.");
            return;
        }
        list.deleteAtPos(1);
    }

    public void peek() {
        if (!isEmpty()) {
            System.out.println(list.getStart().getData());
            return;
        }
        System.out.println("\nCannot peek on empty Queue.");
    }

    public Boolean isFull() {
        //LinkedList is only capped by physical memory
        return false;
    }

    public Boolean isEmpty() {
        return list.getSize() == 0;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        Node temp = list.getStart();

        System.out.print("Queue => ");
        while (temp.getNext() != null) {
            System.out.print(temp.getData() + ",");
            temp = temp.getNext();
        }
        System.out.println(temp.getData());

        System.out.println("Front Pointer => " + list.getStart().getData());
        System.out.println("Rear Pointer  => " + list.getEnd().getData() + "\n");
    }

    public int getSize() {
        return list.getSize();
    }
}
