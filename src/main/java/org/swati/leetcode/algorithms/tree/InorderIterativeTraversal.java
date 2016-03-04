package org.swati.leetcode.algorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class InorderIterativeTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<TreeNode> inorder = new ArrayList<TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root == null) {
            return new ArrayList<Integer>(0);
        }
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && !inorder.contains(node.left)) {
                stack.push(node.left);
            } else if (node.right != null) {
                TreeNode top = stack.pop();
                inorder.add(top);
                stack.push(node.right);
            } else {
                TreeNode top = stack.pop();
                inorder.add(top);
            }
        }

        List<Integer> answer = new ArrayList<Integer>(inorder.size());
        for (TreeNode value : inorder) {
            answer.add(value.val);
        }
        return answer;
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
