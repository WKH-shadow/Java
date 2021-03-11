package JavaPractice;

import java.util.Stack;

/**
 * 栈实现队列
 * 方法：
 * 1.当然也是需要两个栈
 * 2.入队，如果两个栈都是空的，就把数字放在S1，每次都放在S1
 * 2.出都从S2出
 * 2.1.如果S2是空的，那么就把S1当中的元素全部导入到S2当中
 * 2.2.如果S2不是空的，直接将S2的栈顶元素进行pop()操作
 */
class MyStackQueue{
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private int UsedSize;
    public MyStackQueue(){
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
        this.UsedSize = 0;
    }
    /** Push element x to the back of queue.
     * 入队，如果两个栈都是空的，就把数字放在S1，每次都放在S1
     */
    public void push(int x) {
        stack1.push(x);
        this.UsedSize++;
    }

    /** Removes the element from in front of queue and returns that element.
     * 1出都从S2出
     * 1.1.如果S2是空的，那么就把S1当中的元素全部导入到S2当中
     * 1.2.如果S2不是空的，直接将S2的栈顶元素进行pop()操作
     */
    public int pop() {
        int e = 0;
        if(stack2.empty()) {
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        if(!stack2.empty()){
            e = stack2.pop();
        }else {
            System.out.println("队列为空");
        }

        return e;
    }

    /** Get the front element.
     * 方法：
     * 1.如果S2为空，s1不为空，那么就将栈S1的数据利用栈的性值全部放在S2，然后在返回S2的栈顶元素
     * 2.如果S2不为空，那么就直接返回S2的栈顶元素
     */
    public int peek() {
        if(stack2.empty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        int e = 0;
        if(!stack2.empty()){
            e = stack2.peek();
        }else {
            System.out.println("队列为空");
        }
        return e;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(stack1.empty()&&stack2.empty()){
            return true;
        }
        return false;
    }
}
public class TestDemo9 {
    public static void main(String[] args) {
        MyStackQueue myStackQueue = new MyStackQueue();
        myStackQueue.push(1);
        myStackQueue.push(2);
        myStackQueue.push(3);
        System.out.println(myStackQueue.peek());
    }
}
