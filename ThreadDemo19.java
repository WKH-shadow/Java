package WEB;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个固定大小的线程池,超出的线程会在队列中等待
 */
public class ThreadDemo19 {
    public static void main(String[] args) {
        //创建一个大小为10的线程池
        ExecutorService threadpool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 11; i++) {
            //执行线程池方法一
//            threadpool.submit(()->{
//                System.out.println("线程池的名称"+Thread.currentThread().getName());
//            });


            //执行线程池方法二
//            threadpool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("线程池的名称"+Thread.currentThread().getName());
//                }
//            });

            //执行线程池方法三
            threadpool.execute(()->{
                System.out.println("线程池的名称"+Thread.currentThread().getName());
            });
        }

    }
}
