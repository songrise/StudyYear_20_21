class Phi extends Thread {

    private Object fork1, fork2;

    public Phi(Object fork1, Object fork2) {
        this.fork1 = fork1;
        this.fork2 = fork2;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " run.");
        while (true) {
            synchronized (fork1) {
                synchronized (fork2) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " eating.");
                        sleep(10);
                    } catch (Exception e) {
                    }
                }
            }
            try {
                System.out.println(Thread.currentThread().getName() + " thinking.");
                sleep(10);
            } catch (Exception e) {
            }
        }
    }
}

public class DinningPhi {
    public static void main(String[] args) throws Exception {
        Object[] forks = new Object[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Object();
        }
        Phi[] ps = new Phi[5];
        for (int i = 0; i < 5; i++) {
            ps[i] = new Phi(forks[i], forks[(i + 1) % 5]);
            ps[i].start();
            // ps[i].join();
        }
    }
}