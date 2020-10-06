package Exception;

import java.io.IOException;

class MyException extends Exception {
    MyException() {
    };

    MyException(String info) {
        super(info);
    }
}

// use singleton pattern.

class ThrowAnException {
    private static ThrowAnException ins;

    private ThrowAnException() {
    }

    public static ThrowAnException getInstance() {
        if (ins == null) {
            ins = new ThrowAnException();
        }
        return ins;
    }

    /**
     * trigger an IOException
     * 
     * @Throws IOException
     */
    public void bugTrigger() throws IOException {
        System.out.println(this.getClass() + "triggering an exception!");
        throw new IOException("Exception triggered in the " + this.getClass());
    }
}

public class Throw {

    public static void main(String[] args) throws MyException {
        boolean trigger = true;
        // MyException e = new MyException("[Testing my exception]");

        if (trigger) {
            try {
                ThrowAnException i = ThrowAnException.getInstance();
                i.bugTrigger();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("After the catch clause...");

    }
}
