package JavaPractice;


class Node{
    char data;
    Node lift;
    Node right;

    public Node(char data) {
        this.data = data;
        this.lift = null;
        this.right = null;
    }

}
public class TestDemo38 {

     Node createNode2(){
        Node node1  = new Node('A');
        Node node2 = new Node('B');
        Node node3 = new Node('C');
        Node node4 = new Node('D');
        Node node5 = new Node('E');
        Node node6 = new Node('F');
        Node node7 = new Node('G');
        Node node8 = new Node('H');
        node1.lift = node2;
        node1.right= node3;
        node2.lift = node4;
        node2.right=node5;
        node3.lift = node6;
        node3.right= node7;
        node4.lift = node8;
        return node1;

    }
    //前序遍历
     void preOrderTraversal(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data+" ");
        preOrderTraversal(root.lift);
        preOrderTraversal(root.right);

    }
    //中序遍历
    public void inOrderTraversal(Node root){
        if(root == null){
            return;
        }
        preOrderTraversal(root.lift);
        System.out.print(root.data+" ");
        preOrderTraversal(root.right);

    }
    public void postOrderTreversal(Node root){
        if(root == null){
            return;
        }
        preOrderTraversal(root.lift);
        preOrderTraversal(root.right);
        System.out.print(root.data+" ");
        
    }
    //求节点个数
    //方法一：递归法：总的节点数 = 左边的节点个数+右边的节点个数
    public int getSize(Node root){
        int size = 0;
        if(root == null){
            return 0;
        }
        return getSize(root.lift)+getSize(root.right)+1;

    }
    //求节点个数方法二：非递归，每次遇见不为空的时候size++，比较简单，不推荐使用
//    static int size = 0;
//    public int getsize1(Node root){
//        if(root == null){
//            return 0;
//        }
//        size++;
//        getSize(root.lift);
//        getSize(root.right);
//        return size;
//    }


    /**
     * 求叶子节点个数
     */
    //求叶子节点个数,递归法
     int getLeafSize(Node root){
        if(root == null){
            return 0;
        }
        if(root.lift == null && root.lift == null){
            return 1;
        }else {
          return   getLeafSize(root.right)+getLeafSize(root.right);
        }
    }

    //求叶子节点非递归法，遍历的思路
    static int getsize = 0;
     void getGetsize1(Node root){
        if(root == null){
            return;
        }
        if(root.lift == null && root.right == null){
            getsize++;
        }else {
            getGetsize1(root.lift);
            getGetsize1(root.right);
        }

    }

    /**
     * 求第K层有多少个节点，递归：左树的叶子节点+右树的叶子节点
     *
     */
     int getKLevelSize(Node root,int k){
        if (root == null) {
            return 0;
        }
      if(k == 1){
          return  1;
      }else {
          return getKLevelSize(root.lift,k-1)+getKLevelSize(root.right,k-1);
      }
    }

    //求二叉树的深度（高度）：递归法  左树的高度+1/右树的高度+1,谁的高度最高，那谁就是二叉树的深度
     int getHigh(Node root){
        if(root == null){
            return 0;
        }else {
            int leftHigh = getHigh(root.lift);
            int rightHigh = getHigh(root.right);
            return leftHigh > rightHigh ? leftHigh+1 : rightHigh+1;
        }

    }


    /**
     * 给定一个二叉树，查找是否存在val，
     * @param root
     * @param val
     * @return
     */
     Node find(Node root,int val){
        if(root == null){
            return null;
        }
        if(root.data == val){
            return root;
        }else {
            Node ret1 = find(root.lift,val);
            if(ret1 != null){
                return ret1;
            }
            Node ret2 = find(root.right,val);
            if(ret2 != null){
                return ret2;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        TestDemo38  testDemo38 = new TestDemo38();
        Node node = testDemo38.createNode2();
        testDemo38.preOrderTraversal(node);
        testDemo38.getGetsize1(node);
        testDemo38.inOrderTraversal(node);
        testDemo38.postOrderTreversal(node);
        System.out.println(TestDemo38.getsize);
        System.out.println(testDemo38.getKLevelSize(node, 4));
    }
}
