package LeetCode;

/**
 * 根据二叉树创建字符串
 *
 */

class TreeNode1 {
      int val;
      TreeNode1 left;
      TreeNode1 right;
      TreeNode1(int x) { val = x; }
  }
public class leetcode606 {
    public void tree2str1(TreeNode1 t, StringBuffer sb){
        if(t == null){
            return;
        }
        sb.append(t.val);
        if(t.left == null){
            if(t.right == null){
                return;
            }else{
                sb.append("()");
            }
        }else{
            sb.append("(");
            tree2str1(t.left,sb);
            sb.append(")");
        }

        if(t.right == null){
            return;
        }else{
            sb.append("(");
            tree2str1(t.right,sb);
            sb.append(")");
        }
    }
    public String tree2str(TreeNode1 t) {
        StringBuffer sb = new StringBuffer();
        tree2str1(t,sb);
        return sb.toString();
    }
}
