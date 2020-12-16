package comp2011.myImpl.dataStructure;

import java.security.SecureRandom;
import java.util.Arrays;

public class Heap {

    private int[] data;
    int size;

    public static void main(String[] args) {
        int[] arr = new int[] { 50, 40, 60, 70, 80, 30, 20, 65, 55, 45, 35, 56, 54, 47, 41};
        // System.out.println(Arrays.toString(Heap.heapify(arr)));
        Heap heap = new Heap(32);
        heap.batchAdd(arr);
        System.out.println(heap);
        // test();
    }

    static void test() { // use heap sort to test.
        Heap heap = new Heap(20);
        for (int i = 0; i < 1000; i++) {
            SecureRandom random = new SecureRandom();
            int size = random.nextInt(100) % 100;
            int[] a = new int[size];
            for (int j = 0; j < size; j++)
                a[j] = random.nextInt(size);

            int[] cpy = Arrays.copyOf(a, a.length);
            // System.out.println(Arrays.toString(cpy));
            Arrays.sort(cpy);
            Heap.heapSort(a);
            if (!Arrays.equals(a, cpy)) {
                System.out.println(Arrays.toString(cpy));
                System.out.println(Arrays.toString(a));
                System.out.println();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Heap(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void batchAdd(int[] labels) {
        if (labels == null) {
            return;
        }
        for (int i : labels) {
            insert(i);
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // the intuitive but slightly boring version.
    public void down(int p) {
        if (size < 2 * p + 2)
            return;
        int leftChild = p * 2 + 1;
        int rightChild = leftChild + 1;
        int largerChild = leftChild;
        // don't forget to check rightChild < size
        if (rightChild < size && data[leftChild] < data[rightChild])
            largerChild = rightChild;
        if (data[p] >= data[largerChild])
            return;
        swap(p, largerChild);
        down(largerChild);
    }

    // the crisp version.
    public void downV2(int p) {
        if (p * 2 + 2 > size)
            return;
        int larger = p * 2 + 1;
        if (larger + 1 < size && data[larger] < data[larger + 1])
            larger++;
        if (data[p] >= data[larger])
            return;
        swap(p, larger);
        downV2(larger);
    }

    // The running time of iDown is O(logn).
    public void iDown(int p) {
        int i = p;
        while (2 * i + 2 <= size) {
            int leftChild = i * 2 + 1;
            int rightChild = leftChild + 1;
            int largerChild = leftChild;
            if (rightChild < size && data[leftChild] < data[rightChild])
                largerChild = rightChild; // find larger child
            if (data[i] >= data[largerChild])// if parent largerEqual than both children.
                break;
            swap(i, largerChild);
            i = largerChild;
        }
    }

    // The running time of iUp is O(logn).
    public void iUp(int c) {
        int i = c;
        while (i != 0) {
            int p = (i - 1) / 2; // parent index
            if (data[i] <= data[p])
                break;
            swap(i, p);
            i = p;
        }
    }

    private void err(String message) {
        System.out.println("oops..." + message);
    }

    public int deleteMax() {
        if (size == 0) {
            err("downflow");
            return -1;
        }

        int ans = data[0];
        data[0] = data[--size];
        iDown(0);
        return ans;
    }

    public void up(int c) {
        if (c == 0)
            return;
        int p = (c - 1) / 2;
        if (data[c] <= data[p])
            return;
        swap(c, p);
        up(p);
    }

    public void insert(int num) {
        // a more friendly way is to double the size of arr
        if (size == data.length) {
            err("overflow");
            return;
        }
        data[size] = num;
        iUp(size++);
    }

    public void preorder() {
        preorder(0);
    }

    public void preorder(int i) {
        if (i >= size)
            return;
        System.out.print(data[i] + ", ");
        inorder(i * 2 + 1);
        inorder(i * 2 + 2);
    }

    public void inorder() {
        inorder(0);
    }

    public void inorder(int r) {
        if (r >= size)
            return;
        inorder(r * 2 + 1);
        System.out.print(data[r] + ", ");
        inorder(r * 2 + 2);
    }

    // try to implement the following methods.
    public void postorder() {
        postorder(0);
    }

    public void postorder(int r) {
        if (r >= size)
            return;
        inorder(r * 2 + 1);
        inorder(r * 2 + 2);
        System.out.print(data[r] + ", ");
    }

    public void levelDisplay() {
    }

    private int maxChild(int i) {
        int left = 2 * i + 1;
        int right = left + 1;
        if (left > size - 1) {
            return -1;
        }
        if (left == size - 1) {
            return data[left];
        }
        return Math.max(data[left], data[right]);
    }

    // try to finish this.
    private int minChild(int i) {
        int left = 2 * i + 1;
        int right = left + 1;
        if (left > size - 1) {
            return -1;
        }
        if (left == size - 1) {
            return data[left];
        }
        return Math.min(data[left], data[right]);
    }

    /*
     * This is a naive version of heapsort, and it needs extra space.
     */
    public static void heapSort(int[] arr) { // in-place
        Heap heap = new Heap(arr.length);
        for (int i = 0; i < arr.length; i++)
            heap.insert(arr[i]);
        for (int i = arr.length - 1; i >= 0; i--)
            arr[i] = heap.deleteMax();
    }

    /**
     * 
     * @param arr
     * @return heapified arr.
     */
    public static int[] heapify(int[] arr) {
        Heap heap = new Heap(arr.length);
        for (int i = 0; i < arr.length; i++)
            heap.insert(arr[i]);
        return heap.data;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(data, 0, size));
    }

}
