package LeetCode;


class treeNode {
    int val;
    treeNode left;
    treeNode right;
    public treeNode() {}
    treeNode(int val) {
        this.val = val;
    }
    treeNode(int val, treeNode left, treeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
public static class leetcode106 {
    public int postIndex = 0;
    //根据大体思路是后序遍历确定，也即是左右根
    public treeNode buildTreeChild(int[] inorder, int[] postorder, int begin, int end) {
        if(begin > end){
            return null;
        }
        //根据给出的后序遍历数组，然后从最后一个数字还是遍历，然后在区中序遍历中找这个数的索引位置
        treeNode root = new treeNode(postorder[postIndex]);
        //然后再中序遍历中找到它的位置
        int rootIndex = findIndex(inorder,postorder[postIndex],begin,end);
        postIndex--;

        //此时创建root的左树和右树,先构建右树，再构建左树
        root.right = buildTreeChild(inorder,postorder,rootIndex+1,end);
        root.left = buildTreeChild(inorder,postorder,begin,rootIndex-1);
        return root;
    }
    private int findIndex(int[] inorder,int val,int begin,int end){
        for(int i = begin; i <= end ;i++){
            if(inorder[i] == val){
                return i;
            }
        }
        return -1;
    }
    public treeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null){
            return null;
        }
        postIndex = postorder.length-1;
        return buildTreeChild(inorder,postorder,0,inorder.length-1);
    }
}

}
