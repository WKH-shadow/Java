package WEB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lcok锁
 *
 */
public class ThreadDemo10 {
    static class Counter {
        private static int count = 0;
        //首先声明一把锁,但是需要将这把锁变成全局锁，只有在JVM启动的时候加载一次
       static Lock lock = new ReentrantLock(false);
        public static void increment() {
            //第一步：执行加锁操作
            lock.lock();
            //为了保证++操作完成之后会释放锁，所以把它放在finnaly里面
            try {
                //第二步执行代码逻辑++操作
                count++;
            }finally {
                //第三步：释放锁
                lock.unlock();
            }

        }
    }
    private static final int size = 1_0000;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < size; i++) {
                Counter.increment();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < size; i++) {
                Counter.increment();
            }
        });
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(Counter.count);
    }

}
