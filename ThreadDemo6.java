package WEB;

public class ThreadDemo6 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            //调用isInterrupted()
        while (!Thread.interrupted()){
            System.out.println("我正在转账呢");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        //当被中断后打印交易终止
            System.out.println("交易终止");
        });
        thread.start();
        Thread.sleep(3000);
        System.out.println("有内鬼，终止交易");
        //让t1终止，调用线程本身的interrupt
        thread.interrupt();
    }
}
