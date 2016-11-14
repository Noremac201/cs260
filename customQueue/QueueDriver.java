package customQueue;

import java.util.Scanner;

public class QueueDriver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Creating object of class queue
        CustomQueue queue = new CustomQueue();
        System.out.println("Custom Queue");

//		Perform list operations
        while (true) {
            System.out.println("\nQueue Options\n");
            System.out.println("1. EnQueue");
            System.out.println("2. DeQueue");
            System.out.println("3. Check Empty");
            System.out.println("4. Display");
            System.out.println("5. Peek");
            System.out.println("6. Get Size");
            System.out.println("7. Exit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: //enqueue
                    System.out.println("Enter integer element to insert");
                    queue.enQueue(scan.nextInt());
                    break;
                case 2: //dequeue
                    queue.deQueue();
                    break;
                case 3: //check empty
                    if (queue.isEmpty())
                        System.out.println("Queue is empty.");
                    else
                        System.out.println("Queue is not empty");
                    break;
                case 4: //display
                    queue.display();
                    break;
                case 5: //peek
                    queue.peek();
                    break;
                case 6: //get size
                    System.out.println("Size = " + queue.getSize() + " \n");
                    break;
                case 7: //terminate
                    scan.close();
                    System.exit(0);
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            /* Display List */
            queue.display();
        }
    }
}
