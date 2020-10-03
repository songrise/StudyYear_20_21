package comp2011.lec2;

import java.util.Arrays;

public class SimpleAnalysis {

    public static boolean sorted(int[] a) {
        int n = a.length;
        boolean answer = true;
        for (int i = 1; i < n; i++) {
            if (a[i - 1] > a[i])
                answer = false;
        }
        return answer;
    }

    public static boolean sortedTheSmart(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (a[i - 1] > a[i])
                return false;
        }
        return true;
    }

    public static boolean digit(int a, int d) {
        // if (d < 0 || d > 9) return false;
        boolean answer = false;
        while (a > 0) {
            if (a % 10 == d)
                answer = true;
            a = a / 10;
        }
        return answer;
    }

    public static boolean equal(int[] a, int[] b) {
        int n1 = a.length;
        int n2 = b.length;
        if (n1 != n2)
            return false;
        boolean answer = true;
        for (int i = 0; i < n1; i++) {
            if (a[i] != b[i])
                answer = false;
        }
        return answer;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int f1(int[] a) {
        int n = a.length, sum = 0;
        for (int i = 0; i < n; i++)
            sum += a[i];
        return sum;
    }

    public static int f2(int[] a) {
        int n = a.length, sum = 0;
        for (int i = 0; i < n; i++)
            if (a[i] > 0)
                sum += a[i];
        return sum;
    }

    public static void cocktail(int[] a) {
        int start = 0, end = a.length;
        int steps = 0;
        while (start < end) {
            for (int i = start; i < end - 1; i++)
                if ((++steps > 0) && a[i] > a[i + 1])
                    swap(a, i, i + 1);
            end--;
            for (int i = end - 1; i >= start; i--)
                if ((++steps > 0) && a[i] > a[i + 1])
                    swap(a, i, i + 1);
            start++;
        }
        System.out.println("The number of steps is: " + steps);
    }

    public static int f3(int[] a) {
        int n = a.length, sum = 0;
        for (int i = 0; i < n; i++)
            sum += (a[i] > 0 ? a[i] : 0);
        return sum;
    }

    public static int f4(int[] a) {
        int n = a.length, sum = 0;
        for (int i = 1; i <= n; i *= 2)
            sum += (a[i - 1] > 0 ? a[i - 1] : 0);
        return sum;
    }

    public static int f5(int[] a) {
        int n = a.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            if (sum > 100)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = { 10, 8, -4, 89, 2, 0 };
        System.out.println("The array is " + (sorted(a) ? "" : "not ") + "sorted");
        System.out.println("The array is " + (sortedTheSmart(a) ? "" : "not ") + "sorted");
        System.out.println(f1(a));
        System.out.println(f2(a));
        System.out.println(f3(a));
        System.out.println(f4(a));
        System.out.println(f5(a));
        cocktail(a);
        System.out.println(Arrays.toString(a));
    }
}
