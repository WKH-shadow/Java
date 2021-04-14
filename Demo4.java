package Practice;

import java.util.Stack;

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;
    TreeNode1(int x) { val = x; }
}
public class Demo4 {

    //创建二叉树
    public static TreeNode1 createTree(){
        TreeNode1 node1 = new TreeNode1(1);
        TreeNode1 node2 = new TreeNode1(2);
        TreeNode1 node3 = new TreeNode1(3);
        TreeNode1 node4 = new TreeNode1(4);
        TreeNode1 node5 = new TreeNode1(5);
        TreeNode1 node6 = new TreeNode1(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        return node1;
}

      //二叉树的前序遍历递归实现
    public static void preOrderTraver(TreeNode1 root){
        if(root == null){
            return;
        }
        System.out.print(root.val+" ");
        preOrderTraver(root.left);
        preOrderTraver(root.right);
    }
     //二叉树的前序遍历非递归实现
    public static void preOrderTraverlNor(TreeNode1 root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode1> stack = new Stack<>();
        TreeNode1 cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null) {
                stack.push(cur);
                System.out.print(cur.val + " ");
                cur = cur.left;
            }
            TreeNode1 top = stack.pop();
            cur = top.right;
        }
    }
    //中序遍历递归实现
    public static void inOrderTravel(TreeNode1 root){
        if(root == null){
            return;
        }
        inOrderTravel(root.left);
        System.out.print(root.val+" ");
        inOrderTravel(root.right);
    }

    //中序遍历非递归实现
    public static void inOrderTravelNor(TreeNode1 root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode1> stack = new Stack<>();
        TreeNode1 cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;

            }
            TreeNode1 top = stack.pop();
            System.out.print(top.val+" ");
            cur = top.right;
        }
    }

    //后序遍历递归实现
    public static void postOrderTravel(TreeNode1 root){
        if(root == null){
            return;
        }
        postOrderTravel(root.left);
        postOrderTravel(root.right);
        System.out.print(root.val+" ");
    }

    //后序遍历非递归实现
    public static void postOrderTravelNor(TreeNode1 root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode1> stack = new Stack<>();
        TreeNode1 cur = root;
        TreeNode1 pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if(cur.right == null || cur.right == pre){
                System.out.print(cur.val+" ");
                stack.pop();
                pre = cur;
                cur = null;
            }else {
                cur = cur.right;
            }
        }
    }
    public static void main(String[] args) {
        TreeNode1 root = createTree();
        System.out.println("=====前序遍历递归实现=====");
        preOrderTraver(root);
        System.out.println();
        System.out.println("====前序遍历非递归实现====");
        preOrderTraverlNor(root);
        System.out.println();
        System.out.println("====中序遍历递归实现====");
        inOrderTravel(root);
        System.out.println();
        System.out.println("=====中序遍历非递归实现====");
        inOrderTravelNor(root);
        System.out.println();
        System.out.println("====后序遍历递归实现====");
        postOrderTravel(root);
        System.out.println();
        System.out.println("=====后序遍历非递归实现====");
        postOrderTravelNor(root);
    }
}
