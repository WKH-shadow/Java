package WEB;

public class ThreadDemo7 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
        while (!Thread.interrupted()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread cur = Thread.currentThread();
                System.out.println("线程的状态： "+ cur.isInterrupted());//false
                System.out.println("线程的状态： "+ cur.isInterrupted());//false
                System.out.println("===================");
                //当调用interrupt之后
                Thread.interrupted();
                System.out.println("线程的状态： "+ cur.isInterrupted());//true
                System.out.println("线程的状态： "+ cur.isInterrupted());//true

            }
        }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
