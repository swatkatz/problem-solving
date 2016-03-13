package org.swati.leetcode.algorithms.tree;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ValidBinarySearchTree {
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public static boolean isValidBST(TreeNode p, double min, double max) {
        if(p==null)
            return true;

        if(p.val <= min || p.val >= max)
            return false;

        return isValidBST(p.left, min, p.val) && isValidBST(p.right, p.val, max);
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode one = new TreeNode(1);
        TreeNode seven = new TreeNode(7);
        root.left = one;
        root.right = seven;
        TreeNode zero = new TreeNode(0);
        TreeNode two = new TreeNode(2);
        //TreeNode four = new TreeNode(4);
        one.left = zero;
        one.right = two;
        TreeNode ten = new TreeNode(10);
        seven.left = two;
        seven.right = ten;
        System.out.println(" isValid : " + isValidBST(root));
    }
}
