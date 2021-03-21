package WEB;

/**
 * notifyAll的用法
 */
public class ThreadDemo13 {
    private static Object object = new Object();
    private static int n = 0;

    private static class myThread1 extends Thread {
        myThread1() {
            super("n--");
        }

        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    if (n == 0) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    n--;
                    System.out.println(getName() + ":" + n);
                    object.notify();
                }
            }
        }
    }

    private static class myThread2 extends Thread {
        myThread2() {
            super("n++");
        }

        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    if (n == 10) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    n++;
                    System.out.println(getName() + ":" + n);
                    object.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new myThread1();
        Thread thread2 = new myThread2();
        thread1.start();
        thread2.start();
    }
}
