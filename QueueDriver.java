/**
 * Created by bold on 9/3/16.
 */
public class QueueDriver {
    public static void main(String[] args) {
        Queue myQ = new Queue(23);
        myQ.enqueue(25);
        myQ.dequeue();
        myQ.peek();
    }
}