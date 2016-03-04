package org.swati.leetcode.algorithms.tree;

import java.util.Stack;

/**
 * Get LCA for a binary tree
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class LCABinaryTree {
    class Node {
        Node left;
        Node right;
        Integer val;

        Node(Integer val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public boolean dfs(Node root, Stack<Integer> nodeStack, int val) {
        if (root == null) {
            return false;
        }
        nodeStack.push(root.val);
        if (root.val == val) {
            return true;
        }
        if (root.left != null && dfs(root.left, nodeStack, val) ||
                root.right != null && dfs(root.right, nodeStack, val)) {
            return true;
        }
        nodeStack.pop();
        return false;
    }

    public void buildTreeAndGetLCA() {
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        root.left = two;
        root.right = five;
        two.left = three;
        two.right = four;
        three.left = six;
        three.right = seven;

        System.out.println("LCA of 7 and 5 is " + getLCA(root, 5, 7));
    }

    public Integer getLCA(Node root, int val1, int val2) {
        Stack<Integer> parentList1 = new Stack<Integer>();
        Stack<Integer> parentList2 = new Stack<Integer>();
        dfs(root, parentList1, val1);
        dfs(root, parentList2, val2);

        Integer lca = null;
        for (int i = 0; i < Math.min(parentList1.size(), parentList2.size()); i++) {
            if (parentList1.get(i).equals(parentList2.get(i))) {
                lca = parentList1.get(i);
            } else {
                break;
            }
        }
        return lca;
    }

    public static void main(String[] args) {
        LCABinaryTree lcaBinaryTree = new LCABinaryTree();
        lcaBinaryTree.buildTreeAndGetLCA();
    }
}
