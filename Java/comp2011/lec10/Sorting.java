package comp2011.lec10;

import java.util.*;
 
/**
 * 
 * @author yixin cao (November 1, 2020)
 *
 * The in-place version of heapsort
 * 
 */
public class Sorting {

    private static void swap(int [] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    // the crisp version.
    private static void down(int[] a, int p, int size) {
        if (p * 2 + 2 > size) return;
        int larger = p * 2 + 1;
        if (larger + 1 < size && a[larger] < a[larger+1]) 
            larger++;
        if (a[p] >= a[larger]) return;
        swap(a, p, larger);
        down(a, larger, size);
    }
    
    private static void up(int[] a, int c) {
        if (c == 0) return;
        int p = (c - 1) / 2;
        if (a[c] <= a[p]) return;
        swap(a, c, p);
        up(a, p);
    }
    
    // iterative version
    private static void iUp(int[] a, int c) {
    }    
    
    // iterative version
    private static void iDown(int[] a, int p) {
    }    

    public static void heapsort(int[] a) {
        for (int i = 1; i < a.length; i++)
            up(a, i);
        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);  // a simler way of deleteMax.
            down(a, 0, i);
            // Uncomment the following line to see the progress.
            // System.out.println(Arrays.toString(a));
        } 
    }

    public static void main(String args[]){
//		int size = 10;
//		int[] a = new int[size];
//		SecureRandom random = new SecureRandom();
//		for (int i = 0; i < size; i++)
//			a[i] = random.nextInt(size);

        int[] a = {3, 2, 6, 13, 8, 4, 10, 7, 14, 11, 12, 5, 9};
		System.out.println(Arrays.toString(a));
		heapsort(a);
		System.out.println(Arrays.toString(a));
    }
}