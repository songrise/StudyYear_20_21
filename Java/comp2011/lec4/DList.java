package comp2011.lec4;

/**
 * 
 * @author yixin cao
 * 
 * A doubly linked list with generics.
 *
 */
public class DList<T> {
    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> previous;

        Node(T a) {
            element = a;
            next = previous = null;
        }
    }

	private Node<T> head; // firstNode
	private Node<T> tail; // lastNode

	public DList() {
        head = tail = null;
	}

	public boolean isEmpty() {
        return tail == null; //or head == null 
	}

	public void err() {
		System.out.println("Oops...");
	}
	
	public void insertFirst(T e) {
        Node<T> newNode = new Node<T>(e);
        newNode.next = head;
        newNode.previous = null;
        if (isEmpty()) { 
            tail = head = newNode; 
            return;
        }
        head.previous = newNode;
        head = newNode;
	}
	
    public void insertLast(T e) {
        Node<T> newNode = new Node<T>(e);
        newNode.previous= tail;
        newNode.next = null;
        if (isEmpty()) { head = tail = newNode; return;}
        tail.previous = newNode;
        tail = newNode;
    }

	public T removeFirst() {
        if (isEmpty()) { err(); return null;}
        T res = head.element;
        if (head == tail) { head = tail = null; return res; } 
	    head = head.next;
	    head.previous.next = null;
	    head.previous = null;
        return res;
	}

    public T removeLast() {
        if (head == tail) return removeFirst();
        T res = tail.element;
        tail = tail.previous;
        tail.next.previous = null;
        tail.next = null;
        return res;
    }

    public T removeLast2() {
        if (isEmpty()) { err(); return null;}
        T res = tail.element;
        if (tail == head) {tail = head = null; return res; } 
        tail = tail.previous;
        tail.next.previous = null;
        tail.next = null;
        return res;
	}

    public T delete(Node<T> node) {
        if (node == head) return removeFirst();
        if (node == tail) return removeLast();
        
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node.previous = node.next = null;
        return node.element;
    }

	public String toString() {
        if (head == null) return "The list is empty.";
        StringBuilder sb = new StringBuilder();
        Node<T> cur = head;
        while ( cur != null ) {
            sb.append(" - Previous: " + (cur.previous == null ? " Null " : cur.previous.element) + "\n");
            sb.append("    Current: " + cur.element + "\n");
            cur = cur.next;
        }
        return sb.toString();
    }
	
	// There is a bug! Please fix it and post on the forum.
	public void reverse() {
	    if (head == null || head == tail) return;
        Node<T> node = head;
	    while (node != null) {
	        Node<T> temp = node.previous;
	        node.previous = node.next;
	        node.next = temp;
	        node = node.next;
	    }
	    node = head;
	    head = tail;
	    tail = node;
	}
    
    public static void main(String[] args) {
        DList<String> list = new DList<String>();
        list.insertFirst("Shenzhen");
        list.insertFirst("Hong Kong");
        list.insertLast("Guangzhou");
        System.out.println(list);
        list.insertLast("Changsha");
        list.insertLast("Wuhan");
        list.insertLast("Zhengzhou");
        list.insertLast("Beijing");
        list.insertLast("Shenyang");
        System.out.println(list);
        System.out.println("delete the last, which is " + list.removeLast());
		System.out.println(list);
        list.reverse();
        System.out.println("after reversion, it becomes " + list);
    }
}
