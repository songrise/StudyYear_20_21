package p;

public class B extends A {
    public void f(A a) {
        w = 1;
        x = 1;
        y = 1;
        // z = 1;
        a.w = 1;
        a.x = 1;
        a.y = 1;
        // a.z = 1;
    }
}
