/**
 * Created by bold on 9/3/16.
 */
public class QueueDriver {
    public static void main(String[] args) {
        Queue myQ = new Queue(10,20,30);
        myQ.display();
        myQ.deQueue();
        myQ.deQueue();
        myQ.deQueue();
        myQ.deQueue();
        myQ.display();
    }
}