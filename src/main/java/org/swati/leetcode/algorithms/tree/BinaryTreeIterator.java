package org.swati.leetcode.algorithms.tree;

import java.util.Stack;

/**
 * @author swkumar
 * @since 1.0.0
 */
public class BinaryTreeIterator {
    private Stack<TreeNode> stack;

    public BinaryTreeIterator(TreeNode root) {
        this.stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                TreeNode start = node.right;
                while (start != null) {
                    stack.push(start);
                    start = start.left;
                }
            }
            return node.val;
        }
        return -1;
    }

    /**
     * Definition for a binary tree node.*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
