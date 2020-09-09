import java.util.*;

public class InputTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // get first input
        System.out.print("What is your name? ");
        String name = in.nextLine();

        System.out.print("How old are you? ");
        int age = in.nextInt();
        System.out.println("Hello, " + age + " yrs old " + name);
        System.out.printf("Test printf %.2f", (float) age);
    }
}