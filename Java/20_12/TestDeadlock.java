class MyT2 implements Runnable {
    Num num1;
    Num num2;

    MyT2(Num n, Num m) {
        this.num1 = n;
        this.num2 = m;
    }

    public void run() {
        synchronized (num1) {
            synchronized (num2) {
                num2.inc();
                num1.inc();
                System.out.println("Thread " + this);
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) {
                }
            }
        }
    }

}

public class TestDeadlock {
    public static void main(String[] args) throws InterruptedException {
        Num n = new Num(1);
        Num m = new Num(2);
        Thread t1 = new Thread(new MyT2(n, m));
        Thread t2 = new Thread(new MyT2(m, n));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
