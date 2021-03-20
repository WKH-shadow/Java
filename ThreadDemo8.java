package WEB;

/**
 * 如何让t1执行的次数少一点，让t2的次数执行的多一些
 * 方法：调用 Thread.yield（）其原理是当前的线程让出cpu执行权，让其他线程执行
 */
public class ThreadDemo8 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
        while (true){
            //调用Thread.yield()方法，让其让出cpu的执行全，让其他线程执行，因为多线程他是抢占式执行的，
            // 这种方法是人为控制
            Thread.yield();
            Thread.currentThread().setName("线程一");
            System.out.println("我是新线程："+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        });
        t1.start();
        Thread t2 = new Thread(()->{

            while (true){
                System.out.println("我是新线程："+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"线程二");
        t2.start();
        t1.join();
        t2.join();
    }
}
