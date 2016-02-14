package org.swati.problemSolving.datastructures;

/**
 * Represents the Node in a BinarySearchTree
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Node {
    private int value;
    private Node left;
    private Node right;
    private Node parent;

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public Node(int value, Node left, Node right, Node parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public Node(Node node) {
        this.value = node.getValue();
        this.left = node.getLeft();
        this.right = node.getRight();
        this.parent = node.getParent();
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
