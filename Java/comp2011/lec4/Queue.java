package comp2011.lec4;

/**
 * The way of implementing a queue with a circular array.
 * 
 * The <em>front</em> index of the first element of the queue, the element that
 * would be read (dequeue and peek). The <em>rear</em> index of the last element
 * of the queue.
 *
 */
public class Queue<T> {
    private static final int CAPACITY = 32;
    private Object[] data;
    private int front, rear;

    public Queue() {
        this(CAPACITY);
    }

    public Queue(int size) {
        data = new Object[size];
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

    public void enqueue(T e) {
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
    public T dequeue() {
        // Uncomment this to see the change of indices.
        // System.out.println("before dequequing, front = " + front + "; rear = " +
        // rear);

        if (isEmpty()) {
            System.out.println("Error: empty queue.");
            return null;
        }

        T a = (T) data[front++];
        if (front == data.length)
            front = 0; // right end
        // Uncomment this to see the change of indices.
        // System.out.println("after dequequing, front = " + front + "; rear = " +
        // rear);

        return a;
    }

    public String toString() {
        if (isEmpty())
            return "The queue is empty.";
        StringBuilder sb = new StringBuilder();
        if (rear >= front) {
            for (int i = front; i <= rear; i++)
                sb.append(data[i]);
        } else {
            for (int i = front; i < data.length; i++)
                sb.append(data[i]);
            for (int i = 0; i <= rear; i++)
                sb.append(data[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        queue.enqueue("Eason Chan");
        queue.enqueue("Denise Ho");
        queue.enqueue("Jennifer Chan");
        queue.enqueue("Joey Yung");
        queue.enqueue("Kay Tse");
        queue.enqueue("Cheung Jacky");
        for (int i = 0; i < 4; i++)
            System.out.println(queue.dequeue());
        System.out.println("size = " + queue.size());
        queue.enqueue("Winnie");
        queue.enqueue("Mickey");
        queue.enqueue("Teddy");
        queue.enqueue("Peppa");
        for (int i = 0; i < 7; i++)
            System.out.println(queue.dequeue());
        System.out.println("size = " + queue.size());
    }
}
