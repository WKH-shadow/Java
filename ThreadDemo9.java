package WEB;

/**
 * 使用synchronized锁
 * synchronized的常见用法：
 * - 1.修饰静态方法
 * - 2.修饰普通方法
 * - 3.修饰代码块
 */
public  class ThreadDemo9 {

    public static  class Counter{
        private static int count = 0;
        public  static void increment(){
            count++;
            //synchronized修饰代码块
//            synchronized (Counter.class){
//                count++;
//            }

        }
    }
    private static long size = 1_0000;
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(()->{
//            for (int i = 0; i < size; i++) {
//                Counter.increment();
//            }
//        });
//        t1.start();
//        Thread  t2= new Thread(()->{
//            for (int i = 0; i < size; i++) {
//                Counter.increment();
//            }
//        });
//        t2.start();
//        //这里一定要加上join，线程t1和线程t2执行完毕
//        t1.join();
//        t2.join();
//        System.out.println(Counter.count);
   }
}
