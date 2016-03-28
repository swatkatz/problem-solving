package org.swati.leetcode.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swkumar
 * @since 1.0.0
 */
public class LargestBSTSubTree {
    private int count = 0;

    public int largestBSTSubtree(TreeNode root) {
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                count = 0;
                boolean isValid = isValidBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (isValid && max < count) {
                    max = count;
                }
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return max;
    }

    private boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val < min || root.val > max) {
            return false;
        }
        count = count + 1;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
