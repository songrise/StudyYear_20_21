package comp2011.lec5;

/**
 * 
 * @author yixin cao (October 1, 2020)
 *
 * A simple solver of the towers of Hanoi.
 * 
 */
public class HanoiTowers {

    public static void move(int n, int from, int to) {
        String[] rods = {"Left", "Center", "Right"};
        if(n <= 1) {
            System.out.println("disk 1: " + rods[from] + " -> " + rods[to]);
            return;
        }
        int via = 3 - from - to;  // a small trick to avoid the use of several if statements. 
        move(n - 1, from, via);
        System.out.println("disk " + n + ": " + rods[from] + " -> " + rods[to]);
        move(n - 1, via, to);
    }

    // To use a recursive method, we frequently need a kickstarter.
    public static void move(int n) {
        move(n, 0, 2);
    }
    
    public static void main(String[] args) {
        move(6); // Can you calculat the number of steps for different n?
    }
}
