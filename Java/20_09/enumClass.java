enum Size {
    SMALL, MEDIUM, LARGE
}

public class enumClass {
    public static void main(String[] args) {
        // all enum class is inherited toString()
        System.out.println(Size.LARGE);
        Size[] values = Size.values();
        System.out.println(values);

    }

}


