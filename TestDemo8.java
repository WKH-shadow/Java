package JavaPractice;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列实现栈：
 * 方法：
 * 1.首先需要两个队列
 * 2.每次将数放入不为空的队列里面
 * 3.当需要出栈的时候，就只需要将这个数的前n个数全部出队列到另一个空的队列里面，然后在将需要出栈的数字从队列里面弹出来
 */

class MyQueueStack{
    //首先需要两个队列，里面就放Integer
   private Queue<Integer> queue1;
   private Queue<Integer> queue2;
   private int UsedSize;
   //提供构造方法

    public MyQueueStack() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
        this.UsedSize = 0;
    }
    /**
     * 如果两个队列都为空，那么规定将数字放入到queue1当中
     * 如果不为空，那么每次将数字放入到不为空的队列里面
     */
    public void push(int x) {
        if(!queue1.isEmpty()){
            queue1.offer(x);
        }else if(!queue2.isEmpty()){
            queue2.offer(x);
        }else {
            queue1.offer(x);
        }
        this.UsedSize++;
    }

    /**
     * pop时查看栈顶元素也就时出栈
     * 出栈：
     * 1.如果栈为空，也就是两个队列都为空，那么就抛出空指针异常
     * 2.如果不为空，那么需要将不为空的队列出到只剩下一个数
     */
    public int pop() {
        if(queue1.isEmpty()&&queue2.isEmpty()){
            throw new RuntimeException("空指针异常");
        }
        int e = 0;
        if(!queue1.isEmpty()){
            for (int i = 0; i < this.UsedSize-1; i++) {
             queue2.offer(queue1.poll());
            }
            e = queue1.poll();
        }else {
            for (int i = 0; i < this.UsedSize-1; i++) {
                queue1.offer(queue2.poll());
            }
            e = queue2.poll();
        }this.UsedSize--;
        return e;
    }


    /** Get the top element.
     * 查看栈顶元素但是不删除
     * 方法：
     * 1.如果栈为空，也就是两个队列都为空，那么就抛出空指针异常
     * 2.如果不为空，那么需要将不为空的队列所有的元素出到为空的队列里面，但是每一次出队列的
     * 数字都需要被临时记录下来，也就是定义一个临时的变量，把每一次出出来的值放在里面，知道出出来的数字时最后一个，
     * 然后返回出来该临时变量就是我们所需要查看的值
     * @return
     */
    public int top() {
        if (empty()) {
            throw new RuntimeException("空指针异常");
        }
        int e = 0;
        if (!queue1.isEmpty()) {
            for (int i = 0; i < this.UsedSize; i++) {
                e = queue1.poll();
                queue2.offer(e);
            }
        } else {
            for (int i = 0; i < this.UsedSize; i++) {
                e = queue2.poll();
                queue1.offer(e);
            }
        }
        return e;
    }
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return this.UsedSize == 0;
    }
}

public class TestDemo8 {
    public static void main(String[] args) {
        MyQueueStack myQueueStack = new MyQueueStack();
        myQueueStack.push(1);
        myQueueStack.push(2);
        myQueueStack.push(3);
        myQueueStack.push(4);
        System.out.println(myQueueStack.top());
    }

}
