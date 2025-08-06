import java.util.concurrent.locks.ReentrantLock;

public class Join {
    static class t {
        int noOfUser = 0;
        ReentrantLock lock=new ReentrantLock();

        // public synchronized void cnt(int n) {
        public void cnt(int n) {
            // synchronized (this) {
            lock.lock();  // acquire the lock before entering critical section
            try {
                for (int i = 0; i < n; i++) {
                    noOfUser++;
                }
            } finally {
                lock.unlock(); // release the lock in finally to ensure it's always unlocked
            }
            // }
        }
    }

    public static void main(String[] args) {

        t obj = new t();

        Thread t1 = new Thread(() -> {
            obj.cnt(1000000);
        });
        Thread t2 = new Thread(() -> {
            obj.cnt(1000000);
        });

        t1.start();
        t2.start();

        

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.print(obj.noOfUser);

    }
}
