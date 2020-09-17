public class AutoBoxing {
    public static void main(String[] args) {
        int a = 126;
        int b = 126;
        // if a, b between -128 and 127, the comparison will succeed
        Integer c = Integer.valueOf(a);
        Integer d = Integer.valueOf(b);
        if (c == d) {
            System.out.println("c == d");
        }
    }
}
