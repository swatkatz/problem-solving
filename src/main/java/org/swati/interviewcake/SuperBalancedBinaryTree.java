package org.swati.interviewcake;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Write a function to see if a binary tree ↴ is "superbalanced" (a new tree property we just made up).
 A tree is "superbalanced" if the difference between the depths of any two leaf nodes is no greater than one.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class SuperBalancedBinaryTree {
    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public int depth;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }

    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;

    public boolean isTreeSuperBalanced(BinaryTreeNode root) {
        dfs(root, 0);
        return max - min <= 1;
    }

    private void dfs(BinaryTreeNode root, int depth) {
        if (root == null) {
            if (max < depth) {
                max = depth;
            }

            if (min > depth) {
                min = depth;
            }
            return;
        }
        System.out.println("root is " + root.value + " depth is " + depth);
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    /**
     * Iterative solution
     *
     */
    private boolean isTreeSuperBalancedItr(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

        root.depth = 0;
        stack.push(root);

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            if (node.right == null && node.left == null) {
                if (max < node.depth) {
                    max = node.depth;
                }

                if (min > node.depth) {
                    min = node.depth;
                }
            } else {
                if (node.left != null) {
                    node.left.depth = node.depth + 1;
                    stack.push(node.left);
                }

                if (node.right != null) {
                    node.right.depth = node.depth + 1;
                    stack.push(node.right);
                }
            }
        }
        return max - min <= 1;
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode two = new BinaryTreeNode(2);
        BinaryTreeNode three = new BinaryTreeNode(3);
        BinaryTreeNode four = new BinaryTreeNode(4);
        BinaryTreeNode five = new BinaryTreeNode(5);
        root.left = two;
        root.right = three;
        two.left = four;
        four.left = five;

        SuperBalancedBinaryTree superBalancedBinaryTree = new SuperBalancedBinaryTree();
        System.out.println(superBalancedBinaryTree.isTreeSuperBalanced(root));
        System.out.println(superBalancedBinaryTree.isTreeSuperBalancedItr(root));
    }
}
