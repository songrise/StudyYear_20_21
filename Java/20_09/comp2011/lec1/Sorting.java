package comp2011.lec1;

import java.security.SecureRandom;
import java.util.Arrays;

public class Sorting {
	public static void selectionSort(int[] a) {
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
    public static void insertionSort(int[] a) {
        int i, j, key, n = a.length;
        for (i = 1; i < n; i++) {
            key = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (a[j] <= key) break;     
                a[j + 1] = a[j];
            }
            a[j + 1] = key;
        }
    }
    public static void bubbleSort(int[] a) {
        int n = a.length;        
        int i, j, temp;
        boolean flag = true;
        for (i = 1; flag && (i < n); i++) {
            flag = false;
            for (j = 0; j < n - i; j++)
                if (a[j+1] < a[j]) {
                    temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                    flag = true;
                }
        }
        // uncomment the following line to see how many major iterations are run.
        // System.out.println((i-1) + " iterations are executed." );
    }

    public static void bubbleWithoutFlag(int[] a) {
        int n = a.length;        
        int i, j, temp;
        for (i = 1; i < n; i++) {
            for (j = 0; j < n - i; j++)
                if (a[j+1] < a[j]) {
                    temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                }
        }
    }
    
	public static void main(String[] args) {
		int a[] = {10, 8, -4, 89, 2, 0};
		System.out.println("original array: " + Arrays.toString(a));
		bubbleSort(a);
		System.out.println("after sorted: " + Arrays.toString(a));
		
//        for (int i = 0; i < 18; i++)
//            longTest(2<<i);			
	}

    public static void longTest(int size) {
//        int size = 4000, turns = 10;
        System.out.println("size = " + size);        
        int turns = 10;        
        long startTime = 0, duration = 0;

        int[] a = new int[size];
        int[] b = new int[size];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < size; i++)
            a[i] = random.nextInt(size);

        startTime = System.currentTimeMillis();
        for (int j = 0; j < turns; j++) {
            for (int i = 0; i < size; i++)
                b[i] = a[i];
            selectionSort(b);
        }
        duration = (System.currentTimeMillis() - startTime);
        System.out.println(turns + " runs of selectionSort takes " + (duration / 1000.) + " seconds.");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < turns; j++) {
            for (int i = 0; i < size; i++)
                b[i] = a[i];
            insertionSort(b);
        }
        duration = (System.currentTimeMillis() - startTime);
        System.out.println(turns + " runs of insertionSort takes " + (duration / 1000.) + " seconds.");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < turns; j++) {
            for (int i = 0; i < size; i++)
                b[i] = a[i];
            bubbleSort(b);
        }
        duration = (System.currentTimeMillis() - startTime);
        System.out.println(turns + " runs of bubbleSort takes... " + (duration / 1000.) + " seconds.");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < turns; j++) {
            for (int i = 0; i < size; i++)
                b[i] = a[i];
            Arrays.sort(b);
        }
        duration = (System.currentTimeMillis() - startTime);
        System.out.println(turns + " runs of sort of Java takes... " + (duration / 1000.) + " seconds.");
    }

    /*
     * this is a version that doesn't work! 
     */
    public static void selectionSortNotWorking(int[] a) {
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
}
