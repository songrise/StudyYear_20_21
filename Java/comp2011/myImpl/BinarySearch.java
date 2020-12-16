package comp2011.myImpl;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = { 1, 9, 4, 2, 3, 19, 11, 8, 43, 5, 7 };
        Arrays.sort(arr);
        System.out.println(BinarySearch.binarySearch(arr, 7));
    }

    public static int binarySearch(int[] a, int key) {
        return binarySearch(a, 0, a.length - 1, key);
    }

    // the recursive version
    public static int binarySearch(int[] a, int l, int h, int key) {
        if (l > h)
            return 0x7fffffff;// for code simplicity, return INT_MAX if not found.
        int m = l + (h - l) / 2;
        if (a[m] == key) {
            return Math.min(m, binarySearch(a, l, m - 1, key));
        }
        return (key > a[m]) ? binarySearch(a, m + 1, h, key) : binarySearch(a, l, m - 1, key);
    }

}
