package Leetcode;

/**
 * leetCode  ：剑指Offer 22 链表中倒数第K个节点
 * 如：给定一个链表: 1->2->3->4->5, 和 k = 2.
 *  返回链表 4->5.
 */
class  Node{
    int val;
    Node next;
    public Node(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return val+" "+next;
    }
}
public class TestDemo14 {
    /**
     * 创建一个单链表
     * @return
     */
    public  Node create(){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5= new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    /**
     *实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     * 方法
     * 1.定义两个节点slow和fast
     * 2.然后让slow和fast的下标相差k-1，也就是先找打fast的下标
     * 3.然后让两个同时走
     * 4.如果fast的下一个等于null的时候，那么此时slow的下标
     */
    public Node getKthFromEnd(Node head, int k){
        //首先判断临界条件head是否为空，如果为空直接返回null
        if(head == null){
            return head;
        }
        //如果传进来的k值时小于0的，那么就肯定找不到
        if(k < 0){
            return null;
        }
        //定义一个fast等于头节点，然后先找到fast的位置，当循环结束时，也就是fast的位置
        Node fast = head;
        for (int i = 0; i < k-1; i++) {
            fast = fast.next;
        }
        //上面已经找到了fast的位置，此时定义一个slow，让其指向头节点
        //当fast的下一个不为空的时候，让fast和slow都向前走，如果fast的下一个为空，
        // 此时返回slow
        Node slow = head;
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        TestDemo14 testDemo14 = new TestDemo14();
       Node node = testDemo14.create();
        Node newHead = testDemo14.getKthFromEnd(node,2);
        System.out.println(newHead);
    }
}
