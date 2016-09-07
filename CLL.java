/**
 * Created by bold on 9/1/16.
 */
public class CLL {
    private Node start;
    private Node end;
    private int size;

    public CLL() {
        start = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty() {
        if (start == null)
            return true;
        else
            return false;
    }

    public int getSize() {
        return size;
    }

    public void insertAtStart(int val) {
        Node node = new Node(val, null);
        size++;
        if (start == null) {
            start = node;
            end = start;
        } else {
            node.setNext(start);
            start = node;
        }
    }

    public void insertAtEnd(int val) {
        Node node = new Node(val, null);
        size++;
        if (start == null) {
            start = node;
            end = start;
        } else {
            end.setNext(node);
            end = node;
        }
    }

    public void insertAtPos(int val, int pos) {
        Node node = new Node(val, null);
        Node aNode = start;
        pos = pos - 1;
        for (int i = 1; i < size; i++) {
            if (i == pos) {
                Node tmp = aNode.getNext();
                aNode.setNext(node);
                node.setNext(tmp);
                break;
            }
            aNode = aNode.getNext();
        }
        size++;
    }

    public void deleteAtPos(int pos) {
        if (pos == 1) {
            start = start.getNext();
            size--;
            return;
        }
        if (pos == size) {
            Node s = start;
            Node t = start;
            while (s != end) {
                t = s;
                s = s.getNext();
            }
            end = t;
            end.setNext(null);
            size--;
            return;
        }
        Node aNode = start;
        pos = pos - 1;
        for (int i = 1; i < size - 1; i++) {
            if (i == pos) {
                Node tmp = aNode.getNext();
                tmp = tmp.getNext();
                aNode.setNext(tmp);
                break;
            }
            aNode = aNode.getNext();
        }
        size--;
    }

    public void display() {
        System.out.print("Linked List = ");
        if (size == 0) {
            System.out.println("empty");
            return;
        }
        if (start.getNext() == null) {
            System.out.println(start.getData());
            return;
        }
        Node aNode = start;
        System.out.print(start.getData() + "->");
        aNode = start.getNext();
        while (aNode.getNext() != null) {
            System.out.print(aNode.getData() + "->");
            aNode = aNode.getNext();
        }
        System.out.print(aNode.getData() + "\n");
    }
}
