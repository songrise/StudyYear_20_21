package comp2011.lec7;

import java.util.*;
import java.security.SecureRandom;

/**
 * 
 * @author yixin cao (October 10, 2020)
 *
 *         Sorting algorithms, file 3.
 * 
 *         The focus of this week is quicksort. Its idea is very simple, while
 *         the in-place implementation is very challenging.
 * 
 *         There are two ways to partition, the Hoare partition scheme and the
 *         Lomuto partition scheme.
 * 
 */
public class Sorting {
    // This algorithm is not good because it takes too much space.
    // But it's easy to write and let you have the basic ideas of quicksort.
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void naive(int[] a, int begin, int end) {
        if (begin >= end)
            return;
        int pivot = a[end];
        int[] b = Arrays.copyOfRange(a, begin, end + 1);
        /*
         * int[] b = new int[end - begin + 1]; for (i = 0; i < b.length; i++) b[i] =
         * a[begin + i];
         */
        int l = begin, r = end;
        for (int i = 0; i < b.length; i++) {
            if (b[i] <= pivot)
                a[l++] = b[i];
            else
                a[r--] = b[i];
        }
        // System.out.println(Arrays.toString(a));
        // absolutely wrong: naive(a, begin, l - 1);
        naive(a, begin, l - 2); // also ok r - 1.
        naive(a, r + 1, end); // also ok r.
    }

    /**
     * 
     * @param arr
     * @param lo
     * @param hi  highest index
     * @return partition index
     */
    private static int lomutoPartition(int[] arr, int lo, int hi) {

        int pivot = arr[hi];
        int i = lo, j = lo;
        for (; i < hi + 1; i++) { // note i must be equal to hi.
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                j++;
            }
        }

        return j;
    }

    private static void quicksortLomuto(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partition = lomutoPartition(arr, lo, hi);
        System.out.println(Arrays.toString(arr));
        quicksortLomuto(arr, lo, partition - 2);
        quicksortLomuto(arr, partition, hi); // partition + 1
    }

    public static void quicksort(int[] a) {
        // quicksort(a, 0, a.length - 1);
        naive(a, 0, a.length - 1);
        // quicksortLomuto(a, 0, a.length - 1);
    }

    // !=====================

    public static void mergeSort(int[] arr) {
        rmergeSort(arr, 0, arr.length - 1);
    }

    private static void rmergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        rmergeSort(arr, lo, mid);
        rmergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = Arrays.copyOfRange(arr, low, mid + 1);
        int i = 0, j = mid + 1, k = low;
        while (i < temp.length && j < high) {
            if (temp[i] <= arr[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = arr[j++];
            }
        }
        while (i < temp.length) {
            arr[k++] = temp[i++];
        }
    }
    // !=====================

    // public static void selectionSort(int[] arr) {
    // int l = arr.length;
    // int min = 0x80000000;
    // for (int i = 0; i < l - 1; i++) {
    // min = i;
    // for (int j = i + 1; j < l; j++) {
    // if (arr[min] >= arr[j]) {
    // min = j;
    // }
    // swap(arr, min, i);
    // }
    // }
    // }

    // public static void tsort(int[] a) {
    // int n = a.length;
    // for (int i = n - 1; i < 0; --i) {
    // int k = i;
    // for (int j = 0; j < i; j++) {
    // if (a[k] < a[j])
    // k = j;
    // swap(a, i, k);
    // }

    // }
    // }

    public static void main(String args[]) {
        int[] a = { 3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9 };

        // int size = 10;
        // int[] a = new int[size];
        // SecureRandom random = new SecureRandom();
        // for (int i = 0; i < size; i++)
        // a[i] = random.nextInt(size);

        // int[] a = { 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8 };
        System.out.println("original: " + Arrays.toString(a));
        quicksort(a);
        System.out.println("qsort: " + Arrays.toString(a));
        // mergeSort(a);
        // System.out.println("mergesort: " + Arrays.toString(a));

        // int[] b = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        // System.out.println(Arrays.toString(b));
        // selectionSort(b);
        // System.out.println(Arrays.toString(b));
        // quicksort(b);
        // System.out.println(Arrays.toString(b));
    }
}
