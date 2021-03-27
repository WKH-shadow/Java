package Thread;

import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;

//描述类(Timer)
class Timer implements Comparable<Timer> {
    //里面有两个属性，时间+任务
    long time;
    Runnable runnable;//任务就是Runnable类型的，因为它里面有自带run方法，更容易执行

    //赋初值
    public Timer(Runnable runnable, long after) {
        this.runnable = runnable;
        //这里的时间，我们就定义成多长时间之后去执行任务,也就是延迟时间 = 当前时间+要延迟的时间
        this.time = System.currentTimeMillis() + after;
    }

    //提供一个执行方法
    public void run() {
        runnable.run();
    }

    //优先级的排序方法
    @Override
    public int compareTo(Timer o) {
        return (int) (this.time - o.time);//正序.正序的好处：把最紧急的任务排在最前面
        //return (int)(o.time-this.time);//倒叙
    }
}

    //组织类(Worker)
    class Worker {
        //1.创建一个优先级队列,但是不是所有的任务都能够放进这个队列，所有我们就指定只能够存放我们Timer类里面的任务
        //2.既然是一个优先级队列，那么肯定有它的优先级，也就是重写Comparable里面的compareTo()方法
        PriorityBlockingQueue<Timer> priorityBlockingQueue = new PriorityBlockingQueue<>();

        //创建一个锁
        Object lock = new Object();

        //1.任务的扫描和执行
        //如何扫描，也就是当初始化Worker的时候就创建一个线程一直去扫描任务
        public Worker() {
            Thread thread = new Thread(() -> {
                while (true) {
                    //1.扫描任务，首先得要有一个容器去放任务，那就是得有优先级阻塞队列
                    try {
                        //2.获取到队首的任务，而且只要扫描到第一个任务就可以得到整个队列的(任务)检查
                        Timer timer = priorityBlockingQueue.take();
                        //当拿到任务之后就判断拿到任务的时间是否小于当前时间，如果小于，那么说明这个任务达到了当前时间，可以执行
                        if (timer.time <= System.currentTimeMillis()) {
                            //然后调用run方法执行该任务
                            timer.run();
                        } else {
                            //假如拿到的是不具备执行的任务 那就将这个任务再放进阻塞队列
                            priorityBlockingQueue.put(timer);
                            System.out.println("不具备执行条件");
                            //存在问题：如果它不具备执行条件，但是他还是会一直扫描
                            //解决方法：如果他不具备执行条件，那么就让它等着，知道被唤醒
                            synchronized (lock){
                                //那么等待多久呢？就是等待时间= 任务的时间-当前时间
                                lock.wait(timer.time - System.currentTimeMillis());
                            }

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }


        //首先必须有任务，所以要写一个添加任务的方法，里面要传入任务和执行时间
        public void schedule(Runnable runnable, long after) {
            priorityBlockingQueue.put(new Timer(runnable, after));
            synchronized (lock){
                lock.notify();
            }
        }
    }

    public class ThreadDemo32 {
        public static void main(String[] args) {
            //创建定时器
            Worker worker = new Worker();
            //创建一个任务
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //如果要执行一个循环的延迟任务
                    worker.schedule(this,3*1000);
                    System.out.println("执行任务的时间:"+new Date());
                }
            };
            //查看添加任务的时间
            System.out.println("添加任务的时间:"+new Date());
            //添加一个任务，然后间隔3秒
            worker.schedule(runnable,3*1000);
        }
    }

