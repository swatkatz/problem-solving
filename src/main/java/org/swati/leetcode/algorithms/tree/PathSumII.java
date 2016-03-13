package org.swati.leetcode.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 Given the below binary tree and sum = 22,
 5
 /  \
 4   8
 /   / \
 11  13  4
 / \    / \
 7    2  5   1
 return
 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PathSumII {
    List<List<Integer>> allLists;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        allLists = new ArrayList<List<Integer>>();
        List<Integer> aList = new ArrayList<Integer>();
        dfs(root, sum, aList, Integer.MIN_VALUE);
        return allLists;
    }

    private void dfs(TreeNode node, int given, List<Integer> partialList, int partialSum) {
        if (node != null) {
            if (partialSum == Integer.MIN_VALUE) {
                partialSum = node.val;
            } else {
                partialSum += node.val;
            }
            partialList.add(node.val);
            //leaf node
            if (node.left == null && node.right == null) {
                if (partialSum == given) {
                    List<Integer> newList = new ArrayList<Integer>(partialList);
                    allLists.add(newList);
                }
            } else {
                dfs(node.left, given, partialList, partialSum);
                dfs(node.right, given, partialList, partialSum);
            }
            partialList.remove(node.val);
        }
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
