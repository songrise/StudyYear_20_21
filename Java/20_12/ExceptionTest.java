class Checked extends Exception {
    Checked() {
        super();
    }
}

class Unchecked extends RuntimeException {
    Unchecked() {
        super();
    }
}

public class ExceptionTest {

    void fun1() {
        throw new Unchecked();
    }

    void fun2() throws Checked {
        throw new Checked();
    }

}
