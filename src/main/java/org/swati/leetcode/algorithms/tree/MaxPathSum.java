package org.swati.leetcode.algorithms.tree;

/**
 * Given a binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node
 to any node in the tree along the parent-child connections. The path does not need to go through the root.

 For example:
 Given the below binary tree,

 1
 / \
 2   3
 Return 6.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MaxPathSum {

    class Res {
        int sum = Integer.MIN_VALUE;
    }

    public int maxPathSum(TreeNode root) {
        Res res = new Res();
        findMaxPathSum(root, res);
        return res.sum;
    }

    private int findMaxPathSum(TreeNode node, Res res) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int maxLeft = findMaxPathSum(node.left, res);
        int maxRight = findMaxPathSum(node.right, res);

        //either the path through one of the children is max or it's just the node itself
        int thisSubTreeMax = Math.max(getValue(Math.max(maxLeft, maxRight)) + node.val, node.val);

        //the actual value to be set at this step of the recursion..it is either the path through the tree or the entire sub-tree itself
        int currPathMax = Math.max(thisSubTreeMax, node.val + getValue(maxLeft) + getValue(maxRight));
        res.sum = Math.max(res.sum, currPathMax);

        return thisSubTreeMax;
    }

    private int getValue(int value) {
        return value == Integer.MIN_VALUE ? 0 : value;
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
