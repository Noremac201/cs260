/**
 * Created by bold on 9/3/16.
 */
public class QueueDriver {
    public static void main(String[] args) {
        CustomQueue myQ = new CustomQueue();
        myQ.enQueue(10);
        myQ.enQueue(20);
        myQ.enQueue(30);
        myQ.display();
        myQ.deQueue();
        myQ.deQueue();
        myQ.deQueue();
    }
}