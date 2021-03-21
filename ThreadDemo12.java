package WEB;
/**
 * notify的用法
 */

import java.util.Scanner;

public class ThreadDemo12 {
    private static Object object = new Object();
    public static class MyThread extends  Thread{
        @Override
        public void run() {
            synchronized (object){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 10; i < 20; i++) {
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args){
        Thread thread = new MyThread();
        thread.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("我不输入，Thread线程就不会运行");
        scanner.nextInt();
        synchronized (object){
            object.notify();
        }
    }
}
