package comp2011.myImpl;

import java.security.SecureRandom;
import java.util.Arrays;

public class Testing {
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
            quickSort(a);
            if (!Arrays.equals(a, cpy)) {
                System.out.println(Arrays.toString(cpy));
                System.out.println(Arrays.toString(a));
                System.out.println();
            }
        }
        System.out.println("finished.");
    }

    private static void selectionSort(int[] a) {
        if (a == null) {
            return;
        }
        int min;
        for (int i = 0; i < a.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    private static void insertionSort(int[] a) {
        if (a == null) {
            return;
        }
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (a[j] <= key) {
                    break;
                }
                swap(a, j, j + 1);
            }
            a[j + 1] = key;
        }
    }

    private static void bubbleSort(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length && flag; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
        }
    }

    private static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int lo, int hi) {
        if (arr == null || lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);

    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        int[] temp = Arrays.copyOfRange(arr, lo, mid + 1);
        int i = 0, j = mid + 1, k = lo;
        while (i < temp.length && j <= hi) {
            arr[k++] = temp[i] <= arr[j] ? temp[i++] : arr[j++];
        }
        while (i < temp.length) {
            arr[k++] = temp[i++];
        }
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = arr[hi];
        int i = lo, j = lo;
        for (; i < hi + 1; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                j++;
            }
        }

        quickSort(arr, lo, j - 2);
        quickSort(arr, j, hi);

    }

    private static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

}
