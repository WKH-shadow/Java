package WEB;

public class ThreadDemo2 {
    static long count = 100_0000_0000L;
    /**
     * 当没有使用线程的时候
     */
    //    public static void merge(){
//        long begin = System.currentTimeMillis();
//        int a = 0;
//        for (long i = 0; i < count; i++) {
//            a++;
//
//        }
//
//        int b = 0;
//        for (long i = 0; i < count; i++) {
//            b++;
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("需要花费的时间"+(end-begin));
//    }

    /**
     * 当使用线程的时候
     * @param
     */
    public static void mergc2(){
        long begin = System.currentTimeMillis();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a++;
                }
            }
        };
        thread1.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                int b = 0;
                for (long i = 0; i < count; i++) {
                    b++;
                }
            }
        };
        thread2.start();

        //线程等待逻辑
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("需要花费的时间"+(end-begin));
    }

    public static void main(String[] args) {
        //但没有使用线程的时候，可以看出他的大概运行时间为11ms左右
//        merge();

        //需要花费的时间是8ms左右
        mergc2();

    }
}