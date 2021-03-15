package WEB;

/**
 * 未进入多线程之前，执行程序只能串行式执行，先引入A，完了之后才能执行B，完了之后才能执行C……
 * 通过多线程，我们可以并发式的执行A B C
 * 但是多个线程的执行顺序是操作系统底部的调度器实现的
 */
public class ThreadDemo1 {
    static class myThread extends Thread{
        @Override
        public void run() {
            //线程的入口方法，当前程启动的时候，就会执行到run方法中的逻辑
            //这个方法不需要手动调用，由JVM来自动执行的
            while (true){
                System.out.println("我是新线程");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //其实main方法也是一个线程，当我们要查看的时候，加一个while循环就可
    public static void main(String[] args) throws InterruptedException {
        //向上转型
        Thread thread = new myThread();
        //调用start方法，就会在操作系统中创建一个线程
        //内核中就有了一个PCB对象，被加入到双向链表中
        //当线程创建完毕之后，run方法就会被自动调用
        //当run方法执行完毕，线程就会被销毁,所有当我们使用jconsole查看的时候加一个循环，让他一直运行就行
        thread.start();
        while (true){
            System.out.println("我是主线程");
            Thread.sleep(1000);
        }
    }
}
