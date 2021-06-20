package TestDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制带随机指针的链表
 * 方法：使用HashMap
 */
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class TestDemo9 {
    public Node copyRandomList(Node head) {
        //首先需要一个HashMap来存放原节点，和复制之后所对应的节点
        Map<Node,Node> map = new HashMap<>();
        //首先先遍历一边单链表，然后现将简单的复制所有的节点，至于关系，后面在处理
        //定义一个cur开始遍历节点
        Node cur = head;
        while (cur != null){
            //此时创建一个节点，然后所对应的值就是cur的值
            Node node = new Node(cur.val);
            //然后将新旧节点对应关系放入到map当中
            map.put(cur,node);
            //然后cur接着往后遍历
            cur = cur.next;
        }
        //此时Map里面存放的就是所对应的关系，也就是Map<原节点，新节点>,此时就处理他们之间的关系

        //此时cur在重新指向原节点的头部
        cur = head;
        while (cur != null){
            //首先处理next的关系，那也就是新节点的next就对应原节点的next
            map.get(cur).next = map.get(cur.next);
            //然后random的话就是新的节点的random就对应原先节点的random
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        //走到这里说明关系已经处理完毕，那么就然后新的链表的头结点，而新的链表的头结点也就是map表原节点对应的新节点head
        return map.get(head);
    }
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Node cur1 = node1;
        while (cur1 != null){
            System.out.print(cur1.val+" ");
            cur1 = cur1.next;
        }
        System.out.println();
        System.out.println("====================");
        TestDemo9 testDemo9 = new TestDemo9();
        Node head = testDemo9.copyRandomList(node1);
        Node cur = head;
        while (cur != null){
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
    }
}
