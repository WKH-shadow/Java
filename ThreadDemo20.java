package WEB;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个可缓存的线程池，若线程数超过处理所需，
 * 缓存一段时间后会回收，若线程数不够，则新建一个线程
 */
public class ThreadDemo20 {
    public static void main(String[] args) {
       ExecutorService threadpool =  Executors.newCachedThreadPool();
       //这里要注意，你有几个任务他就会创建几个线程来执行任务，即便你的电脑是八核，但是还是会创建出5个线程
        // 所以这也是使用这种线程池的风险
        for (int i = 0; i < 5; i++) {
            threadpool.submit(() -> {
                System.out.println("线程名称："+ Thread.currentThread().getName());
            });
        }
    }
}
