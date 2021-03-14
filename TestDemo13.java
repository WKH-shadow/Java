package Leetcode;

/**
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 */
class  ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val = val;
    }
}
public class TestDemo13 {
    /**
     * 创建一个单链表
     * @return
     */
    public  ListNode createListNode(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5= new ListNode(5);
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
     * 4.如果fast的下一个等于null的时候，那么此时slow的下标指向就是倒数第k的节点的位置
     */
    public int kthToLast(ListNode head, int k){
        //首先判断临界条件head是否为空，如果为空直接返回0
        if(head == null){
            return 0;
        }
        //如果传进来的k值时小于0的，那么就肯定找不到
        if(k < 0){
            return 0;
        }
        //定义一个fastNode等于头节点，然后先找到fastNode的位置，当循环结束时，也就是fastNNode的位置
        ListNode fastNode = head;
        for (int i = 0; i < k-1; i++) {
            fastNode = fastNode.next;
        }
        //上面已经找到了fastNode的位置，此时定义一个slowNode，让其指向头节点
        //当fastNode的下一个不为空的时候，让fastNode和slowNode都向前走，如果fastNode的下一个为空，
        // 此时返回slowNode所对应下标的值就可
        ListNode slowNode = head;
        while (fastNode.next != null){
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        return slowNode.val;
    }
    public static void main(String[] args) {
        TestDemo13 testDemo13 = new TestDemo13();
        ListNode node = testDemo13.createListNode();
        int val = testDemo13.kthToLast(node,2);
        System.out.println(val);
    }
}
