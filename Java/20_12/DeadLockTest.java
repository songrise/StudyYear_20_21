
public class DeadLockTest {

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new DeadLock(true), "线程1");
        Thread t2 = new Thread(new DeadLock(false), "线程2");

        t1.start();
        t2.start();
        
    }
}