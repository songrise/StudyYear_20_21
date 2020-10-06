package Exception;

import java.io.IOException;

public class NotThrow {
    public static void main(String[] args) throws IOException {
        ThrowAnException t = ThrowAnException.getInstance();
        // Must handle the exception or throw it
        t.bugTrigger();
    }
}
