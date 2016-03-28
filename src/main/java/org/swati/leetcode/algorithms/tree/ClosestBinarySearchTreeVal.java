package org.swati.leetcode.algorithms.tree;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

 Note:
 Given target value is a floating point.
 You are guaranteed to have only one unique value in the BST that is closest to the target.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ClosestBinarySearchTreeVal {
    public int closestValue(TreeNode root, double target) {
        TreeNode node = root;
        int result = 0;
        double gap = Double.MAX_VALUE;

        while (node != null) {
            if (node.val == target) {
                return node.val;
            }

            double diff = Math.abs((double)node.val - target);

            if (diff < gap) {
                result = node.val;
                gap = diff;
            }

            if (node.val > target) {
                node = node.left;
            } else if (node.val < target) {
                node = node.right;
            }
        }
        return result;
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
