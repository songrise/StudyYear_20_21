package comp2011.lec5;

import java.util.Arrays;

/**
 * 
 * @author yixin cao (October 6, 2020)
 *
 * recursive implementation of simple sorting algorithms:
 * bubble, insertion, and selection.
 * 
 */
public class Sorting {

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    // the first correct implementation of recursive sorting 
    // will get bonus
    public static void recursiveBubble(int[] a, int end) {
        int n = a.length;
        if (end == 0) return;
        for (int i = 0; i < end; i++) {
            if (a[i] > a[i+1]) {
                swap(a, i, i+1);
            }
        }
        recursiveBubble(a, end - 1);
    }

    // From SRIVASTAVA, Srijan, not verified.
    public static void recursiveInsertion (int arr[], int n){
        if(n<=1) return;
        insertionSort(arr, n-1);
        int last = arr[n-1];
        int i = n-2;
        while(i >=0 && arr[i] > last){
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i + 1] = last;
    }
    
    public static void recursiveSelection(int[] a, int begin) {
        if (begin == a.length - 1) return;  //better to keep the base case at the top.
        int n = a.length;
        int min = begin;
        for (int i = begin+1; i < n; i++) 
            if (a[min] > a[i]) min = i;
        swap(a, begin, min);
        recursiveSelection(a, begin+1);
    }
        
    public static void selectionSort(int[] a) {
	int n = a.length;
	int[] newA = new int[n];
	int min;
	for (int i = 0; i < n; i++) {
	    min = 0;
	    for (int j = 1; j < n; j++) {
		if (a[min] > a[j]) min = j;
	    }
	    newA[i] = a[min];
	    // should be a very large number
	    a[min] = 1000000;
	}
	for (int i = 0; i < n; i++) a[i] = newA[i];
    }
    
    public static void selectionSortV2(int[] a) {
	int n = a.length;
	int min;
	for (int i = 0; i < n-1; i++) {
	    min = i;
	    for (int j = i+1; j < n; j++) {
		if (a[min] > a[j]) min = j;
	    }
	    int temp = a[min];
	    a[min] = a[i];
	    a[i] = temp;
	}
    }
    
    public static void main(String[] args) {
	int a[] = {10, 8, -4, 89, 2, 0};
	System.out.println(Arrays.toString(a));
	// recursiveBubble(a, a.length - 1);
	recursiveSelection(a, 0);
	System.out.println(Arrays.toString(a));
    }
}
