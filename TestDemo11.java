package JavaPractice;

/**
 * 设计一个循环队列
 */
class  MyCircularQueue{
    private  int[] elem;
    private  int front;//指向对头
    private  int rear;//指向队尾,并且我们把rear指向的下标下可以继续存放数据
    public MyCircularQueue(int x){
        this.elem = new int[x];
        this.front = 0;
        this.rear = 0;
    }

    // 向循环队列插入一个元素。如果成功插入则返回真。

    public boolean enQueue(int value) {
        //首先检查队列是否未满，如果满了，就结束，如果不满，那么直接存放在rear下标下，而且rear向前走一步
        if (isFull()){
            return false;
        }else {
            this.elem[this.rear] = value;//每次的数据都存放在rear下面
            this.rear = (this.rear+1)%this.elem.length;//此时rear每次指向下一个下标
        }
        return true;
    }

    //从循环队列中删除一个元素。如果成功删除则返回真。从对头删除一个元素
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }else {
            this.front = (this.front+1)%this.elem.length;
        }
        return true;
    }
//从队首获取元素。如果队列为空，返回 -1
    public int Front() {
        int tmp = 0;
        if(isEmpty()){
            return -1;
        }else {
            tmp = this.elem[this.front];
        }
        return tmp;
    }
//获取队尾元素。如果队列为空，返回 -1
    public int Rear() {
        if(isEmpty()){
            return -1;
        }else {
            /**
             * 在这里一定要注意一个点，获取队尾元素的时候不能只是简单的rear-1下标，因为我们已经定义rear是
             * 可以存放数据的下标，如果只是单纯的rear-1，那么如果rear指向的是0号下标的时候，rear-1就不成立了
             * 因为数组不存在有负数下标，所以此可我们需要进行判断rear是否指向的是0号下标
             */

            //这个表达式的意义是，看this.rear-1==0是否成立，如果成立那么就直接返回数组长度-1，也就是当rear指向0号下标的时候，
            // 我们只需要数组长度-1就是rear的前一个下标，如果不等于0，那么就返回rear-1，也就是rear的前一个下标
            int index = (this.rear-1 == 0) ? this.elem.length-1: this.rear-1;
            return this.elem[index];
        }
    }
// 检查循环队列是否为空。
    public boolean isEmpty() {
        if(this.rear == this.front){
            return true;
        }
        return false;
    }
//检查循环队列是否已满。
    public boolean isFull() {
        if(((this.rear+1)%this.elem.length == this.front)){
            return true;
        }
        return false;
    }

}
public class TestDemo11 {
    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(5);
        myCircularQueue.enQueue(5);
        myCircularQueue.enQueue(15);
        myCircularQueue.enQueue(20);
        myCircularQueue.enQueue(3);
        myCircularQueue.enQueue(16);
        System.out.println(myCircularQueue.Front());
        System.out.println(myCircularQueue.Rear());

    }
}
