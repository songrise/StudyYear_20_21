package comp2011.lec4;

/**
 * 
 * @author yixin cao
 *
 */
public class LinkedList<T> {
    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T a) {
            element = a;
            next = null;
        }
    }

    Node<T> head; // tail

    public LinkedList() {
        head = null;
    }

    public void insertFirst(T a) {
        Node<T> newNode = new Node<T>(a);
        newNode.next = head;
        head = newNode;
        // if (tail == null) tail = newNode;
    }

    public void insertLast(T a) {
        if (head == null) {
            insertFirst(a);
            return;
        }

        Node<T> newNode = new Node<T>(a);
        Node<T> cur = head;
        while (cur.next != null)
            cur = cur.next;
        newNode.next = null;
        cur.next = newNode;
    }

    public void insertAfter(Node<T> insertionPoint, T a) {
        Node<T> newNode = new Node<T>(a);
        newNode.next = insertionPoint.next;
        insertionPoint.next = newNode;
    }

    public void insertBefore(Node<T> insertionPoint, T a) {
        if (head == insertionPoint) {
            insertFirst(a);
            return;
        }

        Node<T> cur = head;
        // at the end of this while loop,
        // cur will be the node that points to insertionPoint
        while (cur.next != insertionPoint && cur.next != null)
            cur = cur.next;
        Node<T> newNode = new Node<T>(a);
        newNode.next = cur.next;
        cur.next = newNode;
    }

    public T removeFirst() {
        if (head == null) {
            System.out.println("underflow");
            return null;
        }
        Node<T> temp = head;
        head = head.next;
        temp.next = null; // optional but suggested.
        return temp.element;
    }

    public T removeLast() {
        if (head == null || head.next == null)
            return removeFirst();

        Node<T> secondToLast = head;
        Node<T> last = secondToLast.next;
        while (last.next != null) {
            secondToLast = secondToLast.next;
            last = last.next;
        }

        secondToLast.next = null; // very important: many bugs are from omission of this step.
        return last.element;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node<T> search(T a) {
        Node<T> cur = head;
        while (cur != null && cur.element != a)
            cur = cur.next;
        return cur;
    }

    public String toString() {
        if (head == null)
            return "The list is empty.";
        StringBuilder sb = new StringBuilder();
        sb.append(head.element);
        Node<T> cur = head.next;
        while (cur != null) {
            sb.append(" -> " + cur.element);
            cur = cur.next;
        }
        return sb.toString();
    }

    // reverse the elements in a linked list.
    public void reverse(Node<T> node) {
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        System.out.println(list.isEmpty());
        list.insertFirst(37);
        System.out.println(list.isEmpty());
        list.insertFirst(99);
        list.insertFirst(12);
        list.insertLast(38);
        System.out.println("After inserting 37, 99, 12 in the front and then 38 at the end, we get");
        System.out.println(list);

        Node<Integer> n99 = list.head;
        while (n99.next != null)
            n99 = n99.next;
        list.insertAfter(n99, 75);
        System.out.println(list);
        list.insertBefore(n99, 55);
        System.out.println(list);

        System.out.println("delete the last, which is " + list.removeLast());
        System.out.println(list);
    }
}