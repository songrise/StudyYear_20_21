package comp2011.myImpl.dataStructure;

import java.util.Arrays;

public class Queue {

    public static void main(String[] arg) {
        Queue queue = new Queue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        queue.show();
        System.out.println(queue.dequeue());
        queue.show();

    }

    private static final int CAPACITY = 32;
    private int[] data;
    private int front, rear;

    public Queue() {
        this(CAPACITY);
    }

    public Queue(int size) {
        data = new int[size];
        front = 0;
        rear = -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        if (rear == front - 1)
            return 0;
        return (rear - front + data.length) % data.length + 1;
        // Note that "+ data.length" in the parentheses, and the final "+1";
        // Consider, e.g., the case front = data.length - 3 and rear = 4.
    }

    public boolean isFull() {
        return size() == data.length - 1;
    }

    public void enqueue(int e) {
        // Uncomment this to see the change of indices.
        // System.out.println("before enquequing, front = " + front + "; rear = " +
        // rear);
        if (isFull()) {
            System.out.println("Error: queue is full.");
            return;
        }

        if (++rear == data.length)
            rear = 0; // right end reached
        data[rear] = e;
        // Uncomment this to see the change of indices.
        // System.out.println("after enquequing, front = " + front + "; rear = " +
        // rear);
    }

    @SuppressWarnings("unchecked")
    public int dequeue() {
        // Uncomment this to see the change of indices.
        // System.out.println("before dequequing, front = " + front + "; rear = " +
        // rear);

        if (isEmpty()) {
            System.out.println("Error: empty queue.");
            return -1;
        }

        int a = data[front++];
        if (front == data.length)
            front = 0; // right end
        // Uncomment this to see the change of indices.
        // System.out.println("after dequequing, front = " + front + "; rear = " +
        // rear);

        return a;
    }

    public void show() {
        System.out.println(Arrays.toString(this.data));
        System.out.println("front pointer at: " + this.front);
        System.out.println("rear pointer at: " + this.rear);

    }

    public String toString() {
        if (isEmpty())
            return "The queue is empty.";
        StringBuilder sb = new StringBuilder();
        if (rear >= front) {
            for (int i = front; i <= rear; i++)
                sb.append(data[i] + "->");
        } else {
            for (int i = front; i < data.length; i++)
                sb.append(data[i] + "->");
            for (int i = 0; i <= rear; i++)
                sb.append(data[i] + "->");
        }
        sb.append("rear");
        return sb.toString();
    }

}
