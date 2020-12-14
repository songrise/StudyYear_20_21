class Num {
    int val;

    Num(int v) {
        val = v;
    }

    void inc() {
        val++;
    }

}

class MyT implements Runnable {
    Num num;
    
    MyT(Num n) {
        this.num = n;
    }

    public void run() {
        System.out.println("Thread" + this + " with val == " + num.val);
    }

}

public class TestThread {
    public static void main(String[] args) throws Exception {
        // Num number = new Num(4);
        // Thread t = new Thread(new MyT(number));
        // Thread t2 = new Thread(new MyT(number));
        // t.start();
        // // t.interrupt();
        // t.join();
        // number.inc();
        // t2.start();

        
    }
}
