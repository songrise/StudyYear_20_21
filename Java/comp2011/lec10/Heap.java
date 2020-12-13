package comp2011.lec10;

import java.util.Arrays;

/**
 * 
 * @author yixin cao (October 10, 2020)
 *
 * The heap data structure storing objects (using generics).
 * 
 * Again, we use an explicit variable key and use it for comparisons  
 * This a bad idea. See more at the comments of  {@code comp2011.lec8.BinarySearchTree}.
 */
public class Heap<T> {
    private static class Node<T> {
        int key;
        T obj;

        public Node(int key, T element) {
            this.key = key;
            this.obj = element;
        }

        public String toString() {
            return key + ": " + obj;
        }
    }

	private Node<T>[] data;
	int size;
	
    @SuppressWarnings("unchecked")
    public Heap(int capacity) {
    	data = new Node[capacity];
    	size = 0;
    }
    
    private void swap(int i, int j) {
        Node<T> temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // the intuitive but slightly boring version.
    public void down(int p) {
        if (size < 2 * p + 2) return;
        int leftChild = p * 2 + 1;
        int rightChild = leftChild + 1;
        int largerChild = leftChild;
        // don't forget to check rightChild < size 
        if (rightChild < size && data[leftChild].key < data[rightChild].key)
            largerChild = rightChild;
        if (data[p].key >= data[largerChild].key) return;
        swap(p, largerChild);
        down(largerChild);
    }
    // the crisp version.
    public void downV2(int p) { 
        if (p * 2 + 2 > size) return;
        int larger = p * 2 + 1;
        if (larger + 1 < size && data[larger].key < data[larger+1].key) 
            larger++;
        if (data[p].key >= data[larger].key) return;
        swap(p, larger);
        downV2 (larger);
    }
    
    private void err(String message) {
        System.out.println("oops..." + message);
    }

    public T deleteMax() {
        if (size == 0) {err("downflow"); return null;}
        
        T ans = data[0].obj;
        data[0] = data[--size];
        down(0);
        return ans; 
    }
    public void up(int c) {
        if (c == 0) return;
        int p = (c - 1) / 2;
        if (data[c].key <= data[p].key) return;
        swap(c, p);
        up(p);
    }
    public void insert(int key, T x) {
        // a more friendly way is to double the size of arr
        if (size == data.length) {err("overflow"); return;}
        data[size] = new Node<T>(key, x);
        up(size++);
    }

    public void preorder() { }
    public void inorder() {
        inorder(0);
    }
    public void inorder(int r) {
        if (r >= size) return;
        inorder(r*2 + 1);
        System.out.println(data[r]);
        inorder(r*2 + 2);
    }
    // try to implement the following methods.
    public void postorder() { }
    public void levelDisplay() { }
    
    private int maxChild(int i) {
        // what if no child?
        // one child?
        // both?
        return -1;
    }    
    
    // try to finish this.
    private int minChild(int i) {
        return -1;
    }    
    
    /*
     * This is a naive version of heapsort, and it needs extra space. 
     */
    public void heapSort(int[] keys, T[] data) {
        Heap<T> heap = new Heap<T>(keys.length);
        for (int i = 0; i < keys.length; i++)
            heap.insert(keys[i], data[i]);
        for (int i = keys.length - 1; i >= 0; i--) 
            data[i] = heap.deleteMax();
    }
    
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(data, 0, size));
    }
    
    static class Student {
        int id;
        String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        Heap<Student> heap = new Heap<Student>(20);
        heap.insert(214, new Student(214, "Chan Eason"));
        heap.insert(562, new Student(562, "Cheung Jacky"));
        heap.insert( 83, new Student( 83, "Ho Denise"));
        heap.insert(115, new Student(115, "Joey Yung"));
        heap.insert( 97, new Student( 97, "Andy Lau"));
        heap.insert(722, new Student(722, "Leung Gigi"));
        heap.insert(398, new Student(398, "Tang Gloria"));
        heap.insert(798, new Student(798, "Mickey"));
        heap.insert(408, new Student(408, "Teddy"));
        heap.insert(199, new Student(199, "Tse Kay"));
        heap.insert(337, new Student(337, "McDull"));
        System.out.println(heap);
        heap.deleteMax();
        System.out.println("=====================");
        System.out.print(heap);
    }
}

