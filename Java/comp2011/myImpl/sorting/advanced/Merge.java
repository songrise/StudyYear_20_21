package comp2011.myImpl.sorting.advanced;

import java.security.SecureRandom;
import java.util.Arrays;

public class Merge {

    public static void main(String args[]) {
        test();
        // int[] a = { 3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9 };

        // // int[] a = { 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8 };
        // System.out.println("original: " + Arrays.toString(a));
        // System.out.println("qsort: " + Arrays.toString(a));
        // mergeSort(a);
        // System.out.println("mergesort: " + Arrays.toString(a));

        // int[] b = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        // System.out.println(Arrays.toString(b));
        // selectionSort(b);
        // System.out.println(Arrays.toString(b));
        // quicksort(b);
        // System.out.println(Arrays.toString(b));
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
            mergeSort(a); // !sort
            if (!Arrays.equals(a, cpy)) {
                System.out.println(Arrays.toString(cpy));
                System.out.println(Arrays.toString(a));
                System.out.println();
            }
        }
        System.out.println("finished.");
    }

    public static void mergeSort(int[] arr) {
        // rMergesortV1(arr);
        // rMergesortV2(arr, 0, arr.length - 1);
        iMergesort(arr);
    }

    public static void rMergesortV1(int[] a) {
        if (a.length <= 1)
            return; // put the base case at the top.
        int n = a.length;

        // step 1: partition (almost) evenly;
        int[] b = new int[(n + 1) / 2]; // if n is odd, then (n+1)/2 > n/2.
        int[] c = new int[n / 2];
        for (int i = 0; i < (n + 1) / 2; i++)
            b[i] = a[i];
        for (int i = 0; i < n / 2; i++)
            c[i] = a[(n + 1) / 2 + i];
        /*
         * The four lines above can be replaced by the following two lines
         * 
         * int[] b = Arrays.copyOfRange(a, 0, (n + 1) / 2); int[] c =
         * Arrays.copyOfRange(a, (n + 1) / 2, n);
         * 
         * Note: in copyOfRange, the final index of the range is exclusive.
         */

        // step 2: recursively sort the two subarrays;
        rMergesortV1(b);
        rMergesortV1(c);

        // step 3: and then merge the sorted subarrays.
        /*
         * (You may let {@code merge} return the merged array and write a = merge(b,
         * c);. But that creates yet another temporary array.)
         */
        merge(b, c, a);
    }

    public static void merge(int[] a1, int[] a2, int[] a) {
        int ind1 = 0, ind2 = 0, ind = 0;
        while (ind1 < a1.length && ind2 < a2.length) {
            if (a1[ind1] <= a2[ind2])
                a[ind++] = a1[ind1++]; // not stable if <= is replaced by <.
            else
                a[ind++] = a2[ind2++];
            /*
             * The two lines above can be replaced by
             * 
             * a[ind++] = (a1[ind1] <= a2[ind2])? a1[ind1++]:a2[ind2++];
             */
            //
        }
        // Don't forget the leftovers: we are not done yet!
        while (ind1 < a1.length)
            a[ind++] = a1[ind1++];
        while (ind2 < a2.length)
            a[ind++] = a2[ind2++];
    }

    /*
     * Mergesort version 2: the standard recursive version.
     * 
     * For an recursive algorithm, it's user-friendly to have a wrap-up so that the
     * user can simply call mergesortRecursive(a);
     */
    public static void rMergesortV2(int[] a, int low, int high) {
        if (high < 1 + low)
            return;
        int mid = low + (high - low) / 2;
        rMergesortV2(a, low, mid);
        rMergesortV2(a, mid + 1, high);
        mergeV2(a, low, mid, high);
    }

    /*
     * Merging two sorted parts into one. The first part is from a[l] to a[m]; and
     * the second from a[m + 1] to a[h]. The merged result will be stored from a[l]
     * to a[h].
     * 
     * One way is to copy two parts into two arrays, and merge them. We can save
     * half space by only copying the first part (from low to mid).
     */
    private static void mergeV2(int[] a, int low, int mid, int high) {
        /*
         * You can use the following three lines if you prefer. int[] temp = new int[mid
         * - low + 1]; for (int i = 0; i < temp.length; i ++) temp[i] = a[low + i];
         */
        int[] temp = Arrays.copyOfRange(a, low, mid + 1);
        int i = 0, j = mid + 1, k = low;
        while (i < temp.length && j <= high)
            a[k++] = temp[i] <= a[j] ? temp[i++] : a[j++]; // not stable if <= is replaced by <.
        while (i < temp.length)
            a[k++] = temp[i++];
        // There is no need to deal with the leftovers of the second part. why?
    }

    /*
     * The second recursive version needs a wrapper.
     */
    public static void rMergesortV2(int[] a) {
        rMergesortV2(a, 0, a.length - 1);
    }

    /*
     * Mergesort version 3: iterative version.
     * 
     * However, this doesn't work, please try to fix it.
     */
    // public static void iMergesort(int[] a) {
    // if (a.length == 0) {
    // return;
    // }
    // int i, j, n = a.length;
    // for (i = 2; i < n; i *= 2) {
    // for (j = 0; j < n - i / 2 + 1; j += i) {// change this line
    // int h = j + i - 1;
    // if (h >= n)
    // h = n - 1;
    // mergeV2(a, j, j + i / 2 - 1, h);
    // }
    // }
    // mergeV2(a, 0, i / 2 - 1, n - 1);// add this line ! last iteration
    // }

    public static void iMergesort(int[] a) {
        if (a.length == 0) {
            return;
        }
        int l = a.length;
        for (int size = 1; size < l; size *= 2) {
            // System.out.println(".");
            for (int i = 0; i < l - size; i += 2 * size) {
                int mid = i + size - 1;
                int high = Math.min(i + size + size - 1, l - 1);
                mergeV2(a, i, mid, high);
            }
        }
    }

}
