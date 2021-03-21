package WEB;

/**
 * volatile关键字，解决内存可见性问题
 */

import java.util.Scanner;

public class ThreadDemo11 {
    static class Counter{
        //当加上volatile关键字之后，每次都从内存中读取数据
       volatile static int count = 0;

    }    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
        while (Counter.count == 0){

        }
            System.out.println("循环结束");
        });
        t1.start();
        Thread t2 = new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入一个数字");
            Counter.count = scanner.nextInt();
        });
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Counter.count);
    }
}
