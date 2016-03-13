package org.swati.leetcode.algorithms.tree;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.

 For example,

 1
 / \
 2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.

 Return the sum = 12 + 13 = 25.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class SumToRootLeafNumbers {
    public int sumNumbers(TreeNode root) {
        int sum = 0;
        String path = "";
        return findAllPaths(root, path, sum);
    }

    private int findAllPaths(TreeNode node, String path, int sum) {
        if (node != null) {
            path = path + node.val;
            if (node.left == null && node.right == null) {
                return Integer.parseInt(path);
            }
            return sum + findAllPaths(node.left, path, sum) + findAllPaths(node.right, path, sum);
        }
        return sum;
    }

    private int findPaths(TreeNode node, String path, int sum) {
        if (node != null) {
            path = path + node.val;
            if (node.left == null && node.right == null) {
                return Integer.parseInt(path);
            }
            sum += findPaths(node.left, path, sum);
            sum += findPaths(node.right, path, sum);
        }
        return sum;
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
