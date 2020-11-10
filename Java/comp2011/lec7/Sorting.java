package comp2011.lec7;

import java.util.*;
import java.security.SecureRandom;

/**
 * 
 * @author yixin cao (October 10, 2020)
 *
 * Sorting algorithms, file 3.
 * 
 * The focus of this week is quicksort. 
 * Its idea is very simple, while the in-place implementation is very challenging.
 * 
 * There are two ways to partition, the Hoare partition scheme and the Lomuto partition scheme. 
 * 
 */
public class Sorting {
    // This algorithm is not good because it takes too much space.
    // But it's easy to write and let you have the basic ideas of quicksort.
    public static void naive(int[] a, int begin, int end) {
        if(begin>=end) return;
        int pivot = a[end];
        int[] b = Arrays.copyOfRange(a, begin, end + 1);
        /* 
         * int[] b = new int[end - begin + 1];
         * for (i = 0; i < b.length; i++)
         *    b[i] = a[begin + i];
         */
        int l = begin, r = end;
        for (int i = 0; i < b.length; i++) {
            if (b[i] <= pivot) a[l++] = b[i];
            else a[r--] = b[i];
        }
        System.out.println(Arrays.toString(a));
        // absolutely wrong: naive(a, begin, l - 1);
        naive(a, begin, l - 2); // also ok r - 1.
        naive(a, r + 1, end);   // also ok r.
    }

    public static void quicksort(int[] a) {
            // quicksort(a, 0, a.length - 1);
            naive(a, 0, a.length - 1);
    }

    public static void main(String args[]){
        // int[] a = {3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9};
        /* int size = 100;
         int[] a = new int[size];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < size; i++)
            a[i] = random.nextInt(size);
         */
        int[] a = {1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8};
        System.out.println(Arrays.toString(a));
        quicksort(a);
        System.out.println(Arrays.toString(a));
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println(Arrays.toString(b));
        quicksort(b);
        System.out.println(Arrays.toString(b));
    }
}
