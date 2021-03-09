package JavaPractice;

class   QueueNode{
    int data;
     QueueNode next;

    public QueueNode(int data) {
        this.data = data;
        this.next = null;
    }

}

//Queue的底层实际上是一个链表，这里拿单链表举例
public class MyQueue {
    public  QueueNode front;//指向对头
    public QueueNode rear;//指向队尾

    //插入元素，选择尾插法，因为尾插法的之后的出队列和入队列的时间复杂度都为O（1）
    public void offer(int data){
        //每次都需要先有一个节点，所以
        QueueNode node = new QueueNode(data);
        //首先判断队列是否为空,如果为空，那么头和尾都指向这个新的节点
        if(empty()){
            this.front = this.rear = node;
        }
        //如果不为空，那么采用尾插法，头front不动，rear指向新的节点，然后每次将rear变成尾节点
        this.rear = this.rear.next;
        rear = node;

    }

    /**
     * 检查头节点并且返回头节点，并删除
     * @return
     */
    public int poll(){
        //首先需要判断队列是否为空，如果为空，直接结束
        if(empty()){
            //这里可以直接返回一个return -1，或者抛出空指针异常
            throw new RuntimeException("队列为空");
        }
        //要返回出头节点的数据，必须先保存这个节点的数据，如果一旦直接删除，就没有办法返回该数据
        int dada = this.front.data;
        this.front = this.front.next;
        return dada;
    }

    /**
     *检查头节点，但是不删除
     * @return
     */
    public int peek(){
        //首先需要判断队列是否为空，如果为空，直接结束
        if(empty()){
            //这里可以直接返回一个return -1，或者抛出空指针异常
            throw new RuntimeException("队列为空");
        }
        //在这里直接返回头节点的数据就行，如果不删除，无需提前保存
        return this.front.data;
    }
    public boolean empty(){
        //在这判断它为空，只需要判断他的头为空也就是front为null即可
        if(this.front == null){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.peek());
        System.out.println(myStack.empty());
        System.out.println(myStack.pop());
    }
}
