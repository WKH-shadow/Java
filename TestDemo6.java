package JavaPractice;

import org.omg.CORBA.INTERNAL;

import java.util.Arrays;

/**
 * 简单实现一个栈MyStack
 */

//栈的底层实际上是一个动态数组
    class MyStack<T>{
    public T[] elem;
    public int UsedSize;

    public MyStack() {
        this.elem = (T[])new Object[10];
        this.UsedSize = 0;
    }

    //实现栈中一些简单的push,pop，peek，empty，isFull操作
    public void push(T val){
        if(isFull()){
            return;
        }
        this.elem[this.UsedSize] = val;
        this.UsedSize++;

    }
    public T pop(){
        if(empty()){
            throw new RuntimeException();
        }
        T data = this.elem[this.UsedSize-1];
        this.UsedSize--;
        return data;

    }
    public T peek(){
        if(empty()){
            throw new RuntimeException();
        }
        T data = this.elem[this.UsedSize-1];
        return data;
    }

    public boolean empty(){
        if(this.UsedSize == 0){
            return true;
        }
        return false;
    }
    public boolean isFull(){
        if(this.UsedSize == this.elem.length){
            return true;
        }
        return false;
    }

}
public class TestDemo6{
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }

}