package comp2011.ass1;

import comp2011.lec4.LinkedList;

/**
 * 
 * @author yixin cao (October 1, 2020)
 *
 *         A simulation of the queue waiting at a cashier.
 * 
 */
public class CustomerQueue {
    private LinkedList<String> list;
    private Node<String> front;
    private Node<String> rear;
    private int size;

    public CustomerQueue() {
        list = new LinkedList<String>();
        this.front = list.head;
        this.rear = list.head;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size > 0;
    }

    public void enqueue(String s) {
        Node<String> newNode = new Node<String>(s);
        newNode.next = null;
        this.rear.next = newNode;
        this.rear = newNode;
        this.size++;
    }

    public String dequeue() {
        if (!this.isEmpty()) {
            try {
                Node<String> temp = front.next;
                front.next = front.next.next;
                temp.next = null;
                this.size--;
                return temp.element;
            } catch (NullPointerException e) {
                System.out.println("**Illegal dequeue from an empty queue!**");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            return "";
        }
    }

    public String toString() {
        return null;
    }

    public CustomerQueue[] split(int k) {
        return null;
    }

    public static void main(String[] args) {
        CustomerQueue queue = new CustomerQueue();
        queue.enqueue("Peppa");
        // queue.enqueue ();
        // queue.enqueue ();
        // queue.enqueue ();
        // queue.enqueue ();
        System.out.println(queue.dequeue());
        // queue.enqueue ();
        // queue.enqueue ();
        // queue.enqueue ();
        // queue.enqueue ();
        // queue.enqueue ();
        System.out.println(queue.dequeue());
        CustomerQueue[] queues = queue.split(3);
        for (CustomerQueue q : queues)
            System.out.println(q);
    }
}
