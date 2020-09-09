public class ArrInitTest {
    public static void main(String[] args) {
        {

            var arr = new int[10];
            // values are by default 0
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr at index %d has value %d\n", i, arr[i]);
            }
        }
        {

            var arr = new boolean[10];
            // booleans are by default false
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr at index %d has value %s\n", i, arr[i]);
            }
        }

        {
            var arr = new String[10];
            // objects are by default null
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr at index %d has value %s\n", i, arr[i]);
            }

        }
    }
}
