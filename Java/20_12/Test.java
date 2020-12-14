import java.util.ArrayList;
import java.util.Arrays;

class A {

}

class B extends A {

}

class C extends B {

}

public class Test {
    static double[][] myDoubles;

    public static void m(List<? super B> arg) {
    }

    public static void main(String[] args) {
        String s = "abc";
        s.replaceAll("b", "d");
        System.out.println( s.equals("adc"));
    }
}
