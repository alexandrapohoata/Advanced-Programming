public class ThreadTest implements Runnable {
    private static final Object obj = new Object();
    private final int num;

    ThreadTest(int n) {
        num = n;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println("Entering thread " + num);
                this.wait();
                System.out.println("Done Thread " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t;
        for (int i = 0; i < 5; i++) {
            t = new Thread(new ThreadTest(i));
            t.start();
        }
        synchronized (obj) {
            obj.notify();
        }
    }
}
