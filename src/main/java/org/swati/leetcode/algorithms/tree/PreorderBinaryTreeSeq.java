package org.swati.leetcode.algorithms.tree;

import java.util.Stack;

/**
 * Verify Preorder Sequence in Binary Search Tree
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PreorderBinaryTreeSeq {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<Integer>();
        Integer low = Integer.MIN_VALUE;
        for (int i = 0; i < preorder.length; i++) {
            if (preorder[i] < low) {
                return false;
            }
            if (stack.empty()) {
                stack.push(preorder[i]);
            } else {
                if (preorder[i] >= stack.peek()) {
                    while (!stack.isEmpty() && preorder[i] > stack.peek()) {
                        low = stack.pop();
                    }
                }
                stack.push(preorder[i]);
            }
        }
        return true;
    }
}
