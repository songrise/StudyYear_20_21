package comp2011.myImpl.sorting.advanced;

import java.security.SecureRandom;
import java.util.Arrays;

public class HeapSort {

    public static void main(String args[]) {
        // int size = 10;
        // int[] a = new int[size];
        // SecureRandom random = new SecureRandom();
        // for (int i = 0; i < size; i++)
        // a[i] = random.nextInt(size);
        test();

        int[] a = { 3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9 };
        System.out.println(Arrays.toString(a));
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }

    static void test() {
        for (int i = 0; i < 1000; i++) {
            final int SIZE = 100;
            SecureRandom random = new SecureRandom();
            int size = random.nextInt(SIZE) % SIZE;
            int[] a = new int[size];
            for (int j = 0; j < size; j++)
                a[j] = random.nextInt(size);

            int[] cpy = Arrays.copyOf(a, a.length);
            // System.out.println(Arrays.toString(cpy));
            Arrays.sort(cpy);
            heapSort(a); // !sort
            if (!Arrays.equals(a, cpy)) {
                System.out.println(Arrays.toString(cpy));
                System.out.println(Arrays.toString(a));
                System.out.println();
            }
        }
        System.out.println("finished.");
    }

    private static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    // the crisp version.
    private static void down(int[] a, int p, int size) {
        if (p * 2 + 2 > size)
            return;
        int larger = p * 2 + 1; // left child
        if (larger + 1 < size && a[larger] < a[larger + 1])
            larger++; // right
        if (a[p] >= a[larger])
            return;
        swap(a, p, larger);
        down(a, larger, size);
    }

    private static void up(int[] a, int c) {
        if (c == 0)
            return;
        int p = (c - 1) / 2;
        if (a[c] <= a[p])
            return;
        swap(a, c, p);
        up(a, p);
    }

    // iterative version
    private static void iUp(int[] a, int c) {
    }

    // iterative version
    private static void iDown(int[] a, int p) {
    }

    public static void heapSort(int[] a) {
        for (int i = 1; i < a.length; i++)
            up(a, i);
        for (int size = a.length - 1; size > 0; size--) {
            swap(a, 0, size); // a simler way of deleteMax.
            down(a, 0, size);
            // Uncomment the following line to see the progress.
            // System.out.println(Arrays.toString(a));
        }
    }

}
