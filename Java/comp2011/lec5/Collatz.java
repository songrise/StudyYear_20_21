package comp2011.lec5;

/*
 * can you guess what this class does?
 */
public class Collatz {
    public static int odd(int n) {
        if (n <= 1) {
            System.out.print("1\n");
            return 1;
        }
        System.out.print(n + " -> ");
        return even(n * 3 + 1);
    }

    public static int even(int n) {
        System.out.print(n + " -> ");
        while (n % 2 == 0)
            n /= 2;
        return odd(n);
    }

    public static void main(String[] args) {
        for (int i = 10; i < 40; i++)
            even(i * 2);

        // try any even number here.
        even(10000000);
    }
}
