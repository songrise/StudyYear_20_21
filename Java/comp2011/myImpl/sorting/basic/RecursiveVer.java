package comp2011.myImpl.sorting.basic;

import java.security.SecureRandom;
import java.util.Arrays;

public class RecursiveVer {
    

    public static void main(String[] args) {
        // int a[] = { 10, 8, -4, 89, 2, 0 };
        // System.out.println(Arrays.toString(a));
        // // recursiveBubble(a, a.length - 1);
        // recursiveSelection(a, 0);
        // System.out.println(Arrays.toString(a));
        test();
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
            insertionSort(a); // !sort
            if (!Arrays.equals(a, cpy)) {
                System.out.println(Arrays.toString(cpy));
                System.out.println(Arrays.toString(a));
                System.out.println();
            }
        }
        System.out.println("finished.");
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // ! wrappers

    public static void bubbleSort(int[] a) {
        recursiveBubble(a, a.length - 1);
    }

    public static void selectionSort(int[] a) {
        recursiveSelection(a, 0);
    }

    public static void insertionSort(int[] a) {
        recursiveInsertion(a, a.length);
    }

    /*
     * This version has the flag, which is standard for bubble sort.
     */
    public static void recursiveBubble(int[] a, int end) {
        if (end == 0)
            return;
        // for (int i = 0; i < end; i++) if (a[i] > a[i+1]) swap(a, i, i+1);
        boolean flag = false;
        for (int i = 0; i < end; i++)
            if (a[i] > a[i + 1]) {
                flag = true;
                swap(a, i, i + 1);
            }
        if (!flag)
            return;
        recursiveBubble(a, end - 1);
    }

    public static void recursiveInsertion(int arr[], int n) {
        if (n == 0)
            return;
        recursiveInsertion(arr, n - 1);
        int last = arr[n - 1];
        int i = n - 2;
        while (i >= 0 && arr[i] > last) {
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i + 1] = last;
    }

    public static void recursiveSelection(int[] a, int begin) {
        if (begin == a.length - 1)
            return;
        int n = a.length;
        int min = begin;
        for (int i = begin + 1; i < n; i++) {
            if (a[min] > a[i])
                min = i;
        }
        swap(a, begin, min);
        recursiveSelection(a, begin + 1);
    }

}
