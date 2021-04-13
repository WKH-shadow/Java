package LeetCode;

/**
 * 根据前序遍历和中序遍历构建一颗二叉树
 *
 */
class TreeNode {
      int val;
      treeNode left;
      treeNode right;
      public TreeNode() {}
      TreeNode(int val) {
          this.val = val;
      }
      TreeNode(int val, treeNode left, treeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

    @Override
    public String toString() {
        return
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

public class leetcode105 {
    //定义一个全局变量，如果把它放在方法参数里面，那么他是一个局部变量，如果进行递归的话，它最终还是会回到原来的值
    public int index = 0;

    //参数解释
    //2.因为每次拿到前序遍历的数在中序遍历中找到之后，它的区间是发生改变的，所以需要一个区间
    public treeNode buildTreeChild(int[] preorder, int[] inorder, int begin, int end) {
        //边界判断，如果begin的值大于end的值，那么就说明这个节点的数就构建完成了
        if (begin > end) {
            return null;
        }

        //首先创建跟根节点
        treeNode root = new treeNode(preorder[index]);
        //然后找到root在中序遍历的位置
        int rootIndex = findIndexInorder(inorder, preorder[index], begin, end);
        index++;
        if (rootIndex == -1) {
            //也就是没找到 返回null;
            return null;
        }

        //此时构建root的左树和右树
        root.left = buildTreeChild(preorder, inorder, begin, rootIndex - 1);
        root.right = buildTreeChild(preorder, inorder, rootIndex + 1, end);
        return root;
    }

    //写一个方法：在中序遍历的数组当中查找根节点所在的下标
    private int findIndexInorder(int[] inorder, int val, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public treeNode buildTree(int[] preorder, int[] inorder) {
        //首先得判断如果数组有一个为null，那么就无法确定一颗二叉树
        if (preorder == null || inorder == null) {
            return null;
        }
        //那么第一次的时候，在前序遍历拿到的第一个数字的时候，是在中序遍历的整个区间区寻找的，
        return buildTreeChild(preorder, inorder, 0, inorder.length - 1);
    }
    
}
