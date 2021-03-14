package Leetcode;

import JavaPractice.TestDemo3;
import JavaPractice.TestDemo38;
import sun.reflect.generics.tree.Tree;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.NodeChangeEvent;

/**
 * 二叉树的遍历，将其放入list<>当中
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;

    }

    public static class TestDemo12 {
        //创建二叉树
         TreeNode createNode1() {
            TreeNode node1 = new TreeNode(11);
            TreeNode node2 = new TreeNode(22);
            TreeNode node3 = new TreeNode(33);
            TreeNode node4 = new TreeNode(44);
            TreeNode node5 = new TreeNode(55);
            TreeNode node6 = new TreeNode(66);
            TreeNode node7 = new TreeNode(77);
            TreeNode node8 = new TreeNode(88);
            node1.left = node2;
            node1.right = node3;
            node2.left = node4;
            node2.right = node5;
            node3.left = node6;
            node3.right = node7;
            node4.left = node8;
            return node1;
        }

        /**
         * LeetCode前序遍历
         *
         * @param root
         * @return
         */
        List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            list.add(root.val);
            List<Integer> list1 = preorderTraversal(root.left);
            list.addAll(list1);
            List<Integer> list2 = preorderTraversal(root.right);
            list.addAll(list2);
            return list;
        }

        /**
         * leetCode中序遍历
         *
         * @param root
         * @return
         */
        List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            } else {
                List<Integer> list1 = inorderTraversal(root.left);
                list.addAll(list1);
                list.add(root.val);
                List<Integer> list2 = inorderTraversal(root.right);
                list.addAll(list2);
                return list;
            }

        }

        /**
         * LeetCode后续遍历
         * @param root
         * @return
         */
        List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            List<Integer> list1 = postorderTraversal(root.left);
            list.addAll(list1);
            List<Integer> list2 = postorderTraversal(root.right);
            list.addAll(list2);
            list.add(root.val);
            return list;
        }

        public static void main(String[] args) {
            TestDemo12 testDemo12 = new TestDemo12();
            TreeNode node = testDemo12.createNode1();
            System.out.println(testDemo12.preorderTraversal(node));
            System.out.println(testDemo12.inorderTraversal(node));
            System.out.println(testDemo12.postorderTraversal(node));
        }
    }
}
