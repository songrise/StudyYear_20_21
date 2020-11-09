package comp2011.lec8;

import java.util.*;
import java.security.SecureRandom;
 
/**
 * 
 * @author yixin cao (October 20, 2020)
 *
 * Sorting algorithms, file 4.
 * 
 * Here is a collection of different implementations of sorting algorithms. 
 * Most of them are from previous students.
 * 
 * Please send me your codes. 
 * 
 */
public class Sorting {

    // the recursive version of selection sort.
    public static void selection(int[] a, int begin, int end) {
    	if (begin + 1 >= end) return;
    	int min = begin;
    	for (int i = begin + 1; i < end; i++) 
    		if (a[i] < a[min]) min = i;
    	int temp = a[begin];
    	a[begin] = a[min];
    	a[min] = temp;
    	selection(a, begin + 1, end);
    }
    
    // both a1 and a2 are previously sorted
    // a.length = a1.length + a2.length
    //
    // updated; significantly simpler than the previous week.
    public static void merge(int[] a1, int[] a2, int[] a) {
    	int i = 0, j1 = 0, j2 = 0;
    	while (j1 != a1.length && j2 != a2.length) {
    		a[i++] =(a1[j1] < a2[j2])? a1[j1++]: a2[j2++]; 
    	} 
    	for (; j1 < a1.length; j1++)
    		a[i++] = a1[j1];
    	for (; j2 < a2.length; j2++)
    		a[i++] = a2[j2];
    }
    
    public static void merge(int[] a, int l, int m, int h) {
    	int[] left = new int[m - l + 1];
    	for (int i = 0; i < left.length; i++) 
    		left[i] = a[l + i];
    	int i = l, j1 = 0, j2 = m + 1;
    	while (j1 < left.length && j2 <= h) 
    		a[i++] = (left[j1] < a[j2])? left[j1++] : a[j2++];
//    	if (j1 < left.length)
   			for (; j1 < left.length; j1++)
   				a[i++] = left[j1];
    }
    
    public static void mergesort(int[] a, int begin, int end) {
    	if (begin >= end) return;
    	// split evenly into two parts
    	int l = begin, h = end;
    	int m = (l + h) / 2;
    	mergesort(a, l, m);
    	mergesort(a, m + 1, h);
    	merge(a, l, m, h);
    }
    
    public static void mergesort(int[] a) {
    	mergesort(a, 0, a.length - 1);
    }

    // non-recursive version of mergesort
    public static void ms(int[] a) {
    	int n = a.length;
    	for (int i = 2; i < n; i *= 2) {
    		for (int j = 0; j < n; j += i) {
    			int h = j + i - 1;
    			if (h >= n) h = n - 1;
    			merge(a, j, j + i / 2 - 1, h);
    		}
    	}
    }
    
    // From Wen Xi (Class 2020).
    public static void wenxi(int[] a) {
    	int i,j,n = a.length;
    	for (i = 2; i < n; i *= 2) {
    		for (j = 0; j < n-i/2+1; j += i) {//change this line
    			int h = j + i - 1;
    			if (h >= n) h = n - 1;
    			merge(a, j, j + i / 2 - 1, h);
    		}
    	}
    	merge(a,0,i/2-1,n-1);//add this line    	
    }

    // From Alison Suyan Xu (Class 2020).
    public static void alison(int[] a) {
        int n = a.length;
        for (int i = 2; i <= 2*n-2; i *= 2) {
        	for (int j = 0; j < n; j += i) {
        		int h = j + i - 1;
        		int l = j + i/2 -1;
        		if (h >= n) h = n - 1;
        		if (l >= n) l = n - 1;
        		//System.out.println(n+" "+i+" "+j+" "+l+" "+h);
        		merge(a, j, l, h);
        	}
        }
    }
    
    // This algorithm is not good because it takes too much space.
    // But it's easy to write and let you have the basic ideas of quicksort.
    public static void naive(int[] a, int begin, int end) {
    	if(begin>=end) return;
		int i, pivot = a[end];
		int[] b = new int[end - begin + 1];
		for (i = 0; i < b.length; i++)
			b[i] = a[begin + i];
		int l = begin, r = end;
		for (i = 0; i < b.length; i++) {
			if (b[i] <= pivot) a[l++] = b[i];
			else a[r--] = b[i];
		}
		System.out.println(Arrays.toString(a));
		// absolutely wrong: naive(a, begin, l - 1);
		naive(a, begin, l - 2); // also ok r - 1.
		naive(a, r + 1, end);   // also ok r.
    }

    // Second attempt of mine.   
    public static void quicksort(int[] a, int begin, int end) {
		if (begin >= end) return;
		int i, j = begin, pivot = a[end];
		for (i = begin; i <= end; i++)
			if (a[i] <= pivot)
				swap(a, i, j++);
		quicksort(a, begin, j - 2);
		quicksort(a, j, end);
    }
    public static void swap(int [] a, int x, int y) {
    	int temp=a[x];
    	a[x]=a[y];
    	a[y]=temp;
    }

    // FOUR versions of Hoare partition scheme
    // 0. by CHENG Mingrui (Class 2020)
    static void cheng(int[] a, int st, int ed){
        if(st >= ed) return;
        int p = a[ed], i = st - 1, j = ed + 1;
        while(true){
            while(a[++i] < p);
            while(a[--j] > p);
            if(i >= j) break;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        cheng(a, st, i - 1);
        cheng(a, i, ed);
    }
    
    // 1. by JIAO Ying (Class 2019)
    public static void jiao(int[] a, int begin, int end) {
        if(begin >= end) return;
        int i = begin - 1, j = end + 1, pivot = a[end];
        while (i < j) {
            while (a[++i] < pivot) ;
            while (a[--j] > pivot) ;
            if (i < j) swap(a, i, j);
        }
        jiao(a, begin, i - 1);
        jiao(a, i, end);
    }

    // 2. standard one.
    public static void hoare(int[] a, int begin, int end) {
    	if (begin >= end) return;
    	int i = begin - 1, j = end + 1;
    	int pivot = a[end];
    	while(i < j) {
    		while(a[++i] < pivot);
    		while(a[--j] > pivot);
    		if (i < j) swap(a, i, j);
    	}
    	hoare(a, begin, i - 1);
    	hoare(a, i, end);
    }

    // 3. The one written by me in an early life.
    // Might contain bugs.
    public static void notme(int[] a, int low, int high) {
        int i = low;
        int j = high;
        int pivot = a[high];
        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            if (i <= j) swap(a, i++, j--);
        }
        if (low < j) notme(a, low, j);
        if (i < high) notme(a, i, high);
    }
    
    public static void quicksort(int[] a) {
    	// quicksort(a, 0, a.length - 1);
    	naive(a, 0, a.length - 1);
    }

    public static void main(String args[]){
//		int size = 10;
//		int[] a = new int[size];
//		SecureRandom random = new SecureRandom();
//		for (int i = 0; i < size; i++)
//			a[i] = random.nextInt(size);

        int[] d = {};
        Arrays.sort(d);
        int[] a = {3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9};
		System.out.println(Arrays.toString(a));
		quicksort(a);
		System.out.println(Arrays.toString(a));
    }
}