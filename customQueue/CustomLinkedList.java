/**
 * Created by bold on 9/1/16.
 */
public class CustomLinkedList {
    private Node start;
    private Node end;
    private int size;

    public CustomLinkedList() {
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
        if (pos == 1 || pos >= size) {
            System.err.println("Invalid Position\n");
            return;
        }

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

    public void updateAtPosition(int pos, int data) {
        if (pos == 1) {
            start.getNext().setData(data);
        }
        if (pos == size) {
            Node s = start;
            while (s.getNext() != null) {
                s = s.getNext();
            }
            s.setData(data);
            return;
        }
        Node aNode = start;
        pos = pos - 1;
        for (int i = 1; i < size - 1; i++) {
            if (i == pos) {
                aNode.setData(data);
            }
            aNode = aNode.getNext();
        }
        return;
    }

    public int searchByValue(int val) {
        if (size == 0) {
            System.err.println("List is empty");
            //returns impossible vindex.
            return -1;
        }
        Node aNode = start;
        for (int i = 1; i < size - 1; i++) {
            if (val == aNode.getData()) {
                return i;
            }
            aNode = aNode.getNext();
        }
        //if not found, returns impossible index.
        return -1;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

}
