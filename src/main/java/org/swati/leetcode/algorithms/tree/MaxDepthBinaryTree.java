package org.swati.leetcode.algorithms.tree;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes
 * along the longest path from the root node down to the farthest leaf node.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MaxDepthBinaryTree {
    int max = 0;
    public int maxDepth(TreeNode root) {
        max = 0;
        dfs(root, 0);
        return max;
    }

    public void dfs(TreeNode node, int depth) {
        if (node != null) {
            depth = depth + 1;
            //if leaf node
            if (node.left == null && node.right == null) {
                if (max < depth) {
                    max = depth;
                }
            } else {
                dfs(node.left, depth);
                dfs(node.right, depth);
            }
        }
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
