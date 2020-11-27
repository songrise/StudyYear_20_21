package comp2011.lec5;

import java.util.Scanner;

/**
 * 
 * @author yixin cao (October 6, 2020)
 *
 *         Examples for recursion.
 * 
 */
public class Recursion {

    public static void mystery1(String s) {
        if (s.length() == 0)
            return;
        System.out.print(s.charAt(0));
        mystery1(s.substring(1));
    }

    public static void mystery2(String s) {
        if (s.length() == 0)
            return;
        mystery2(s.substring(1));
        System.out.print(s.charAt(0));
    }

    public static long sum(int n) {
        if (n <= 1)
            return n;
        return sum(n - 1) + n;
    }

    // this version is not good, becaue
    // the value of factorial quickly goes out of the rang of lang.
    public static long factorial(int n) {
        if (n <= 1)
            return 1;
        return factorial(n - 1) * n;
    }

    // a recursive (and very bad) way to calculate fibonacci numbers.
    public static long rFibonacci(int n) {
        if (n <= 1)
            return n;
        return rFibonacci(n - 2) + rFibonacci(n - 1);
    }

    // in-place
    public static long fibonacci(int n) {
        if (n < 1)
            return 0;
        int a = 0, b = 1;
        for (int i = 1; i < n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // not in-place.
    public static long simpleFibonacci(int n) {
        if (n < 1)
            return 0;
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++)
            f[i] = f[i - 1] + f[i - 2];
        return f[n];
    }

    public static int iterativeGCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (a * b != 0) {
            int smaller = Math.min(a, b);
            a = (a + b) % smaller;
            b = smaller;
        }
        return a + b;
    }

    public static int recursiveGCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a * b == 0)
            return a + b;
        int smaller = Math.min(a, b);
        if ((a + b) % smaller == 0)
            return smaller;
        return recursiveGCD((a + b) % smaller, smaller);
    }

    public static void main(String[] args) {
        mystery1("HongKong");
        System.out.print("\n\n");
        mystery2("HongKong");
        System.out.print("\n\n");

        for (int i = 10; i < 40; i++) {
            System.out.println(i);
            System.out.println(fibonacci(i));
            System.out.println(rFibonacci(i));
        }
        Scanner keyboard = new Scanner(System.in);
        int num1, num2;
        System.out.print("Enter the first integer:");
        num1 = keyboard.nextInt();
        System.out.print("Enter the second integer:");
        num2 = keyboard.nextInt();

        System.out.println("the gcd is " + recursiveGCD(num1, num2));
        System.out.println("the gcd is " + iterativeGCD(num1, num2));
        keyboard.close();
    }
}
