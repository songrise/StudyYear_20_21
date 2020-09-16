package comp2011.lec2;

import java.security.SecureRandom;
import java.util.Arrays;

import comp2011.lec1.Sorting;
public class ArraySearch {

	private static int linearSearch(int[] a, int key) {
	    int n = a. length ;
	    for (int i = 0; i < n; i++)
	        if (a[i] == key) return i;
	    return -1;
	}

    // the non-recursive (iterative) version
    // can you change this method to always return the first occurence of key in a?
	public static int binarySearch(int[] a, int key) {
		int n = a.length;
		int low = 0, high = n - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (a[mid] == key) return mid;
			else if (a[mid] > key) high = mid - 1;
			else if (a[mid] < key) low = mid + 1; 
		}
		return -1;
	}

	// the recursive version
	public static int binarySearch(int[] a, int l, int h, int key) {
		if(l > h) return -1;
		int m = l + (h - l) / 2;
		if (a[m] == key) return m;
		return (key > a[m]) ? binarySearch(a, m + 1, h, key) 
				: binarySearch(a, l, m - 1, key);
	}
    
	public static void main(String[] args) {
		int n = 30;
		int[] a = new int[n];
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < n; i++)
			a[i] = random.nextInt(20);
		System.out.println(Arrays.toString(a));
		System.out.println(linearSearch(a, 10));

		Sorting.selectionSort(a);
		System.out.println(Arrays.toString(a));

		System.out.println(binarySearch(a, 10));
	}
}
