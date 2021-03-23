package WEB;

import java.util.concurrent.TimeUnit;

//使用循环数组实现生产者消费者模型
public class ThreadDemo18 {
    //Java里面的数组需要初始化容量，因为目前不知道，使用根据用户输入来决定
    int[] items = null;
    int first;//队首元素下标——出列的时候会用到
    int last;//队尾元素下标——入队的时候会用到
    int size;//实际存储容量

    //初始化，传一个容量参数
    public ThreadDemo18(int cap){
        items = new int[cap];
        first = 0;
        last  = 0;
        size = 0;
    }

    //创建一把锁
    Object lock = new Object();
    //实现入队方法put（）操作
    public synchronized void put(int val) throws InterruptedException {
        //需要给lock加一把锁
        synchronized (lock) {
            //首先判断队列是否已经满了，满了就说明生产者生产的太快了，那么此时就需要阻塞等待
            if (size == items.length) {
                System.out.println("生产者阻塞了"+Thread.currentThread().getName());
                //阻塞等待，首先需要有一把琐，然后使用wait方法
                //之后用notify唤醒，也就是释放锁，但是前提是要给lock加一把锁
                //然后等消费者消费了一条数据之后唤醒，也就是再pop操作下唤醒
                lock.wait();
            }
            //每次存放再last的位置，并且last++,size++
            items[last++] = val;
            size++;
            //不能够一直存放数据，要判断队列是否已经被放满
            if (last >= items.length) {
                //队列放满,此时然last的位置置为0；
                last = 0;
            }
            //此时说明已经添加数据，入队列了，所以此时唤醒消费操作
            lock.notify();
        }
    }

    //实现出队操作
    public int pop() throws InterruptedException {
        synchronized (lock) {
            int ret = -1;
            //出队之前，判断队列是否为空
            if (size <= 0) {
                System.out.println("消费者阻塞了"+Thread.currentThread().getName());
                //此时消费者消费的太快，需要阻塞等待
                //然后等待生产者生产了一条数据之后再唤醒消费动作，
                lock.wait();
            }
            //此时队列不为空，然后出队顶元素，first++，size--
            ret = items[first++];
            size--;
            //判断如果first的下标已经是最后一个了，需要将first置为0
            if (first > items.length) {
                first = 0;
            }
            //走到这一步此时表示已经出队列了，说明就应该唤醒生产者继续生产数据了
            lock.notify();
            return ret;
        }
    }
    public static void main(String[] args) {
        ThreadDemo18 threadDemo18 = new ThreadDemo18(100);
        //创造第一个生产者
        Thread  t1 = new Thread(()->{
            while (true){
                try {
                    //生产数据
                    threadDemo18.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        //创建一个消费者
        Thread t2 = new Thread(()->{
            while (true){
                try {
                    //消费数据
                    System.out.println("消费数据："+threadDemo18.pop());
                    //此时等待1秒是为了让生产者生产的快一点，消费者消费的慢一点
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

    }
}
