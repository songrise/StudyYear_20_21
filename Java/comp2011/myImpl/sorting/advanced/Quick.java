package comp2011.myImpl.sorting.advanced;

import java.security.SecureRandom;
import java.util.Arrays;

public class Quick {
    public static void main(String[] args) {
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
            quickSort(a); // !sort
            if (!Arrays.equals(a, cpy)) {
                System.out.println(Arrays.toString(cpy));
                System.out.println(Arrays.toString(a));
                System.out.println();
            }
        }
        System.out.println("finished.");
    }

    // !wrapper
    public static void quickSort(int[] a) {
        // quicksort(a, 0, a.length - 1);
        // naive(a, 0, a.length - 1);
        // quickSortLomuto(a, 0, a.length - 1);
        quickSortHoare(a, 0, a.length - 1);
    }

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
        int[] b = Arrays.copyOfRange(a, begin, end + 1);// copy of original array.
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
        // the pivot should be at index l-1 or r. (which implies l-1 == r)
        naive(a, begin, l - 2); // also ok r - 1.
        naive(a, r + 1, end); // also ok r.
    }

    // ! lomuto partition
    /**
     * 
     * @param arr
     * @param lo
     * @param hi  highest index
     * @return partition index +1
     */
    private static void quickSortLomuto(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partition = lomutoPartition(arr, lo, hi);
        // System.out.println(Arrays.toString(arr));
        quickSortLomuto(arr, lo, partition - 2);
        quickSortLomuto(arr, partition, hi); // partition + 1
    }

    private static int lomutoPartition(int[] arr, int lo, int hi) {

        int pivot = arr[hi];
        int i = lo, j = lo;
        for (; i < hi + 1; i++) { // !! note i must be able to reach hi.
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                j++;
            }
        }

        return j; // ! j is not the index of partition, but pivot + 1, since j++;
    }

    // ! hoare partition

    public static void quickSortHoare(int[] a, int begin, int end) {
        if (begin >= end)
            return;
        // int i = begin - 1, j = end + 1;
        // int pivot = a[end];
        // while (i < j) {
        // while (a[++i] < pivot)
        // ;
        // while (a[--j] > pivot)
        // ;
        // if (i < j)
        // swap(a, i, j);
        // }
        int i = hoarePartition(a, begin, end);
        quickSortHoare(a, begin, i - 1);
        quickSortHoare(a, i, end);
    }

    static int hoarePartition(int[] arr, int begin, int end) {
        if (begin >= end) {
            return -1;
        }
        int i = begin - 1, j = end + 1;// !notice -1 and +1
        int pivot = arr[end];
        while (i < j) { // ?? they cannot be equal
            while (arr[++i] < pivot) {
                // pass
            }
            while (arr[--j] > pivot) {
                // pass
            }
            if (i < j) { // !notice this condition
                swap(arr, i, j);
            }
        }
        return i; //
    }

}
