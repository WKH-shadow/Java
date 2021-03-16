package WEB;

/**
 * 启动线程
 */
public class ThreadDemo5 {
    //定义一个标识符，通过控制标识符来控制线程的终止
   private static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
       Thread thread = new Thread(()->{
               while (!flag) {
                   System.out.println("别打扰我，我正在转账呢");
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println("转账终止");
       });
       thread.start();
       //此时等3s中之后发现有内鬼，然后终止交易
       Thread.sleep(3000);
           System.out.println("有内鬼，终止交易");
           //因为此时有内鬼，然后将标识符设置为true
            flag = true;
    }
}
