package comp2011.myImpl.sorting.basic;

import java.security.SecureRandom;
import java.util.Arrays;

public class IterativeVer {
    public static void main(String[] args) {
        // int a[] = { 10, 8, -4, 89, 2, 0 };
        // System.out.println("original array: " + Arrays.toString(a));
        // bubbleSort(a);
        // System.out.println("after sorted: " + Arrays.toString(a));
        // for (int i = 0; i < 18; i++)
        // longTest(2<<i);
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
            bubbleSort(a);
            if (!Arrays.equals(a, cpy)) {
                System.out.println(Arrays.toString(cpy));
                System.out.println(Arrays.toString(a));
                System.out.println();
            }
        }
        System.out.println("finished.");
    }

    public static void selectionSort(int[] a) {
        int n = a.length;
        int min;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[min] > a[j])
                    min = j;
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
                if (a[j] <= key)
                    break;
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
                if (a[j + 1] < a[j]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
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
                if (a[j + 1] < a[j]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
        }
    }
}
