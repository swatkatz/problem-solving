package org.swati.leetcode.algorithms.tree;

/**
 * Generate a tree from inorder and postorder traversals
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class BuildTreeFromInPostOrder {
    /**
     * Definition for a binary tree node.*/
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return makeBTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode makeBTree(int[] inOrder, int[] postOrder, int iStart, int iEnd, int postStart, int postEnd) {
        if (iStart > iEnd) {
            return null;
        }

        int rootValue = postOrder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        if (iStart == iEnd) {
            return root;
        }
        int index = getInorderIndex(inOrder, iStart, iEnd, root.val);
        root.left = makeBTree(inOrder, postOrder, iStart, index - 1, postStart,
                postStart + index - (iStart + 1));
        root.right = makeBTree(inOrder, postOrder, index + 1, iEnd, postStart
                + index - iStart, postEnd - 1);
        return root;
    }

    public int getInorderIndex(int[] inOrder, int start, int end, int data) {
        for (int i = start; i <= end; i++) {
            if (inOrder[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BuildTreeFromInPostOrder buildTreeFromInPostOrder = new BuildTreeFromInPostOrder();
        int[] inorder = new int[] {5, 8, 4, 7, 9};
        int[] postorder = new int[] {5, 4, 8, 9, 7};
        TreeNode root = buildTreeFromInPostOrder.buildTree(inorder, postorder);
        System.out.println("node.val " + root.val);
    }
}
