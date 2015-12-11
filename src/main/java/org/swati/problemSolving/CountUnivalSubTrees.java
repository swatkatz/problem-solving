package org.swati.problemSolving;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class CountUnivalSubTrees {
    public static class Node {
        Node left;
        Node right;
        int val;

        public Node(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getVal() {
            return val;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private static int count = 0;
    public void countUnivalSubTrees(Node node, Node parent) {
        if (node == null) {
            return;
        }

        if (parent != null && node.getVal() != parent.getVal() && isUnival(node)) {
            count++;
        }
        countUnivalSubTrees(node.getLeft(), node);
        countUnivalSubTrees(node.getRight(), node);
    }

    private boolean isUnival(Node node) {
        Node left = node.getLeft();
        Node right = node.getRight();
        if (left == null && right == null) { //leaf node is a unival in itself
            return true;
        }
        if (left == null && node.getVal() == right.getVal()) {
            return true;
        }
        if (right == null && node.getVal() == left.getVal()) {
            return true;
        }
        if (left != null && right != null && node.getVal() == left.getVal() && node.getVal() == right.getVal()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CountUnivalSubTrees countUnivalSubTrees = new CountUnivalSubTrees();
        Node root = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(2);
        Node n4 = new Node(2);
        Node n5 = new Node(5);
        Node n6 = new Node(5);
        Node n7 = new Node(3);
        Node n8 = new Node(3);
        Node n9 = new Node(4);
        Node n10 = new Node(4);
        Node n11 = new Node(3);
        Node n12 = new Node(3);

        root.setLeft(n1);
        root.setRight(n2);
        n1.setLeft(n3);
        n1.setRight(n4);
        n3.setLeft(n5);
        n3.setRight(n6);
        n2.setLeft(n7);
        n2.setRight(n8);
        n7.setLeft(n9);
        n7.setRight(n10);
        n8.setLeft(n11);
        n8.setRight(n12);

        countUnivalSubTrees.countUnivalSubTrees(root, null);
        System.out.println("Univals are " + count);
    }
}
