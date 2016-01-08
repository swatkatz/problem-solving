package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.List;

/**
 * Find if a given binary tree has duplicate sub trees.
 * (just two leaf nodes of a parent do not count as subtree in this question).
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class DuplicateSubTrees {
    private boolean duplicateFound = false;
    private List<TreeBuilder.Node> subTreeRoots;

    private boolean isDuplicateSubTreePresent(TreeBuilder.Node root) {
        duplicateFound = false;
        subTreeRoots = new ArrayList<TreeBuilder.Node>();
        traverseTree(root);
        return duplicateFound;
    }

    /**
     * Traverse the tree in preorder and check for duplicates at each node of the tree
     * @param node: the current node being traversed
     */
    private void traverseTree(TreeBuilder.Node node) {
        if (!duplicateFound) {
            if (node != null) {
                //is a parent
                if (node.left != null || node.right != null) {
                    for (TreeBuilder.Node compareNode : subTreeRoots) {
                    //before comparison, we could add a check to see if compareNode is it's parent, then don't compare.
                        duplicateFound = isIdentical(compareNode, node);
                        if (duplicateFound) {
                            System.out.println("Dup1 is " + node + " Dup2 is " + compareNode);
                            break;
                        }
                    }
                    subTreeRoots.add(node);
                }
                traverseTree(node.left);
                traverseTree(node.right);
            }
        }
    }

    /**
     * Given two disctinct nodes in the tree, finds if the subtrees are identical
     * @param node1: start node 1
     * @param node2: start node 2
     * @return if the subtrees are identical
     */
    private boolean isIdentical(TreeBuilder.Node node1, TreeBuilder.Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        return node1.id.equals(node2.id)
                && isIdentical(node1.left, node2.left)
                && isIdentical(node1.right, node2.right);
    }

    public static void main(String[] args) {
        //dups 1
        TreeBuilder.Node n7 = new TreeBuilder.Node(1, null, null, "n7");
        TreeBuilder.Node n8 = new TreeBuilder.Node(1, null, null, "n8");
        TreeBuilder.Node n6 = new TreeBuilder.Node(1, null, null, "n6");
        TreeBuilder.Node n5 = new TreeBuilder.Node(1, null, null, "n5");
        TreeBuilder.Node n2 = new TreeBuilder.Node(1, null, null, "n2");
        TreeBuilder.Node n3 = new TreeBuilder.Node(1, n7, n8, "n3");
        TreeBuilder.Node n1 = new TreeBuilder.Node(1, n2, n3, "n1");
        TreeBuilder.Node n4 = new TreeBuilder.Node(1, n5, n6, "n4");
        TreeBuilder.Node root = new TreeBuilder.Node(1, n1, n4, "root");
        DuplicateSubTrees duplicateSubTrees = new DuplicateSubTrees();
        System.out.println("Has duplicate " + duplicateSubTrees.isDuplicateSubTreePresent(root));

        //dups 2
        TreeBuilder.Node n11 = new TreeBuilder.Node(7, null, null, "n11");
        TreeBuilder.Node n12 = new TreeBuilder.Node(4, null, null, "n12");
        TreeBuilder.Node n13 = new TreeBuilder.Node(7, null, null, "n13");
        TreeBuilder.Node n14 = new TreeBuilder.Node(4, null, null, "n14");
        TreeBuilder.Node n9 = new TreeBuilder.Node(6, null, null, "n9");
        n7 = new TreeBuilder.Node(6, null, null, "n7");
        TreeBuilder.Node n10 = new TreeBuilder.Node(8, n11, n12, "n10");
        n8 = new TreeBuilder.Node(2, n9, n10, "n8");
        n6 = new TreeBuilder.Node(2, n7, n8, "n6");
        n4 = new TreeBuilder.Node(6, null, null, "n4");
        n5 = new TreeBuilder.Node(8, n13, n14, "n5");
        n2 = new TreeBuilder.Node(2, n4, n5, "n2");
        n3 = new TreeBuilder.Node(1, null, null, "n3");
        n1 = new TreeBuilder.Node(3, n2, n3, "n1");
        root = new TreeBuilder.Node(4, n1, n6, "root");
        System.out.println("Has duplicate " + duplicateSubTrees.isDuplicateSubTreePresent(root));

        //no dups
        n1 = new TreeBuilder.Node(1, null, null);
        n2 = new TreeBuilder.Node(2, null, null);
        n3 = new TreeBuilder.Node(3, n1, n2);
        n4 = new TreeBuilder.Node(4, null, null);
        n5 = new TreeBuilder.Node(5, n4, null);
        root = new TreeBuilder.Node(0, n3, n5);
        System.out.println("Has duplicate " + duplicateSubTrees.isDuplicateSubTreePresent(root));
    }
}
