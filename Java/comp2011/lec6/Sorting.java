package comp2011.lec6;

import java.util.Arrays;

/**
 * 
 * @author yixin cao (October 6, 2020)
 *
 * Sorting algorithms, file 2.
 * 
 * The focus of this week is mergesort. 
 * Its idea is very simple, while the implementation is nontrivial.
 * 
 * We have three recursive implementations.
 * 
 */
public class Sorting {

    /*
     * Swapping the ith and jth element of the array a.
     */
    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    /*
     * This version has the flag, which is standard for bubble sort.
     */
    public static void recursiveBubble(int[] a, int end) {
        if (end == 0) return;
        // for (int i = 0; i < end; i++) if (a[i] > a[i+1])  swap(a, i, i+1);
        boolean flag = false;
        for (int i = 0; i < end; i++) 
            if (a[i] > a[i+1]) {
                flag = true;
                swap(a, i, i+1);
            }
        if (!flag) return; 
        recursiveBubble(a, end - 1);
    }
    
    public static void recursiveInsertion(int[] a, int begin) {
        
    }
    
    public static void recursiveSelection(int[] a, int begin) {
        if (begin == a.length - 1) return;
        int n = a.length;
        int min = begin;
        for (int i = begin+1; i < n; i++) {
            if (a[min] > a[i]) min = i;
        }
        swap(a, begin, min);
        recursiveSelection(a, begin+1);
    }
        
    /*
     * 1st recursive version of mergesort.
     * This version requires us to build two auxiliary arrays a1 and a2.
     * 
     */
    public static void rMergesortV1(int[] a) {
        if (a.length <= 1) return; // put the base case at the top.
        int n = a.length;

        // step 1: partition (almost) evenly;
        int[] b = new int[(n + 1) / 2];  // if n is odd, then (n+1)/2 > n/2.
        int[] c = new int[n / 2];
        for (int i = 0; i < (n + 1) / 2; i++) b[i] = a[i];
        for (int i = 0; i < n / 2; i++) c[i] = a[(n + 1) / 2 + i];
        /*
         * The four lines above can be replaced by the following two lines
         *  
         *     int[] b = Arrays.copyOfRange(a, 0, (n + 1) / 2);
         *     int[] c = Arrays.copyOfRange(a, (n + 1) / 2, n);
         * 
         * Note: in copyOfRange, the final index of the range is exclusive.
         */

        // step 2: recursively sort the two subarrays;
        rMergesortV1(b);
        rMergesortV1(c);
        
        // step 3: and then merge the sorted subarrays.
        /* (You may let {@code merge} return the merged array 
         * and write a = merge(b, c);.
         * But that creates yet another temporary array.)
         */ 
        merge(b, c, a);
    }
    /*
     * Merging two sorted arrays into one.
     * Both input arrays a1 and a2 are supposed to be already sorted. 
     * 
     */
    public static void merge(int[] a1, int[] a2, int[] a) {
        int ind1 = 0, ind2 = 0, ind = 0;
        while (ind1 < a1.length && ind2 < a2.length) {
            if ( a1[ind1] <= a2[ind2] ) a[ind++] = a1[ind1++];  // not stable if <= is replaced by <.
            else a[ind++] = a2[ind2++];
            /*
             *  The two lines above can be replaced by 
             *  
             *  a[ind++] = (a1[ind1] <= a2[ind2])? a1[ind1++]:a2[ind2++];
             */
            // 
        }
        // Don't forget the leftovers: we are not done yet!
        while (ind1 < a1.length) a[ind++] = a1[ind1++];
        while (ind2 < a2.length) a[ind++] = a2[ind2++];
    }
    
    /*
     * Mergesort version 2: the standard recursive version.
     * 
     * For an recursive algorithm, it's user-friendly to have a wrap-up 
     * so that the user can simply call mergesortRecursive(a); 
     */
    public static void rMergesortV2(int[] a, int low, int high) {
        if (high < 1 + low) return;
        int mid = low + (high - low) / 2;
        rMergesortV2(a, low, mid);
        rMergesortV2(a, mid+1, high);
        merge(a, low, mid, high);
    }
    
    /*
     * Merging two sorted parts into one.
     * The first part is from a[l] to a[m]; and the second from a[m + 1] to a[h].
     * The merged result will be stored from a[l] to a[h].
     * 
     * One way is to copy two parts into two arrays, and merge them.
     * We can save half space by only copying the first part (from low to mid).
     */
    private static void merge(int[] a, int low, int mid, int high) {
        /*
         * You can use the following three lines if you prefer.
         * int[] temp = new int[mid - low + 1];
         * for (int i = 0; i < temp.length; i ++) 
         *     temp[i] = a[low + i];
         */
        int[] temp = Arrays.copyOfRange(a, low, mid + 1);
        int i = 0, j = mid+1, k = low;
        while (i < temp.length && j <= high) 
            a[k++] = temp[i] <= a[j]?temp[i++]:a[j++];   // not stable if <= is replaced by <.
        while (i < temp.length) a[k++] = temp[i++];
        // There is no need to deal with the leftovers of the second part. why? 
    }
    /*
     * The second recursive version needs a wrapper.
     */
    public static void rMergesortV2(int[] a) {
        rMergesortV2(a, 0, a.length- 1);
    }
    
    /*
     * Mergesort version 3: iterative version.
     * 
     * However, this doesn't work, please try to fix it.
     */
    public static void iMergesort(int[] a) {
    }

    public static void main(String[] args) {
		int a[] = {10, 8, -4, 89, 2, 0, 4, -19, 200};
		System.out.println(Arrays.toString(a));
		// recursiveBubble(a, a.length-1);
		rMergesortV2(a);
		//rMergesortV1(a);
		System.out.println(Arrays.toString(a));
	}
}
