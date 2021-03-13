package JavaPractice;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode1{
    int data;
    TreeNode1 left;
    TreeNode1 right;

    public TreeNode1(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "TreeNode1{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
public class TestDemo39 {
     TreeNode1 createNode(){
        TreeNode1 treeNode = new TreeNode1(11);
        TreeNode1 treeNode1 = new TreeNode1(22);
        TreeNode1 treeNode2 = new TreeNode1(33);
        TreeNode1 treeNode3 = new TreeNode1(44);
        TreeNode1 treeNode4 = new TreeNode1(55);
        TreeNode1 treeNode5 = new TreeNode1(66);
        TreeNode1 treeNode6 = new TreeNode1(77);
        TreeNode1 treeNode7 = new TreeNode1(88);
        TreeNode1 treeNode8 = new TreeNode1(99);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        return treeNode;

    }
   public void preOrderTraversal(TreeNode1 root){
        if(root == null){
            return;
        }
       System.out.println(root.data+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
   }
   public void inOrderTraversal(TreeNode1 root){
    if(root == null){
        return;
    }
    inOrderTraversal(root.left);
    System.out.println(root.data+" ");
    inOrderTraversal(root.right);
   }
public void postOrderTraversal(TreeNode1 root){
        if(root == null){
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
    System.out.println(root.data+" ");

}
//求节点个数
int getSize1(TreeNode1 root){
        if(root == null){
            return 0;
        }
        return getSize1(root.left)+getSize1(root.right)+1;
}

    /**
     * 求第K层有多少个叶子节点
     * @param
     */

    public int gerLeavesize(TreeNode1 root,int k){
        if(root == null||k < 0){
            return 0;
        }
        if(k == 1){
            return 1;
        }
        return gerLeavesize(root.left,k-1)+gerLeavesize(root.right,k-1);
    }

    /**
     * 查找value所在的节点，如果没有找到，返回null
     * @param
     */
    public TreeNode1 find(TreeNode1 root,int value){
        if(root == null){
            return null;
        }
        if(root.data == value){
            return root;
        }
        TreeNode1 ret = find(root.left,value);
        if(ret != null){
            return ret;
        }
        ret = find(root.right,value);
        if(ret != null){
            return ret;
        }
        return null;
    }


    /**
     * 判断两棵树是否相同
     * @param
     */

    public boolean isSameTree(TreeNode1 p,TreeNode1 q){
        if(p != null&&q == null||p == null&&q != null){
            return false;
        }
        if(p == null && q == null){
            return true;
        }
        if(p.data != q.data){
            return false;
        }
        return isSameTree(p.left,q.left)&&isSameTree(p.right,   q.right);
    }

    /**
     * 判断t是不是s这颗树的子树
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode1 s,TreeNode1 t){
        //首先判断两棵树是否为空，如果一个为空，则返回false
        if(s==null||t == null){
            return false;
        }
        //下来判断s和t是不是一样的，如果是一样的然后再去看s的左树和t的左树一不一样
        if(isSameTree(s,t)){
            return true;
        }
        //判断t是不是s这棵树的左子树
        if(isSubtree(s.left,t)){
            return true;
        }

        //判断t是不是s这棵树的右子树
        if(isSubtree(s.right,t)){
            return true;
        }
        //如果都不是则返回false
        return false;
    }


    /**
     * 获取一棵树的最大高度（深度）
     */
    public int maxHigh(TreeNode1 root){
        if(root == null){
            return 0;
        }
        int tmp1 = maxHigh(root.left);
        int tmp2 = maxHigh(root.right);
        return tmp1>=tmp2?tmp1+1:tmp2+1;
    }

    /**
     * 判断一棵树是不是平衡二叉树：
     * 也就是左树和右树的最高深度相差为1
     * 方法：就是获取组偶数的最高高度，然后获取右树的最高盖度，做差值，
     * 也就用到了我们求一棵树深度的方法
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode1 root){
        if(root == null) {
        return true;
        }
        //拿到左树的最高高度
        int lefthigh = maxHigh(root.left);
        int righthigh = maxHigh(root.right);
        return    Math.abs(lefthigh - righthigh)  < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     *层序遍历：方法：
     * 1.首先需要依靠一个队列
     * 2.先把头节点放进去，然后当队列不为空的时候弹出元素，并且记录当前弹出的元素
     * 3.判断弹出的元素的左孩子为不为空，不是空，那么入队列
     * 4.判断弹出元素的右孩子为不为空，不为空放入队列
     * @param
     */
   public void levelOrderTraversal(TreeNode1 root){
       if(root == null){
           return;
       }
        Queue<TreeNode1> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                TreeNode1 cur = queue.poll();
                System.out.println(cur.data+" ");
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }

        }

    /**
     * 二叉树的层序遍历进阶版：leetcode102
     * @param
     */

   public List<List<Integer>> levelOrder(TreeNode1 root) {
        //首先需要一个List<List<Integer>>
        List<List<Integer>> list = new ArrayList<>();
        //判断根节点是否为空
        if(root == null){
            //如果为空，直接返回一个空list
            return list;
        }
        //首先我们需要先将节点放入队列中，然后在放入list1当中
        Queue<TreeNode1> queue = new LinkedList<>();
        //首先将根节点放入队列中
        queue.offer(root);
        //当队列不为空的时候弹出对头元素，然后记录下来，
        //然后看他的左节点和右节点为不为空，如果不为空，那么放入队列
        //判断条件就是队列是否为空
        while(!queue.isEmpty()){
            //每层放进去的数量可以获取，然后作为每层结束的判断依据
            int size = queue.size();
            //因为每层都是一个list，所有每层都new一个list1出来，然后将每层的数据放在list1当中
            List<Integer> list1  = new ArrayList<>();
            while(size > 0){
                TreeNode1 cur = queue.poll();//记录出队列的元素
                list1.add(cur.data);//将每层中弹出来的数据放在list1当中
                size--;//每次取完数据要--
                //判断cur的左

                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            //然后每次一层的数据的总的list1放在list当中
            list.add(list1);
        }
        return list;
    }

    /**
     * 判断一颗树是不是完全二叉树
     * 方法：
     * 1.需要一个队列，然后将根节点放进去
     * 2.然后定义一个cur，记录队列每次弹出的元素，然后不管cur的左和右为不为空都放进队列
     * 3.然后直到第一个cur出现为空的时候，只要队列里面剩下的都为空，那么就是一个完全二叉树，相反则不是
     * @param
     */
    public boolean isCompleteTree(TreeNode1 root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode1> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode1 cur = queue.poll();
            if (cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                break;
            }
        }
        while (!queue.isEmpty()) {
            TreeNode1 ret = queue.poll();
            if (ret != null) {
                return false;
            } else {
                queue.poll();
            }

        }
        return true;
    }


    public static void main(String[] args) {
        TestDemo39 testDemo39 = new TestDemo39();
        TreeNode1 node1 = testDemo39.createNode();
        System.out.println(testDemo39.gerLeavesize(node1, 4));
        System.out.println(testDemo39.find(node1, 88));
        testDemo39.levelOrderTraversal(node1);
        List<List<Integer>> lists = testDemo39.levelOrder(node1);
        System.out.println(lists);
    }
}
