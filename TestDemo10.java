package JavaPractice;

import java.util.Stack;

/**
 * 实现一个最小栈
 * 入栈：
 * 1.首先需要两个栈 一个stack 一个minStack
 * 2.对于stack来说，每一次都会存放数据
 * 3.对于minStack来说
 * 3.1如果是第一次存放，那么肯定会将stack中存放的第一个数据存放进去
 * 3.2如果不是一次存放，那么每一次入栈的元素需要和栈顶元素进行比较，如果栈顶元素小，则入栈，如果比栈顶元素哒，那么不入栈
 * 出栈：
 * 1.需要判断stack栈的数据，在minStack当中是否存在，需要和栈顶元素进行比较，如果相等，minstack和stack都需要出栈
 */
//首先需要两个栈，一个stack  一个minStack
    class getMinStack{
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public getMinStack(){
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }
    public void push(int x) {
        //stack里面每一次都会存放数据
        stack.push(x);
        //如果minStack里面为空，那么直接将这个数据存放进去
        if (minStack.empty()) {
            minStack.push(x);
        } else {
            //如果不为空，将这个数据和minStack栈顶元素进行比较，如果x<=minStack.peek(),那么直接放进去
            if (x <= minStack.peek()) {
                minStack.push(x);
            }
        }
    }
    //查看栈顶元素并且删除
    //stack中每次弹出的数据需要和minStack中的栈顶元素比较，因为如果Stack中要弹出的数据和minStack的数据一样
    //那么minStack中栈顶元素也需要弹出，因为他的最小值发生了变化
    public void pop() {
            if(!stack.empty()) {
                int e = stack.pop();
                if (e == minStack.peek()) {
                    e = minStack.pop();
                }
            }
    }
//获取栈顶元素，但是不删除
    public int top() {
        if (stack.isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
public class TestDemo10 {
    public static void main(String[] args) {
        getMinStack getminStack = new getMinStack();
        getminStack.push(3);
        getminStack.push(-12);
        getminStack.push(-15);
        getminStack.push(13);
        getminStack.push(5);
        System.out.println(getminStack.top());
        System.out.println(getminStack.getMin());

    }
}
